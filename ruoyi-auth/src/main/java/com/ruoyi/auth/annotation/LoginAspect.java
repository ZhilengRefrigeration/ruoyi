package com.ruoyi.auth.annotation;

import java.lang.annotation.*;

/**
 * 登录切面注解
 * @author xiejs
 * @since 2022-04-14
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginAspect {
}
