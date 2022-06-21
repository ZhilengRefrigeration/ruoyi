package com.xjs.annotation;

import com.xjs.enums.SensitiveTypeEnum;

import java.lang.annotation.*;

/**
 * 数据脱敏注解
 * @author xiejs
 * @since 2022-06-21
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Desensitized {
    //    脱敏类型(规则)
    SensitiveTypeEnum value();
}
