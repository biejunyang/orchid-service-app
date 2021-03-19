package com.orchid.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orchid.core.Result;
import com.orchid.system.entity.SysRole;
import com.orchid.system.service.SysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 系统角色信息表(SysRole)表控制层
 *
 * @author makejava
 * @since 2021-03-18 16:20:47
 */
@RestController
@RequestMapping("role")
public class SysRoleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param sysRole 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<SysRole> page, SysRole sysRole) {
        return Result.success(this.sysRoleService.page(page, new QueryWrapper<>(sysRole).orderByDesc("id")));
    }




    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.sysRoleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysRole 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody SysRole sysRole) {
        sysRoleService.save(sysRole);
        return Result.success();
    }



    /**
     * 修改数据
     *
     * @param sysRole 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody SysRole sysRole) {
        this.sysRoleService.updateById(sysRole);
        return Result.success();
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.sysRoleService.removeByIds(idList));
    }


    /**
     * 获取角色拥有的权限
     * @return
     */
    @GetMapping("privileges")
    public Result rolePrivilege(Integer id){
        return Result.success();
    }
}
