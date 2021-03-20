package com.orchid.system.controller;

import com.orchid.core.Result;
import com.orchid.system.entity.SysPrivilege;
import com.orchid.system.entity.SysUser;
import com.orchid.system.service.SysPrivilegeService;
import com.orchid.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表(SysUser)表控制层
 *
 * @author makejava
 * @since 2021-03-20 12:01:19
 */
@RestController
@RequestMapping("user")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    @Autowired
    private SysPrivilegeService sysPrivilegeService;


    /**
     * 获取用户所有权限列表
     * @param userId
     * @return
     */
    @GetMapping("privileges")
    private Result userPrivilege(@RequestParam("userId") Long userId){
        return Result.success(sysPrivilegeService.tree(new SysPrivilege()));
    }
}