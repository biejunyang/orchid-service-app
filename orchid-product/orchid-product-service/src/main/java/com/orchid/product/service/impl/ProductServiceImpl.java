package com.orchid.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchid.product.dao.ProductDao;
import com.orchid.product.entity.Product;
import com.orchid.product.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * (Product)表服务实现类
 *
 * @author makejava
 * @since 2021-01-07 14:05:11
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {

}