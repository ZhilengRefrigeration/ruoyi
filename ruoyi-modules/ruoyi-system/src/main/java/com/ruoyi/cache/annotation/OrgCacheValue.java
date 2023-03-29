/**
 * 
 */
package com.ruoyi.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 获取组织架构的缓存，声明一个pojo的属性映射值
 * @author Condy
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface OrgCacheValue {
	//CacheValueNum value() default CacheStaffInfoNum.staffId;
	String value() default "";
	String id() default "user";

}
