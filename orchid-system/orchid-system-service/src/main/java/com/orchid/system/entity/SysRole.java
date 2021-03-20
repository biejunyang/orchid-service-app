package com.orchid.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色信息表(SysRole)表实体类
 *
 * @author makejava
 * @since 2021-03-18 16:20:46
 */
@SuppressWarnings("serial")
public class SysRole extends Model<SysRole> {

    private Long id;
    //角色名称
    private String name;
    //角色唯一编码
    private String code;
    //备注
    private String remark;
    //禁用标识 （0：启用；1：禁用；）
    private Object disabled;
    //创建时间
    private Date createTime;
    //创建人
    private String createUser;

    private String createClient;
    //更新时间
    private Date updateTime;
    //更新人
    private String updateUser;

    private String updateClient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Object getDisabled() {
        return disabled;
    }

    public void setDisabled(Object disabled) {
        this.disabled = disabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateClient() {
        return createClient;
    }

    public void setCreateClient(String createClient) {
        this.createClient = createClient;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateClient() {
        return updateClient;
    }

    public void setUpdateClient(String updateClient) {
        this.updateClient = updateClient;
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
