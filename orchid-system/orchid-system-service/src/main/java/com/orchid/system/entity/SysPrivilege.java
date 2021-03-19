package com.orchid.system.entity;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.orchid.core.factory.TreeNode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统权限信息表(SysPrivilege)表实体类
 *
 * @author makejava
 * @since 2021-03-18 16:20:45
 */
@SuppressWarnings("serial")
public class SysPrivilege extends Model<SysPrivilege> implements TreeNode {

    private Long id;
    //名称
    private String name;
    //唯一编码
    private String code;
    //类型：1菜单目录；2菜单；3按钮
    private Object type;
    //url
    private String url;
    //所有上级节点的编码;
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
    private List<SysPrivilege> children;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
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

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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


    @Override
    public Long getPid() {
        if(StrUtil.isNotEmpty(this.getPids())){
            String[] strs=this.pids.split(",");
            return Long.valueOf(strs[strs.length-1]);
        }
        return null;
    }

    @Override
    public List getChildren() {
        return children;
    }

    @Override
    public void setChildren(List children) {
        this.children=children;
    }
}
