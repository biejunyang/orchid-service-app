package com.orchid.system.entity;

import java.io.Serializable;

/**
 * 用户角色关联信息表(SysUserRole)实体类
 *
 * @author makejava
 * @since 2021-03-20 12:01:19
 */
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 443165873742067893L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 用户id
    */
    private Long userId;
    /**
    * 角色id
    */
    private Long roleId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}