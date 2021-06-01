package com.orchid.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息表(SysUser)表实体类
 *
 * @author makejava
 * @since 2021-03-23 20:35:47
 */
@SuppressWarnings("serial")
public class SysUser extends Model<SysUser> {
    //id
    private Long id;
    //账号
    private String username;
    //密码
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    //姓名
    private String realName;
    //昵称
    private String nickName;
    //出生日期
    private Date birthday;
    //性别：1男，2女，3不明
    private Integer sex;
    //邮箱
    private String email;
    //手机号
    private String phone;
    //用户类型：1系统用户
    private Object type;
    //组织机构id
    private Long organId;
    //职位id
    private Long positionId;
    //备注
    private String remark;
    //禁用标识 （0：启用；1：禁用；）
    private Integer disabled;
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

    @TableField(exist = false)
    private String organName;
    /**
     * 管理员类型（0超级管理员 1非管理员）
     */
    private Integer adminType;

    //角色信息
    @TableField(exist = false)
    private List<SysRole> roles;

    //权限信息
    @TableField(exist = false)
    private List<SysPrivilege> privileges;


    //关键字查询,姓名，账号，手机号
    @TableField(exist = false)
    private String keyword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Long getOrganId() {
        return organId;
    }

    public void setOrganId(Long organId) {
        this.organId = organId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
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

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public Integer getAdminType() {
        return adminType;
    }

    public void setAdminType(Integer adminType) {
        this.adminType = adminType;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public List<SysPrivilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<SysPrivilege> privileges) {
        this.privileges = privileges;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
