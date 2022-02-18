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
public class CheckNumberConstraintValidator implements ConstraintValidator<CheckNumber,Integer> {

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
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        if (Objects.nonNull(value)) {
            for (int i : num) {
                if (value == i) {
                    return true;
                }
            }
            return false;
        }

        //为空则直接过
        return true;
    }
}
