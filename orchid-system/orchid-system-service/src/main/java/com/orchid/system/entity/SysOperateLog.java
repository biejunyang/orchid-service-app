package com.orchid.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.orchid.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 操作日志表(SysOperateLog)表实体类
 *
 * @author makejava
 * @since 2021-06-29 14:50:07
 */
@Data
@NoArgsConstructor
@TableName("sys_operate_log")
public class SysOperateLog  {

    private Long id;

    //日志标题
    private String title;

    //操作功能名称(菜单名称)
    private String name;

    //操作类型，如添加，修改，删除
    private String type;

    //操作人
    private String operateUser;

    //操作时间
    private Date operateTime;

    //操作是否成功：0否；1是
    private Object success;

    //日志详情
    private String message;


}
