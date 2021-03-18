package com.orchid.product.controller;

import cn.hutool.core.util.StrUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.orchid.product.api.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/product")
@RestController
public class ProductController {

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        Product product=new Product();
        product.setId(id);
        product.setCode("A000110");
        product.setName("商品1");
        return product;
    }

    @HystrixCommand(fallbackMethod = "helloFail")
    @GetMapping("/hello")
    public String hello(String name){
        if(StrUtil.isEmpty(name)){
            throw new RuntimeException("xxxxx");
        }
        return "<h1>hello</hello>";
    }

    public String helloFail(String name){
        return "<h1>hello, failed!!!</h1>";
    }
}
