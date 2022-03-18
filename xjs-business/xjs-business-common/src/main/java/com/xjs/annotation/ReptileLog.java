package com.xjs.annotation;

import java.lang.annotation.*;

/**
 * 自定义爬虫日志注解
 * @author xiejs
 * @since 2022-02-17
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReptileLog {

    /**
     * 爬虫名称
     */
    String name() default "";

    /**
     * 请求url(当该值为""时，会默认反射获取形参的String值作为url)
     */
    String url() default "";

}
