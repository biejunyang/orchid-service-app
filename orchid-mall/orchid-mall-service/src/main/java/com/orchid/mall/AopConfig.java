package com.orchid.mall;

import com.orchid.core.log.OperateLogAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author biejunyang
 * @version 1.0
 * @date 2021/6/29 17:12
 */
@Configuration
public class AopConfig {


    @Bean
    OperateLogAop operateLogAop(){
        return new OperateLogAop(null);
    }
}
