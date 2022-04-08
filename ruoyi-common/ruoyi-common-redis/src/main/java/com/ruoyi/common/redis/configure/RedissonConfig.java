package com.ruoyi.common.redis.configure;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson分布式锁配置
 *
 * @author xiejs
 * @since 2022-04-08
 */
@Configuration
@ConfigurationProperties(prefix = "redisson")
@Data
public class RedissonConfig {

    private String ip;

    private Integer port;


    /**
     * 所有对 Redisson 的使用都是通过 RedissonClient
     *
     * @return RedissonClient
     */
    @Bean(destroyMethod = "shutdown",name = "redissonClient")
    public RedissonClient redissonClient() {
        // 1、创建配置
        Config config = new Config();
        // Redis url should start with redis:// or rediss://
        config.useSingleServer().setAddress("redis://" + ip + ":" + port + "");

        // 2、根据 Config 创建出 RedissonClient 实例
        return Redisson.create(config);
    }


}
