package com.orchid.system.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orchid.core.Result;
import com.orchid.core.util.TreeUtil;
import com.orchid.mybatis.util.AssertUtils;
import com.orchid.system.entity.SysOrgan;
import com.orchid.system.service.SysOrganService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 组织机构信息表(SysOrgan)表控制层
 *
 * @author makejava
 * @since 2021-03-23 10:23:45
 */
@RestController
@RequestMapping("organ")
public class SysOrganController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysOrganService sysOrganService;

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param sysOrgan 查询实体
     * @return 所有数据
     */
    @GetMapping()
    public Result selectAll(Page<SysOrgan> page, SysOrgan sysOrgan) {
        QueryWrapper wrapper=new QueryWrapper();
        if(StrUtil.isNotEmpty(sysOrgan.getName())){
            wrapper.like("name", "%"+sysOrgan.getName()+"%");
        }
        if(sysOrgan.getPids()!=null){
            wrapper.likeRight("pids", sysOrgan.getPids());
            if(StrUtil.isEmpty(sysOrgan.getName())){
                String[] strs=sysOrgan.getPids().split(",");
                wrapper.or(true).eq("id", Long.valueOf(strs[strs.length-1]));
            }
        }
        return Result.success(this.sysOrganService.page(page, wrapper));
    }

    /**
     * 获取树形列表
     * @param sysOrgan
     * @return
     */
    @GetMapping("tree")
    public Result selectTree(SysOrgan sysOrgan) {
        List<SysOrgan> organs=this.sysOrganService.list(new QueryWrapper<>(sysOrgan));
        return Result.success(TreeUtil.buildTree(organs));
    }



    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.sysOrganService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysOrgan 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody SysOrgan sysOrgan) {
        AssertUtils.columnNotUsed(this.sysOrganService.getBaseMapper(), sysOrgan, "机构名称", SysOrgan::getName);
        AssertUtils.columnNotUsed(this.sysOrganService.getBaseMapper(), sysOrgan, "机构编码", SysOrgan::getCode);
        sysOrgan.setPids(null);
        if(sysOrgan.getParentId()!=null){
            SysOrgan parent=sysOrganService.getById(sysOrgan.getParentId());
            String pids= (StrUtil.isNotEmpty(parent.getPids()) ? parent.getPids()+"," : "") + parent.getId();
            sysOrgan.setPids(pids);
        }
        this.sysOrganService.save(sysOrgan);
        return Result.success();
    }

    /**
     * 修改数据
     *
     * @param sysOrgan 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody SysOrgan sysOrgan) {
        this.sysOrganService.updateOrgan(sysOrgan);
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
        return success(this.sysOrganService.removeByIds(idList));
    }
}
