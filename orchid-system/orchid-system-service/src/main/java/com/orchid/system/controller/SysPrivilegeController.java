package com.orchid.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orchid.core.Result;
import com.orchid.core.factory.TreeBuildFactory;
import com.orchid.system.entity.SysPrivilege;
import com.orchid.system.service.SysPrivilegeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统权限信息表(SysPrivilege)表控制层
 *
 * @author makejava
 * @since 2021-03-18 16:20:46
 */
@RestController
@RequestMapping("privilege")
public class SysPrivilegeController{
    /**
     * 服务对象
     */
    @Resource
    private SysPrivilegeService sysPrivilegeService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping
    public Result selectTree(SysPrivilege sysPrivilege) {
        QueryWrapper<SysPrivilege> wrapper=new QueryWrapper<>(sysPrivilege);
        List<SysPrivilege> privileges=sysPrivilegeService.list(wrapper.orderByAsc("sort", "id"));
        return Result.success(new TreeBuildFactory<SysPrivilege>().doTreeBuild(privileges));
    }


//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("{id}")
//    public R selectOne(@PathVariable Serializable id) {
//        return success(this.sysPrivilegeService.getById(id));
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param sysPrivilege 实体对象
//     * @return 新增结果
//     */
//    @PostMapping
//    public R insert(@RequestBody SysPrivilege sysPrivilege) {
//        return success(this.sysPrivilegeService.save(sysPrivilege));
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param sysPrivilege 实体对象
//     * @return 修改结果
//     */
//    @PutMapping
//    public R update(@RequestBody SysPrivilege sysPrivilege) {
//        return success(this.sysPrivilegeService.updateById(sysPrivilege));
//    }
//
//    /**
//     * 删除数据
//     *
//     * @param idList 主键结合
//     * @return 删除结果
//     */
//    @DeleteMapping
//    public R delete(@RequestParam("idList") List<Long> idList) {
//        return success(this.sysPrivilegeService.removeByIds(idList));
//    }
}
