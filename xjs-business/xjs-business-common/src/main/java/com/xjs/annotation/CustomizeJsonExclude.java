package com.xjs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解加在属性上，如果属性的值为空，则该属性不进行序列化返回
 * @author xiejs
 * @since 2022-03-16
 */
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomizeJsonExclude {
}
