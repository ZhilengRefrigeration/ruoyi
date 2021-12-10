package com.ruoyi.common.core.annotation.order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 默认排序
 * 如何使用:
 * @DefaultOrder(column="createTime", orderType="desc", tableName="user")
 * class UserVO {
 *     ...
 * }
 * startPage(UserVO.class)
 * @author ruoyi
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultOrder {
    // 默认排序列 驼峰最终会转换为下划线命名法作为最终排序列名。
    String column() default "";
    // 排序类型, desc/asc
    String orderType() default "asc";
    // 表名, 可不指定
    String tableName() default "";
}