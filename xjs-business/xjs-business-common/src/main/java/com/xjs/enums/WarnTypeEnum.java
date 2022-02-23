package com.xjs.enums;

/**
 * 预警类型枚举
 * @author xiejs
 * @since  2022-01-07
 */
public enum WarnTypeEnum {

    API("API","API接口(%s)调用超出限定值。限定值--%s次，实际值--%s次");

    private final String type;

    private final String message;

    WarnTypeEnum(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
