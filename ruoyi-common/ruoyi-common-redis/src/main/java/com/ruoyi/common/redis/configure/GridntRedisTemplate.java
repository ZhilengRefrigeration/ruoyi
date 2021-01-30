package com.ruoyi.common.redis.configure;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 自定义RedisTemplate，支持选库
 *
 * @author gxx
 */
@Component
public class GridntRedisTemplate extends StringRedisTemplate {

    public GridntRedisTemplate(RedisConnectionFactory connectionFactory) {
        super.setConnectionFactory(connectionFactory);
    }

    public static ThreadLocal<Integer> REDIS_DB_INDEX = new ThreadLocal<>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        try {
            Integer dbIndex = REDIS_DB_INDEX.get();
            if (dbIndex != null) {
                if (connection instanceof JedisConnection) {
                    if (((JedisConnection) connection).getNativeConnection().getDB() != dbIndex) {
                        connection.select(dbIndex);
                    }
                } else {
                    connection.select(dbIndex);
                }
            } else {
                connection.select(0);
            }
        } finally {
            //REDIS_DB_INDEX.remove();
        }
        return super.preProcessConnection(connection, existingConnection);
    }
}
