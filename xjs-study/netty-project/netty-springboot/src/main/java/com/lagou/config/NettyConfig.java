package com.lagou.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @since 2022-03-09
 */
@Component
@ConfigurationProperties(prefix = "netty")
@Data
public class NettyConfig {

    //netty监听的端口
    private int port ;

    //websocket访问路径
    private String path;

}
