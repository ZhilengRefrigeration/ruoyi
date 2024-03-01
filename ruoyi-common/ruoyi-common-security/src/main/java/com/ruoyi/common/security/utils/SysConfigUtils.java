package com.ruoyi.common.security.utils;

import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.redis.service.RedisService;

import java.util.Collection;

/**
 * 系统参数工具类 (sys_config)
 *
 * @author Alan Scipio
 * created on 2024/3/1
 */
public class SysConfigUtils {

    private SysConfigUtils() {
        throw new IllegalStateException("Utility class cannot be instantiated");
    }

    private static RedisService redisService;

    /**
     * 设置参数缓存
     *
     * @param configKey   参数键
     * @param configValue 参数值
     */
    public static void setConfigCache(String configKey, String configValue) {
        if (redisService == null) {
            redisService = SpringUtils.getBean(RedisService.class);
        }
        redisService.setCacheObject(getCacheKey(configKey), configValue);
    }

    /**
     * 获取参数缓存
     *
     * @param configKey 参数键
     * @return configValue 参数值
     */
    public static String getConfigCache(String configKey) {
        if (redisService == null) {
            redisService = SpringUtils.getBean(RedisService.class);
        }
        return redisService.getCacheObject(getCacheKey(configKey));
    }

    /**
     * 删除指定参数缓存
     *
     * @param key 字典键
     */
    public static void removeConfigCache(String key) {
        if (redisService == null) {
            redisService = SpringUtils.getBean(RedisService.class);
        }
        redisService.deleteObject(getCacheKey(key));
    }

    /**
     * 清空所有参数缓存
     */
    public static void clearConfigCaches() {
        if (redisService == null) {
            redisService = SpringUtils.getBean(RedisService.class);
        }
        Collection<String> keys = redisService.keys(CacheConstants.SYS_CONFIG_KEY + "*");
        redisService.deleteObject(keys);
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return CacheConstants.SYS_CONFIG_KEY + configKey;
    }
}
