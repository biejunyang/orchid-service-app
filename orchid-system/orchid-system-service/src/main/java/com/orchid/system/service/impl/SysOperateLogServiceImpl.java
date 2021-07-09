package com.orchid.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchid.core.auth.AuthContextHolder;
import com.orchid.core.log.OperateLog;
import com.orchid.core.log.OperateLogService;
import com.orchid.system.dao.SysOperateLogDao;
import com.orchid.system.entity.SysOperateLog;
import com.orchid.system.factory.LogFactory;
import com.orchid.system.service.SysOperateLogService;
import com.orchid.system.task.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

/**
 * 操作日志表(SysOperateLog)表服务实现类
 *
 * @author makejava
 * @since 2021-06-29 14:50:07
 */
@Slf4j
@Service("sysOperateLogService")
public class SysOperateLogServiceImpl extends ServiceImpl<SysOperateLogDao, SysOperateLog> implements SysOperateLogService, OperateLogService {


    @Autowired
    private TaskService taskService;


    /**
     * 操作日志统一写入
     * @param operateLog
     * @param joinPoint
     * @param result
     */
    @Override
    public void insertOperateLog(OperateLog operateLog, JoinPoint joinPoint, Object result) {

        SysOperateLog sysOperateLog= LogFactory.createBaseLog(operateLog, joinPoint);
        sysOperateLog.setSuccess(1);
        sysOperateLog.setResult(JSON.toJSONString(result));

        //异步写入日志信息
        taskService.insertLog(sysOperateLog);
    }


    /**
     * 异常操作日志统一写入
     * @param operateLog
     * @param joinPoint
     * @param exception
     */
    @Override
    public void insertExceptionLog(OperateLog operateLog, JoinPoint joinPoint, Exception exception) {
        SysOperateLog sysOperateLog= LogFactory.createBaseLog(operateLog, joinPoint);
        log.info("current Thread: {}", Thread.currentThread().getName());
        sysOperateLog.setSuccess(0);
        sysOperateLog.setResult(Arrays.toString(exception.getStackTrace()));

        //异步写入日志信息
        taskService.insertLog(sysOperateLog);
    }
}
