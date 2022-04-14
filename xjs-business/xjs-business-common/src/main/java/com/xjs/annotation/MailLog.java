package com.xjs.annotation;

import java.lang.annotation.*;

/**
 * 自定义邮件日志注解
 * @author xiejs
 * @since 2022-04-13
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MailLog {
}
