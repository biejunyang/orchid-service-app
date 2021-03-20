package com.orchid.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchid.core.Result;
import com.orchid.core.factory.TreeBuildFactory;
import com.orchid.system.dao.SysPrivilegeDao;
import com.orchid.system.entity.SysPrivilege;
import com.orchid.system.service.SysPrivilegeService;
import netscape.security.Privilege;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统权限信息表(SysPrivilege)表服务实现类
 *
 * @author makejava
 * @since 2021-03-18 16:20:46
 */
@Service("sysPrivilegeService")
public class SysPrivilegeServiceImpl extends ServiceImpl<SysPrivilegeDao, SysPrivilege> implements SysPrivilegeService {


    @Override
    public List<SysPrivilege> tree(SysPrivilege sysPrivilege) {
        QueryWrapper<SysPrivilege> wrapper=new QueryWrapper<>(sysPrivilege);
        List<SysPrivilege> privileges=this.list(wrapper.orderByAsc("sort", "id"));
        return new TreeBuildFactory<SysPrivilege>().doTreeBuild(privileges);
    }
}
