package com.xjs.annotation;

import java.lang.annotation.*;

/**
 * 服务之间的调用注解
 * @author xiejs
 * @since 2022-01-13
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RpcLog {
    /**
     * 请求服务方法
     * @return String
     */
    String method() default "";

    /**
     * 请求服务名称
     * @return String
     */
    String serviceName() default "";
}
