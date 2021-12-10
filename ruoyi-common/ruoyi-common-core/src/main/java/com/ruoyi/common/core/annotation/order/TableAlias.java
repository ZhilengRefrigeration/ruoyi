package com.ruoyi.common.core.annotation.order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段表别名
 * 如何使用:
 * class UserVO {
 *     ...
 *     @TableAlias("dept")
 *     private String deptName;
 *     ...
 * }
 * startPage(UserVO.class)
 * 查询多表关联时, 指定表别名, 即可简单实现完成对应的字段排序。
 * @author ruoyi
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableAlias {
    String value() default "";
}