package com.xjs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author xiejs
 * @since 2022-05-25
 */
@RestController
@RequestMapping("time")
public class TimeController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String getTime() {

        List<ServiceInstance> instances = discoveryClient.getInstances("EUREKA-CLIENT-PROVIDER");

        ServiceInstance serviceInstance = instances.get(0);

        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();

        //获取生产者定义的元数据
        Map<String, String> metadata = serviceInstance.getMetadata();
        String now = metadata.get("now");
        System.out.println(now);

        String url = "http://" + host + ":" + port + "/time";

        return restTemplate.getForObject(url, String.class);

    }
}
