package com.orchid.system.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.orchid.core.Result;
import com.orchid.mybatis.util.AssertUtils;
import com.orchid.system.entity.SysPrivilege;
import com.orchid.system.service.SysPrivilegeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

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
        return Result.success(sysPrivilegeService.tree(sysPrivilege));
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.sysPrivilegeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysPrivilege 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody SysPrivilege sysPrivilege) {

        AssertUtils.columnNotUsed(sysPrivilegeService.getBaseMapper(), sysPrivilege, "权限编码", SysPrivilege::getCode);

        sysPrivilege.setPids(null);
        if(sysPrivilege.getParentId()!=null){
            SysPrivilege parent=sysPrivilegeService.getById(sysPrivilege.getParentId());
            String pids= (StrUtil.isNotEmpty(parent.getPids()) ? parent.getPids()+"," : "") + parent.getId();
            sysPrivilege.setPids(pids);
        }
        sysPrivilegeService.save(sysPrivilege);
        return Result.success();
    }



    /**
     * 修改数据
     *
     * @param sysPrivilege 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody SysPrivilege sysPrivilege) {
        SysPrivilege old=sysPrivilegeService.getById(sysPrivilege.getId());

        AssertUtils.columnNotUsed(sysPrivilegeService.getBaseMapper(), sysPrivilege, "权限编码", SysPrivilege::getCode, old);
        sysPrivilegeService.updateById(sysPrivilege);
        return Result.success();
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {

        sysPrivilegeService.deletePrivilege(id);
        return Result.success();
    }
}
