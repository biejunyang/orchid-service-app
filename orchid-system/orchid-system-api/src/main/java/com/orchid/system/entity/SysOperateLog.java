package com.orchid.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
public class SysOperateLog {

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
    private Integer success;

    //日志详情
    private String message;


    /**
     * ip
     */
    private String ip;

    /**
     * 地址
     */
    private String location;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 类名称
     */
    private String className;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 请求方式（GET POST PUT DELETE)
     */
    private String reqMethod;

    /**
     * 请求参数
     */
    private String param;

    /**
     * 返回结果
     */
    private String result;



}
