package com.orchid.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@SpringBootApplication
@RestController
public class SystemServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(SystemServiceApp.class,args);
    }


    @GetMapping("/userInfo")
    public Object userInfo(AbstractAuthenticationToken authenticationToken){
        return authenticationToken;
    }

}
