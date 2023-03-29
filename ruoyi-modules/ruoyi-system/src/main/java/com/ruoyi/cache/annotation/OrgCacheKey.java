package com.ruoyi.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 缓存的关键字ID
 * @author Administrator
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface OrgCacheKey {
	String id() default "user";
	/**
	 * 缓存的类型:目前可以是CacheStaffInfo,CacheDeptInfo,CacheCompanyInfo
	 * @return
	 */
	OrgCacheTypeNum type() default OrgCacheTypeNum.CacheUserInfo;

}
