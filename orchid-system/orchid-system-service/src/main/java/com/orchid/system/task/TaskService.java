package com.orchid.system.task;

import com.orchid.system.entity.SysOperateLog;
import com.orchid.system.service.SysOperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步任务
 * @author biejunyang
 * @version 1.0
 * @date 2021/7/8 16:32
 */
@Slf4j
@Service
public class TaskService {

    @Autowired
    private SysOperateLogService sysOperateLogService;


    /**
     * 日志异步写入
     * 注意开启：@EnableAsync异步任务
     * @param sysOperateLog
     */
    @Async
    public void insertLog(SysOperateLog sysOperateLog) {
        sysOperateLogService.save(sysOperateLog);
    }
}
