package com.xjs.enums;

/**
 * @author xiejs
 * @desc  预警等级枚举
 * @create 2022-01-07
 */
public enum WarnLevelEnum {
    /* 普通 **/
    NOEMAL("普通"),
    /* 警告 **/
    WARNING("警告"),
    /* 严重 **/
    DANGER("严重");

    private final String message;

    WarnLevelEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
