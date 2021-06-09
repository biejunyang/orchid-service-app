package com.orchid.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchid.system.dao.SysRoleDao;
import com.orchid.system.entity.SysPrivilege;
import com.orchid.system.entity.SysRole;
import com.orchid.system.entity.SysRolePrivilege;
import com.orchid.system.entity.SysUserRole;
import com.orchid.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统角色信息表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2021-03-18 16:20:46
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Autowired
    private SysRolePrivilegeService rolePrivilegeService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Autowired
    private SysPrivilegeService privilegeService;

    @Override
    public List<SysPrivilege> rolePrivileges(Long roleId) {
        List<Long> rolePrivilegeIds = rolePrivilegeService.lambdaQuery().eq(SysRolePrivilege::getRoleId, roleId)
                .select(SysRolePrivilege::getPrivilegeId).list()
                .parallelStream().map(SysRolePrivilege::getPrivilegeId)
                .collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(rolePrivilegeIds)) {
            return privilegeService.lambdaQuery().in(SysPrivilege::getId, rolePrivilegeIds).list();
        }
        return CollectionUtil.newArrayList();
    }

    @Override
    public void grantPrivileges(Long roleId, List<Long> privilegeIds) {
        rolePrivilegeService.remove(Wrappers.<SysRolePrivilege>lambdaQuery()
                .eq(SysRolePrivilege::getRoleId, roleId));
        if(CollUtil.isNotEmpty(privilegeIds)){
            List<SysRolePrivilege> rolePrivileges=new ArrayList<>();
            privilegeIds.forEach(pid->rolePrivileges.add(new SysRolePrivilege(roleId, pid)));
            rolePrivilegeService.saveBatch(rolePrivileges);
        }
    }


    /**
     * 删除角色信息
     * @param roleIds
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<Long> roleIds) {

        //删除角色权限关联信息
        rolePrivilegeService.remove(Wrappers.<SysRolePrivilege>lambdaQuery()
                .in(SysRolePrivilege::getRoleId, roleIds));

        //删除用户角色关联信息
        userRoleService.remove(Wrappers.<SysUserRole>lambdaQuery()
                .in(SysUserRole::getRoleId, roleIds));

        //删除角色
        this.removeByIds(roleIds);
    }
}
