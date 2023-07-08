package com.ruoyi.system.domain.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author :
 * @date : 2019/11/30
 */
public enum FowTypeEnum {

    MEMBER_APPLY("memberApply", "队员申请"),

    MAKE_AN_APPOINTMENT("makeAnAppointment", "约战消息");

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String desc;

    FowTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}