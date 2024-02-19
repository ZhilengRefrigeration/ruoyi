package com.ruoyi.common.core.constant;

import java.util.NoSuchElementException;

/**
 * @author Alan Scipio
 * created on 2024/2/18
 */
public interface IEnum {

    int getCode();

    String getName();

    static IEnum getByCode(IEnum[] enums, int code) {
        if (enums == null || enums.length == 0) {
            throw new IllegalArgumentException("enums is empty");
        }
        for (IEnum value : enums) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new NoSuchElementException("No such enum with code: [" + code + "], enum type: [" + enums[0].getClass().getName() + "]");
    }

    static IEnum getByName(IEnum[] enums, String name) {
        if (enums == null || enums.length == 0) {
            throw new IllegalArgumentException("enums is empty");
        }
        for (IEnum value : enums) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        throw new NoSuchElementException("No such enum with name: [" + name + "], enum type: [" + enums[0].getClass().getName() + "]");
    }

}
