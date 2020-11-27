package com.orchid.product.controller;

import com.orchid.product.api.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/product")
@RestController
public class ProductController {

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) throws InterruptedException {
        Thread.sleep(2000);
        Product product=new Product();
        product.setId(id);
        product.setCode("A000110");
        product.setName("商品1");
        return product;
    }
}
