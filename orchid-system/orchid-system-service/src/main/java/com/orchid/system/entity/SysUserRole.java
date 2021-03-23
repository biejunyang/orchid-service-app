package com.orchid.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * 用户角色关联信息表(SysUserRole)表实体类
 *
 * @author makejava
 * @since 2021-03-23 20:35:50
 */
@SuppressWarnings("serial")
public class SysUserRole extends Model<SysUserRole> {
    //主键
    private Long id;
    //用户id
    private Long userId;
    //角色id
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

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
