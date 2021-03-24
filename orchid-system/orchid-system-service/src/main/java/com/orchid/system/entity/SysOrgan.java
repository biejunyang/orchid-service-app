package com.orchid.system.entity;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.orchid.core.model.TreeNode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 组织机构信息表(SysOrgan)表实体类
 *
 * @author makejava
 * @since 2021-03-23 10:23:44
 */
@SuppressWarnings("serial")
public class SysOrgan extends Model<SysOrgan> implements TreeNode {

    private Long id;
    //名称
    private String name;
    //唯一编码
    private String code;
    //备注
    private String remark;
    //所有上级节点的id
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String pids;
    //排序值
    private Integer sort;
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


    @TableField(exist = false)
    private List<SysOrgan> children;

    @TableField(exist = false)
    private Long parentId;


    @Override
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

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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


    public Long getParentId() {
        return this.parentId;
    }

    @Override
    public Object getPid() {
        if(StrUtil.isNotEmpty(this.getPids())){
            String[] strs=this.pids.split(",");
            return Long.valueOf(strs[strs.length-1]);
        }
        return null;
    }

    @Override
    public void setChildren(List children) {
        this.children=children;
    }


    public List getChildren() {
        return this.children;
    }


    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
