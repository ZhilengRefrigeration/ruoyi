package com.xjs.job.aop;

import java.lang.annotation.*;

/**
 * 任务日志注解
 * @author xiejs
 * @since 2022-03-01
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskLog {
    /**
     * 任务名称
     */
    public String name() default "";
}
