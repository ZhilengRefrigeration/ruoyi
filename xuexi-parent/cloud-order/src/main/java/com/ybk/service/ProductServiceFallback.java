package com.ybk.service;

import com.ybk.domain.Product;
import org.springframework.stereotype.Service;

/**
 *容错类
 */
@Service
public class ProductServiceFallback  implements  ProductService{

    @Override
    public Product findProduct(Integer pid) {
        //容错逻辑
        Product product = new Product();
        return product;
    }
}
