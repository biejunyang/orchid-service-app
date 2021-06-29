package com.orchid.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchid.core.auth.AuthContextHolder;
import com.orchid.core.log.OperateLog;
import com.orchid.core.log.OperateLogService;
import com.orchid.system.dao.SysOperateLogDao;
import com.orchid.system.entity.SysOperateLog;
import com.orchid.system.service.SysOperateLogService;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 操作日志表(SysOperateLog)表服务实现类
 *
 * @author makejava
 * @since 2021-06-29 14:50:07
 */
@Service("sysOperateLogService")
public class SysOperateLogServiceImpl extends ServiceImpl<SysOperateLogDao, SysOperateLog> implements SysOperateLogService, OperateLogService {


    @Override
    public void insertLog(JoinPoint joinPoint, OperateLog operateLog) {

        SysOperateLog sysOperateLog=new SysOperateLog();
        sysOperateLog.setTitle(operateLog.title());
        sysOperateLog.setName(operateLog.name());
        sysOperateLog.setType(operateLog.type());
        sysOperateLog.setOperateUser(AuthContextHolder.getContext().getUsername());
        sysOperateLog.setOperateTime(new Date());
//        sysOperateLog.setMessage(getMessage(operateLog, args, result, username));
        this.save(sysOperateLog);
    }


}
