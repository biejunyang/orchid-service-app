package com.orchid.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orchid.core.Result;
import com.orchid.system.entity.SysOperateLog;
import com.orchid.system.service.SysOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;


/**
 * 操作日志表(SysOperateLog)表控制层
 *
 * @author makejava
 * @since 2021-06-29 17:00:43
 */
@RestController
@RequestMapping("operateLog")
public class SysOperateLogController {
    /**
     * 服务对象
     */
    @Autowired
    private SysOperateLogService sysOperateLogService;

    /**
     * 分页查询数据
     *
     * @param page          分页对象
     * @param sysOperateLog 查询实体
     * @return 所有数据
     */
    @GetMapping("{page}/{size}")
    public Result findPage(@PathVariable Integer page, @PathVariable Integer size, SysOperateLog sysOperateLog) {
        Page<SysOperateLog> pageHelper = new Page<>(page, size);
        return Result.success(this.sysOperateLogService.page(pageHelper, new QueryWrapper<>(sysOperateLog)));
    }

    /**
     * 查询数据列表
     *
     * @param sysOperateLog 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result find(SysOperateLog sysOperateLog) {
        return Result.success(this.sysOperateLogService.list(new QueryWrapper<>(sysOperateLog)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.sysOperateLogService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysOperateLog 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody SysOperateLog sysOperateLog) {
        return Result.success(this.sysOperateLogService.save(sysOperateLog));
    }

    /**
     * 修改数据
     *
     * @param sysOperateLog 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody SysOperateLog sysOperateLog) {
        return Result.success(this.sysOperateLogService.updateById(sysOperateLog));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.sysOperateLogService.removeByIds(idList));
    }
}
