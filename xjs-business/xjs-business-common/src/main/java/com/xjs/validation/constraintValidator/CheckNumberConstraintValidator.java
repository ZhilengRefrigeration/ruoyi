package com.xjs.validation.constraintValidator;

import com.xjs.validation.annotation.CheckNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * 数字长度约束验证器
 * @author xiejs
 * @since 2022-02-18
 */
public class CheckNumberConstraintValidator implements ConstraintValidator<CheckNumber,Object> {

    private int[] num;

    @Override
    public void initialize(CheckNumber constraintAnnotation) {
        this.num = constraintAnnotation.num();
    }

    /**
     *  校验
     * @param value 属性值
     * @param context context
     * @return boolean
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if (Objects.nonNull(value)) {

            if (value instanceof Integer) {
                int val =(int) value;
                for (int i : num) {
                    if (val == i) {
                        return true;
                    }
                }
            }

            if (value instanceof Long) {
                long val =(long) value;
                for (int i : num) {
                    if (val == i) {
                        return true;
                    }
                }
            }


            return false;
        }

        //为空则直接过
        return true;
    }
}
