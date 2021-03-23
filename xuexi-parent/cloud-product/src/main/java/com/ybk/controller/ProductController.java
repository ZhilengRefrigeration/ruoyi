package com.ybk.controller;

import com.ybk.domain.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @RequestMapping(value = "/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid) {
        System.out.println("调用商品服务成功---" + pid);
        Product product = new Product();
        product.setPid(pid);
        product.setPname("测试商品");
        return product;
    }
}
