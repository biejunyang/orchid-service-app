package com.orchid.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.orchid.system.entity.SysPrivilege;
import com.orchid.system.entity.SysRole;
import com.orchid.system.entity.SysUser;
import com.orchid.system.vo.UserVo;

import java.util.List;

/**
 * 用户信息表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2021-03-23 20:35:48
 */
public interface SysUserService extends IService<SysUser>{

    List<SysUser> findUsers(SysUser userVo);

    IPage<SysUser> findUsers(IPage<SysUser> page, SysUser userVo);

    void deleteUsers(List<Long> ids);


    List<SysRole> userRoles(Long userId);

    List<SysPrivilege> userPrivileges(Long userId);

    void grantRole(UserVo userVo);
}
