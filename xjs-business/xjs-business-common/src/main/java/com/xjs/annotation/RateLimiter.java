package com.xjs.annotation;

import com.xjs.enums.InterfaceLimitType;

import java.lang.annotation.*;

/**
 *  接口限流注解
 * @author xiejs
 * @since 2022-05-16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /**
     * 限流key
     */
    String key() default "rate_limit:";

    /**
     * 限流时间,单位秒
     */
    int time() default 60;

    /**
     * 限流次数
     */
    int count() default 100;

    /**
     * 限流类型
     */
    InterfaceLimitType limitType() default InterfaceLimitType.DEFAULT;
}
