package com.orchid.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orchid.core.Result;
import com.orchid.core.auth.AuthContext;
import com.orchid.core.auth.AuthContextHolder;
import com.orchid.core.util.TreeUtil;
import com.orchid.system.entity.SysPrivilege;
import com.orchid.system.entity.SysRole;
import com.orchid.system.entity.SysUser;
import com.orchid.system.service.SysRoleService;
import com.orchid.system.service.SysUserService;
import com.orchid.system.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private SysRoleService roleService;


    @Autowired
    private SysUserService userService;


    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param sysRole 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<SysRole> page, SysRole sysRole) {
        return Result.success(this.roleService.page(page, new QueryWrapper<>(sysRole).orderByDesc("id")));
    }




    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.roleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysRole 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody SysRole sysRole) {
        roleService.save(sysRole);
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
        this.roleService.updateById(sysRole);
        return Result.success();
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        roleService.deleteByIds(idList);
        return Result.success();
    }


    /**
     * 获取角色拥有的权限
     *
     * @param roleId
     * @return
     */
    @GetMapping("editPrivileges")
    public Result editPrivileges(@RequestParam("roleId") Long roleId){
        SysUser sysUser=new SysUser();
        sysUser.setId(AuthContextHolder.getContext().getLoginUser().getId());
        sysUser.setUsername(AuthContextHolder.getContext().getLoginUser().getUsername());
        sysUser.setAdminType(AuthContextHolder.getContext().getLoginUser().getAdminType());

        //当前修改用户拥有权限(2)
        List<SysPrivilege> userPrivileges=userService.userPrivileges(sysUser);

        //修改角色拥有权限(4)
        List<SysPrivilege> rolePrivileges=roleService.rolePrivileges(roleId);

        //取并集获取所有可展示的权限列表
        List<SysPrivilege> allPriveleges=new ArrayList<>(userPrivileges);
        allPriveleges.removeAll(rolePrivileges);
        allPriveleges.addAll(rolePrivileges);

        allPriveleges.forEach(item -> {
            if(!userPrivileges.contains(item)){//用户没有权限修改
                item.setDisabled(1);
            }
        });
        Map<String, Object> resultMap=new HashMap<>();
        resultMap.put("allPriveleges", TreeUtil.buildTree(allPriveleges));
        resultMap.put("checkedIds", rolePrivileges.parallelStream().map(SysPrivilege::getId).collect(Collectors.toList()));
        return Result.success(resultMap);
    }

    /**
     * 角色授权
     * @return
     */
    @PostMapping("grant")
    public Result grantPrivileges(@RequestBody RoleVo roleVo){
        roleService.grantPrivileges(roleVo.getRoleId(), roleVo.getPrivilegeIds());
        return Result.success();
    }
}
