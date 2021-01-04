package com.orchid.product.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.orchid.product.api.entity.Product;
import com.orchid.product.client.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@EnableFeignClients
@EnableCircuitBreaker
public class OrderController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/{id}")
    public Object findById(@PathVariable("id") Long id){
        Product product=productClient.getProduct(id);
        return product;
    }

    public Object findByIdFail(Long id){
        System.out.println("findByIdFailxxxxxxxxxxxxxxxxx");
        return "查看失败";
    }




    @HystrixCommand(fallbackMethod = "helloFail",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000" )
    })
    @GetMapping("/hello")
    public String hello(String id) throws InterruptedException {
//        Thread.sleep(5000);
        if(id==null){
            throw new RuntimeException("xxxxx");
        }
        return "<h1>hello,"+id+"</h1>";
    }



    public String helloFail(String id) {
        return "<h1>hello，failed</h1>";
    }


    @GetMapping("/welcome")
    public String welcome(){
        return "<h1>welcome</h1>";
    }


}
