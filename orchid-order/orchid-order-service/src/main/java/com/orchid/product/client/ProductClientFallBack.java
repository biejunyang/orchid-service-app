package com.orchid.product.client;

import com.orchid.product.api.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallBack implements ProductClient {
    @Override
    public Product getProduct(Long id) {
        Product product=new Product();
        product.setId(0L);
        product.setName("备选商品");
        return product;
    }
}
