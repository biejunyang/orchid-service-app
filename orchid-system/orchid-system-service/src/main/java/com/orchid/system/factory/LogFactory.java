package com.orchid.system.factory;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.orchid.core.auth.AuthContextHolder;
import com.orchid.core.log.OperateLog;
import com.orchid.system.entity.SysOperateLog;
import com.orchid.web.util.HttpUtil;
import com.orchid.web.util.IpAddressUtil;
import com.orchid.web.util.JoinPointUtil;
import com.orchid.web.util.UaUtil;
import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 操作日志创建工厂
 * @author biejunyang
 * @version 1.0
 * @date 2021/7/8 14:40
 */
public class LogFactory {


    public static SysOperateLog createBaseLog(OperateLog operateLog, JoinPoint joinPoint){
        SysOperateLog sysOperateLog=new SysOperateLog();
        sysOperateLog.setName(operateLog.name());
        sysOperateLog.setType(operateLog.type());
        if(StrUtil.isNotEmpty(operateLog.title())){
            sysOperateLog.setTitle(operateLog.title());
        }else {
            sysOperateLog.setTitle(operateLog.name()+"-"+operateLog.type());
        }
        sysOperateLog.setOperateUser(AuthContextHolder.getContext().getUsername());
        sysOperateLog.setOperateTime(new Date());


        String className = joinPoint.getTarget().getClass().getName();

        String methodName = joinPoint.getSignature().getName();

        String param = JoinPointUtil.getArgsJsonString(joinPoint);

        sysOperateLog.setClassName(className);
        sysOperateLog.setMethodName(methodName);
        sysOperateLog.setParam(param);

        HttpServletRequest request = HttpUtil.getRequest();
        if (ObjectUtil.isNotNull(request)) {
            sysOperateLog.setIp(IpAddressUtil.getIp(request));
            sysOperateLog.setLocation(IpAddressUtil.getAddress(sysOperateLog.getIp()));
            sysOperateLog.setBrowser(UaUtil.getBrowser(request));

            sysOperateLog.setOs(UaUtil.getOs(request));
            sysOperateLog.setUrl(request.getRequestURI());
            sysOperateLog.setReqMethod(request.getMethod());
        }

        return sysOperateLog;
    }
}
