package com.orchid.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchid.system.dao.SysUserDao;
import com.orchid.system.entity.SysUser;
import com.orchid.system.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2021-03-23 20:35:48
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

}
