package com.orchid.system.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orchid.core.Result;
import com.orchid.mybatis.util.AssertUtils;
import com.orchid.system.entity.SysUser;
import com.orchid.system.service.SysUserService;
import com.orchid.system.vo.UserVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用户信息表(SysUser)表控制层
 *
 * @author makejava
 * @since 2021-03-23 20:35:48
 */
@RestController
@RequestMapping("user")
public class SysUserController extends ApiController {

    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;


    /**
     * 查询数据列表
     *
     * @param sysUser
     * @return
     */
    @GetMapping
    public Result find(SysUser sysUser) {
        return Result.success(sysUserService.findUsers(sysUser));
    }


    /**
     * 查询分页列表
     *
     * @param page
     * @param size
     * @param sysUser
     * @return
     */
    @GetMapping("{page}/{size}")
    public Result findPage(@PathVariable Integer page, @PathVariable Integer size, SysUser sysUser) {
        Page<SysUser> pager=new Page<>(page,size);
        return Result.success(sysUserService.findUsers(pager, sysUser));
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result findById(@PathVariable Serializable id) {
        SysUser user=this.sysUserService.getById(id);
        if(user == null) {
            return Result.success();
        }
        user.setPassword("N/A");
        user.setRoles(sysUserService.userRoles(user));
        user.setPrivileges(sysUserService.userPrivileges(user));
        return Result.success(user);
    }


    /**
     * 新增数据
     *
     * @param sysUser 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody SysUser sysUser) {
        AssertUtils.columnNotUsed(sysUserService.getBaseMapper(), sysUser, "用户名", SysUser::getUsername);
        sysUser.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(sysUser.getPassword()));
        return Result.success(this.sysUserService.save(sysUser));
    }

    /**
     * 修改数据
     *
     * @param sysUser 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody SysUser sysUser) {
        SysUser oldUser=sysUserService.getById(sysUser.getId());
        AssertUtils.columnNotUsed(sysUserService.getBaseMapper(), sysUser, "用户名", SysUser::getUsername,oldUser);
        return Result.success(this.sysUserService.updateById(sysUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        sysUserService.deleteUsers(idList);
        return Result.success();
    }


    /**
     * 获取用户关联的角色
     * @param id
     * @return
     */
    @GetMapping("roles")
    public Result userRoles(Long id){
        SysUser user=sysUserService.getById(id);
        return Result.success(sysUserService.userRoles(user));
    }


    /**
     * 获取用户所有权限列表
     * @param userId
     * @return
     */
    @GetMapping("privileges")
    public Result userPrivilege(@RequestParam("userId") Long userId){
        return Result.success();
    }


    /**
     * 角色授权
     * @param userVo
     * @return
     */
    @PostMapping("grantRole")
    public Result grantRole(@RequestBody UserVo userVo){
        sysUserService.grantRole(userVo);
        return Result.success();
    }

    /**
     * 获取当前认证信息
     * @return
     */
    @GetMapping("/userInfo3")
    @ResponseBody
    public Object userInfo3(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

}
