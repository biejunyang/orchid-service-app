package com.orchid.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchid.core.Result;
import com.orchid.system.dao.SysUserDao;
import com.orchid.system.entity.SysPrivilege;
import com.orchid.system.entity.SysRole;
import com.orchid.system.entity.SysUser;
import com.orchid.system.entity.SysUserRole;
import com.orchid.system.service.SysPrivilegeService;
import com.orchid.system.service.SysRoleService;
import com.orchid.system.service.SysUserRoleService;
import com.orchid.system.service.SysUserService;
import com.orchid.system.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2021-03-23 20:35:48
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private SysUserRoleService userRoleService;


    @Autowired
    private SysPrivilegeService privilegeService;

    @Autowired
    private SysRoleService roleService;

    @Override
    public List<SysUser> findUsers(SysUser userVo) {
        return userDao.findUsers(userVo);
    }

    @Override
    public IPage<SysUser> findUsers(IPage<SysUser> page, SysUser userVo) {
        List<SysUser> list=userDao.findUsers(page, userVo);
        return page.setRecords(list);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUsers(List<Long> ids) {
        //删除用户关联的角色信息
        userRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().in(SysUserRole::getUserId, ids));

        //删除用户信息
        this.removeByIds(ids);
    }


    /**
     * 获取用户拥有角色
     * @param user
     * @return
     */
    @Override
    public List<SysRole> userRoles(SysUser user) {
        List<SysRole> roles=new ArrayList<>();
        if(user.getAdminType().equals(1)){
            //管理员角色默认拥有全部角色
        }else{
            List<Long> roleIds=userRoleService.list(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, user.getId()))
                    .parallelStream().map(SysUserRole::getRoleId).collect(Collectors.toList());
            if(CollectionUtil.isNotEmpty(roleIds)){
                roles=roleService.list(Wrappers.<SysRole>lambdaQuery().in(SysRole::getId, roleIds));
            }
        }
        return roles;
    }


    /**
     * 获取用户拥有的权限
     * @param user
     * @return
     */
    @Override
    public List<SysPrivilege> userPrivileges(SysUser user) {
        List<SysPrivilege> privileges=new ArrayList<>();
        if(user.getAdminType().equals(1)){
            //管理员角色默认拥有所有权限
        }else{
            privileges=userDao.userPrivileges(user.getId());
        }
        return privileges;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void grantRole(UserVo userVo) {
        //登录用户拥有的角色
        List<Long> ownRoleIds=roleService.list().parallelStream().map(SysRole::getId).collect(Collectors.toList());

        userRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userVo.getId())
                .in(SysUserRole::getRoleId, ownRoleIds));

        if(CollectionUtil.isNotEmpty(userVo.getRoleIds())){
            userVo.getRoleIds().forEach(r -> {
                userRoleService.save(new SysUserRole(null, userVo.getId(), r));
            });
        }
    }

}
