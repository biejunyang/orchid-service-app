package com.orchid.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orchid.core.Result;
import com.orchid.mybatis.util.AssertUtils;
import com.orchid.system.entity.SysConfig;
import com.orchid.system.service.SysConfigService;
import com.orchid.web.aop.NoRepeatInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;


/**
 * 系统参数配置表(SysConfig)表控制层
 *
 * @author makejava
 * @since 2021-06-18 14:10:04
 */
@RestController
@RequestMapping("sysConfig")
public class SysConfigController {
    /**
     * 服务对象
     */
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 分页查询数据
     *
     * @param page      分页对象
     * @param sysConfig 查询实体
     * @return 所有数据
     */
    @GetMapping("{page}/{size}")
    public Result findPage(@PathVariable Integer page, @PathVariable Integer size, SysConfig sysConfig) {
        Page<SysConfig> pageHelper = new Page<>(page, size);
        return Result.success(this.sysConfigService.page(pageHelper, new QueryWrapper<>(sysConfig)));
    }

    /**
     * 查询数据列表
     *
     * @param sysConfig 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result find(SysConfig sysConfig) {
        return Result.success(this.sysConfigService.list(new QueryWrapper<>(sysConfig)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.sysConfigService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysConfig 实体对象
     * @return 新增结果
     */
    @NoRepeatInsert(key="'SysConfig:code:'+#sysConfig.code", label = "参数编码")
//    @NoRepeatInsert(label = "参数编码", name="code")
    @PostMapping
    public Result insert(@RequestBody SysConfig sysConfig) {
        AssertUtils.columnNotUsed(sysConfigService.getBaseMapper(), sysConfig, "参数编码", SysConfig::getCode);
        return Result.success(this.sysConfigService.save(sysConfig));
    }

    /**
     * 修改数据
     *
     * @param sysConfig 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody SysConfig sysConfig) {
        return Result.success(this.sysConfigService.updateById(sysConfig));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.sysConfigService.removeByIds(idList));
    }
}
