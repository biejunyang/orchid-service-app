package com.orchid.system;

import com.alibaba.fastjson.JSON;
import com.orchid.core.auth.AuthContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SystemServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(SystemServiceApp.class,args);
    }
//
//
    @Autowired
    private AuthContext authContext;
//
////    @PreAuthorize("hasAuthority('menxuqd2')")
    @GetMapping("/userInfo")
    public Object userInfo(AbstractAuthenticationToken authenticationToken){

        System.out.println(authenticationToken);
        System.out.println(authenticationToken.getPrincipal());

        System.out.println(authContext);
        System.out.println(authContext.getAuthentication());
        System.out.println(authContext.getLoginUser());
        System.out.println(JSON.toJSONString(authContext.getLoginUser()));
        System.out.println(authContext.getAuthoritys());
        System.out.println(authContext.getClientId());
        System.out.println(authContext.getRoles());
        System.out.println(authContext.getUsername());
        System.out.println(authContext.isSuperAdmin());
        return authenticationToken.getPrincipal();
    }







}
