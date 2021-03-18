package com.orchid.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orchid.system.entity.SysPrivilege;
import com.orchid.system.service.SysPrivilegeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 系统权限信息表(SysPrivilege)表控制层
 *
 * @author makejava
 * @since 2021-03-18 16:20:46
 */
@RestController
@RequestMapping("sysPrivilege")
public class SysPrivilegeController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysPrivilegeService sysPrivilegeService;

    /**
     * 分页查询所有数据
     *
     * @param page         分页对象
     * @param sysPrivilege 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SysPrivilege> page, SysPrivilege sysPrivilege) {
        return success(this.sysPrivilegeService.page(page, new QueryWrapper<>(sysPrivilege)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.sysPrivilegeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysPrivilege 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody SysPrivilege sysPrivilege) {
        return success(this.sysPrivilegeService.save(sysPrivilege));
    }

    /**
     * 修改数据
     *
     * @param sysPrivilege 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SysPrivilege sysPrivilege) {
        return success(this.sysPrivilegeService.updateById(sysPrivilege));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.sysPrivilegeService.removeByIds(idList));
    }
}
