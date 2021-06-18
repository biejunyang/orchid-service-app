package com.orchid.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchid.system.dao.SysConfigDao;
import com.orchid.system.entity.SysConfig;
import com.orchid.system.service.SysConfigService;
import org.springframework.stereotype.Service;

/**
 * 系统参数配置表(SysConfig)表服务实现类
 *
 * @author makejava
 * @since 2021-06-18 14:10:04
 */
@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfig> implements SysConfigService {

}
