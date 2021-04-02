package com.orchid.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class SystemServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(SystemServiceApp.class,args);
    }

}
