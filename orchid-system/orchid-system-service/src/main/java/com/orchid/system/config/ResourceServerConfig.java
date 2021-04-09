package com.orchid.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        super.configure(resources);
        resources.tokenStore(tokenStore());
    }


    @Bean
    public TokenStore tokenStore(){
        RedisTokenStore tokenStore=new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix("orchid:auth-server");
        return tokenStore;
    }
}
