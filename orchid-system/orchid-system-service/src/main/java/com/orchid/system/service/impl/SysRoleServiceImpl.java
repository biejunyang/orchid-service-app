package com.orchid.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchid.system.dao.SysRoleDao;
import com.orchid.system.entity.SysRole;
import com.orchid.system.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * 系统角色信息表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2021-03-18 16:20:46
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

}
