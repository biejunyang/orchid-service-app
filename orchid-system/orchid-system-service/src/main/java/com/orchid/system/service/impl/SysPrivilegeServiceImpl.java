package com.orchid.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchid.system.dao.SysPrivilegeDao;
import com.orchid.system.entity.SysPrivilege;
import com.orchid.system.service.SysPrivilegeService;
import org.springframework.stereotype.Service;

/**
 * 系统权限信息表(SysPrivilege)表服务实现类
 *
 * @author makejava
 * @since 2021-03-18 16:20:46
 */
@Service("sysPrivilegeService")
public class SysPrivilegeServiceImpl extends ServiceImpl<SysPrivilegeDao, SysPrivilege> implements SysPrivilegeService {

}
