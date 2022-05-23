package com.xjs.service;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author xiejs
 * @since 2022-05-23
 */
@SPI("human")
public interface HelloService {

    String sayHello();

    @Adaptive
    String sayHello(URL url);
}
