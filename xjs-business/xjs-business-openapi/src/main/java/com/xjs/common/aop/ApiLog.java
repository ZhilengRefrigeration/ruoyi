package com.xjs.common.aop;

import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.log.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @author xiejs
 * @desc 自定义api日志注解
 * @create 2021-12-26
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiLog {
    /**
     * api名称
     */
    public String name() default "";

    /**
     * 请求url
     */
    public String url() default "";

    /**
     * 请求方法
     */
    public String method() default "Post";
}
