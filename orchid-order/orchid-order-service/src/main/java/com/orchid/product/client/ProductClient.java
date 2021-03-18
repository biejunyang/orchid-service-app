package com.orchid.product.client;


import com.orchid.product.api.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "orchid-product", fallback = ProductClientFallBack.class)
public interface ProductClient {

    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable("id") Long id);

}
