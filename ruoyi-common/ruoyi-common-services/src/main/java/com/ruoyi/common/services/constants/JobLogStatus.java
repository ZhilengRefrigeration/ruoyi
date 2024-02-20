package com.ruoyi.common.services.constants;

import com.ruoyi.common.core.constant.IEnum;
import lombok.Getter;

/**
 * @author Alan Scipio
 * created on 2024/2/20
 */
@Getter
public enum JobLogStatus implements IEnum {

    SUCCESS(0, "正常执行"),

    ERROR(1, "异常");

    private final int code;
    private final String name;

    JobLogStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
