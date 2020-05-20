package com.ruoyi.common.security.annotation;

import java.lang.annotation.*;

/**
 * 服务调用不鉴权注解
 * 
 * @author ruoyi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner
{
    /**
     * 是否AOP统一处理
     */
    boolean value() default true;

    /**
     * 需要特殊判空的字段(预留)
     */
    String[] field() default {};
}
