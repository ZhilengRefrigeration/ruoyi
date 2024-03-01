package com.ruoyi.common.security.utils;

import com.alibaba.fastjson2.JSONArray;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.api.domain.SysDictData;

import java.util.Collection;
import java.util.List;

/**
 * 字典工具类
 *
 * @author ruoyi
 */
public class DictUtils {

    private DictUtils() {
        throw new IllegalStateException("Utility class cannot be instantiated");
    }

    private static RedisService redisService;

    /**
     * 设置字典缓存
     *
     * @param key       参数键
     * @param dictDataList 字典数据列表
     */
    public static void setDictCache(String key, List<SysDictData> dictDataList) {
        if (redisService == null) {
            redisService = SpringUtils.getBean(RedisService.class);
        }
        redisService.setCacheObject(getCacheKey(key), dictDataList);
    }

    /**
     * 获取字典缓存
     *
     * @param key 参数键
     * @return dictDataList 字典数据列表
     */
    public static List<SysDictData> getDictCache(String key) {
        if (redisService == null) {
            redisService = SpringUtils.getBean(RedisService.class);
        }
        JSONArray arrayCache = redisService.getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(arrayCache)) {
            return arrayCache.toList(SysDictData.class);
        }
        return null;
    }

    /**
     * 删除指定字典缓存
     *
     * @param key 字典键
     */
    public static void removeDictCache(String key) {
        if (redisService == null) {
            redisService = SpringUtils.getBean(RedisService.class);
        }
        redisService.deleteObject(getCacheKey(key));
    }

    /**
     * 清空字典缓存
     */
    public static void clearDictCache() {
        if (redisService == null) {
            redisService = SpringUtils.getBean(RedisService.class);
        }
        Collection<String> keys = redisService.keys(CacheConstants.SYS_DICT_KEY + "*");
        redisService.deleteObject(keys);
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return CacheConstants.SYS_DICT_KEY + configKey;
    }
}
