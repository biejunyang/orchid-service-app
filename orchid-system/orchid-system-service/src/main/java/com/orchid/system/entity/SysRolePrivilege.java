package com.orchid.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (SysRolePrivilege)表实体类
 *
 * @author makejava
 * @since 2021-03-18 16:20:44
 */
@SuppressWarnings("serial")
public class SysRolePrivilege extends Model<SysRolePrivilege> {

    private Long id;

    private Long roleId;

    private Long privilegeId;

    public SysRolePrivilege() {
    }

    public SysRolePrivilege(Long roleId, Long privilegeId) {
        this.roleId = roleId;
        this.privilegeId = privilegeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
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
