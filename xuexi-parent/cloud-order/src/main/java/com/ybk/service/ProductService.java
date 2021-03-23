package com.ybk.service;

import com.ybk.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "servier-product",
        fallback = ProductServiceFallback.class)
public interface ProductService {

    @RequestMapping("/product/{pid}")
    Product findProduct(@PathVariable("pid") Integer pid);
}
