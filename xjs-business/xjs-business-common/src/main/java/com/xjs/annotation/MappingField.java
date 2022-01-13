package com.xjs.annotation;

import java.lang.annotation.*;

/**
 * 映射字段（tip: 1 = 成功）
 * @author xiejs
 * @since 2022-01-13
 */
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MappingField {

    /**
     * 字段本身具有的值
     * @return string
     */
    String key() default "";

    /**
     * 需要转换的值
     * @return string
     */
    String value() default "";
}
