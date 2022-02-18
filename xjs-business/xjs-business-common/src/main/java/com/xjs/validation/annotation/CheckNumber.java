package com.xjs.validation.annotation;

/**
 * 自定义校验注解 ：检查数字类型长度
 * @author xiejs
 * @since 2022-02-18
 */

import com.xjs.validation.constraintValidator.CheckNumberConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CheckNumberConstraintValidator.class })
public @interface CheckNumber {

    /**
     * 描述信息
     * @return str
     */
    String message() default "数值在指定范围之外！！！";

    /**
     * 校验数字  tip:  [1,2]
     * @return int
     */
    int [] num();

    Class<?>[] groups() default {};

    Class<? extends Payload>[]  payload() default {};

}
