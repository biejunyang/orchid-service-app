package com.orchid.mall;

import com.orchid.core.log.OperateLog;
import com.orchid.core.log.OperateLogService;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author biejunyang
 * @version 1.0
 * @date 2021/6/29 17:12
 */
@Service
public class SysOperateLogService implements OperateLogService {

    private RestTemplate restTemplate;

    @Override
    public void insertLog(JoinPoint joinPoint, OperateLog operateLog) {
//        restTemplate.postForObject(, , );
    }
}
