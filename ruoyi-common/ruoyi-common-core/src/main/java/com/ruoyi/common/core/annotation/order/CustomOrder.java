package com.ruoyi.common.core.annotation.order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义排序
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
@Repeatable(CustomOrders.class)
public @interface CustomOrder {
    // 列名 驼峰最终会转换为下划线命名法作为最终排序列名。
    String column() default "";

    // 列对应的表名
    String tableName() default "";
}