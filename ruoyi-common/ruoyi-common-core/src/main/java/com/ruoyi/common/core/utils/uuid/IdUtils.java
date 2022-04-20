package com.ruoyi.common.core.utils.uuid;

/**
 * ID生成器工具类
 * 
 * @author ruoyi
 */
public class IdUtils
{
    /**
     * 获取随机UUID
     * 
     * @return 随机UUID
     */
    public static String randomuuid()
    {
        return UUID.randomuuid().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleuuid()
    {
        return UUID.randomuuid().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 随机UUID
     */
    public static String fastuuid()
    {
        return UUID.fastuuid().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String fastsimpleuuid()
    {
        return UUID.fastuuid().toString(true);
    }
}
