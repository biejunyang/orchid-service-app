package com.orchid.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orchid.system.entity.SysPrivilege;
import com.orchid.system.entity.SysRole;

import java.util.List;

/**
 * 系统角色信息表(SysRole)表服务接口
 *
 * @author makejava
 * @since 2021-03-18 16:20:46
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysPrivilege> rolePrivileges(Long roleId);

    void grantPrivileges(Long roleId, List<Long> privilegeIds);

    void deleteByIds(List<Long> roleIds);


}
