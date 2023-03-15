package com.ruoyi.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.util.RedisNotifyService;
import com.ruoyi.util.SpringLoadComplete;

@Configuration
@EnableCaching
@Component
public class CacheConfigurer {
	@Autowired
	private RedisService redisService;
	@Bean
	public SpringLoadComplete getSpringLoadComplete() {
		return new SpringLoadComplete();
	}
	@Bean
	public RedisNotifyService getRedisNotifyService() {
		RedisNotifyService notifyService= new RedisNotifyService(redisService);
		return notifyService;
	}

}
