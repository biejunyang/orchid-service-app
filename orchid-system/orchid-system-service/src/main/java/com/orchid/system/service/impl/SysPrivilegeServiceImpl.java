package com.orchid.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchid.core.util.TreeUtil;
import com.orchid.system.dao.SysPrivilegeDao;
import com.orchid.system.entity.SysPrivilege;
import com.orchid.system.entity.SysRolePrivilege;
import com.orchid.system.service.SysPrivilegeService;
import com.orchid.system.service.SysRolePrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统权限信息表(SysPrivilege)表服务实现类
 *
 * @author makejava
 * @since 2021-03-18 16:20:46
 */
@Service("sysPrivilegeService")
public class SysPrivilegeServiceImpl extends ServiceImpl<SysPrivilegeDao, SysPrivilege> implements SysPrivilegeService {

    @Autowired
    private SysRolePrivilegeService rolePrivilegeService;


    @Override
    public List<SysPrivilege> tree(SysPrivilege sysPrivilege) {
        QueryWrapper<SysPrivilege> wrapper=new QueryWrapper<>(sysPrivilege);
        List<SysPrivilege> privileges=this.list(wrapper.orderByAsc("sort", "id"));
        if(CollUtil.isNotEmpty(privileges)){
            Set<Long> parentIds=new HashSet<>();
            privileges.forEach(item -> {
                if(StrUtil.isNotEmpty(item.getPids())){
                    parentIds.addAll(Arrays.stream(item.getPids().split(",")).map(Long::new).collect(Collectors.toList()));
                }
            });
            List<Long> ids = privileges.parallelStream().map(SysPrivilege::getId).collect(Collectors.toList());
            parentIds.removeAll(ids);
            if(CollUtil.isNotEmpty(parentIds)){
                privileges.addAll(this.lambdaQuery().in(SysPrivilege::getId, parentIds).list());
            }
            return TreeUtil.buildTree(privileges);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePrivilege(Long id) {
        SysPrivilege privilege=this.getById(id);
        String pids = (StrUtil.isNotEmpty(privilege.getPids()) ? privilege.getPids()+",": "") +privilege.getId();

        rolePrivilegeService.remove(Wrappers.<SysRolePrivilege>lambdaQuery()
                .apply("privilege_id in (select id from sys_privilege where pids like {0} or id = {1})", pids+"%", id));

        this.remove(Wrappers.<SysPrivilege>lambdaQuery()
                .likeRight(SysPrivilege::getPids, pids).or().eq(SysPrivilege::getId, id));

    }
}
