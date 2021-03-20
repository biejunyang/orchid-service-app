package com.orchid.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orchid.system.entity.SysPrivilege;
import netscape.security.Privilege;

import java.util.List;

/**
 * 系统权限信息表(SysPrivilege)表服务接口
 *
 * @author makejava
 * @since 2021-03-18 16:20:45
 */
public interface SysPrivilegeService extends IService<SysPrivilege> {

    List<SysPrivilege> tree(SysPrivilege sysPrivilege);
}
