package com.xjs.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author xiejs
 * @desc  状态类型枚举 需要返回什么参数就在toString实现 这个枚举需要mp在配置文件中配置扫描路径
 * @create 2021-12-27
 */
public enum StatusEnum {

    SUCCESS(1,"成功"),
    ERROR(2,"失败");


    private String desc;

    @EnumValue//标记数据库存的值是value
    @JsonValue
    private final int value;

    StatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
