package com.ybk.controller;

import com.ybk.domain.Product;
import com.ybk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController {


    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product/{pid}")
    public void order(@PathVariable("pid") Integer pid) {
        System.out.println("调用下单服务成功---" + pid);
        List<ServiceInstance> instances = discoveryClient.getInstances("servier-product");
        // ServiceInstance serviceInstance = instances.get(0);
        //  Product forObject = restTemplate.getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + pid, Product.class);
        //  System.out.println("查询到商品信息---" + forObject);

        //ribbon实现负载均衡
        // Product forObject1 = restTemplate.getForObject("http://servier-product/product/" + pid, Product.class);
        //System.out.println("查询到商品信息11---" + forObject1);

        Product product = productService.findProduct(pid);
        System.out.println("查询到商品信息11---" + product);
    }
}
