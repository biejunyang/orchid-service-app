package com.orchid.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.orchid.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 系统参数配置表(SysConfig)表实体类
 *
 * @author makejava
 * @since 2021-06-18 14:10:01
 */
@Data
@NoArgsConstructor
@TableName("sys_config")
public class SysConfig extends BaseEntity<SysConfig> {


    //名称
    @NotEmpty(message = "参数名称不能为空!")
    private String name;

    //编码
    @NotEmpty(message = "参数编码不能为空!")
    private String code;

    //值
    @NotEmpty(message = "参数值不能为空!")
    private String value;

    //是否是系统参数（Y-是，N-否）
    private String sysFlag;

    //备注
    private String remark;

    //常量所属分类的编码，来自于“常量的分类”字典
    private String groupCode;


}
