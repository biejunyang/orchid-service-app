package com.orchid.product.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;

/**
 * (Product)表实体类
 *
 * @author makejava
 * @since 2021-01-07 14:05:05
 */
@SuppressWarnings("serial")
@TableName("product")
public class Product extends Model<Product> {

    @TableId(value="id", type = IdType.ASSIGN_ID)
    private Long id;
    
    
@TableField("name")
    private String name;
    
    
@TableField("price")
    private Double price;
    
    
@TableField("shop_id")
    private Long shopId;
    
    
@TableField("shop_name")
    private String shopName;
    
    
@TableField("type_ids")
    private String typeIds;
    
    
@TableField("type_names")
    private String typeNames;
    


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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(String typeIds) {
        this.typeIds = typeIds;
    }

    public String getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(String typeNames) {
        this.typeNames = typeNames;
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