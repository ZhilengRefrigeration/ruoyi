package com.ruoyi.common.redis.configure;

import cn.hutool.core.util.RandomUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * redis配置
 *
 * @author ruoyi
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private CacheProperties cacheProperties;

    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);

        //解决查询缓存转换异常的问题
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        serializer.setObjectMapper(mapper);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory
                                                  redisConnectionFactory) {
        // 分别创建String和JSON格式序列化对象，对缓存数据key和value进行转换
        RedisSerializer<String> strSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
        // 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);
        // 定制缓存数据序列化方式及时效

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(strSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jacksonSeial));

        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        //将配置文件中所有的配置都生效
        if (redisProperties.getTimeToLive() != null) {
            //定义随机时间值，防止缓存大量过期
            long salt = RandomUtil.randomLong(10, 100);
            Duration time = redisProperties.getTimeToLive();
            Duration plusSeconds = time.plusSeconds(salt);
            config = config.entryTtl(plusSeconds);
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }

        //单独给某个定义过期时间
        Set<String> cacheNames = new HashSet<>();
        cacheNames.add("bussiness:index:wb_search");
        cacheNames.add("bussiness:index:log_count");
        cacheNames.add("bussiness:index:oper_log");
        ConcurrentHashMap<String, RedisCacheConfiguration> configMap = new ConcurrentHashMap<>();
        configMap.put("bussiness:index:wb_search", config.entryTtl(Duration.ofMinutes(10L)));
        configMap.put("bussiness:index:log_count", config.entryTtl(Duration.ofMinutes(10L)));
        configMap.put("bussiness:index:oper_log", config.entryTtl(Duration.ofSeconds(10L)));

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(config)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .build();
    }


}
