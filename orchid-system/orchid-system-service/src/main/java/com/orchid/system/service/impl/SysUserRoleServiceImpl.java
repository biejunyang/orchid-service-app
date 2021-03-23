package com.orchid.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchid.system.dao.SysUserRoleDao;
import com.orchid.system.entity.SysUserRole;
import com.orchid.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户角色关联信息表(SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2021-03-23 20:35:51
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

}
