package com.orchid.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orchid.system.entity.SysOrgan;

/**
 * 组织机构信息表(SysOrgan)表服务接口
 *
 * @author makejava
 * @since 2021-03-23 10:23:45
 */
public interface SysOrganService extends IService<SysOrgan> {

    void updateOrgan(SysOrgan organ);
}
