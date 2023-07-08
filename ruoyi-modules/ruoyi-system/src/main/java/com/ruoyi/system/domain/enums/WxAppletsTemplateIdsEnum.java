package com.ruoyi.system.domain.enums;

import lombok.Getter;
import lombok.Setter;

/**微信小程序模版id
 * @author :wyb
 * @date : 2019/11/30
 */
public enum WxAppletsTemplateIdsEnum {
    COMPETITION_SIGN_UP("nsYn7oicXL4bdmQcLgvjIj7tKK3cdTgk8fo1AtOgbMU", "赛事报名通知"),
    JOIN_COMPETITION_REMIND("F827c-LWDS3UTaJYthC-i_V_2G1ZlmDhiaipzcl8_Xk", "参赛提醒"),
    COMPETITION_COURSE_REMIND("PpYtjeEGaP12P3ZptJAkIeaaF3WAlwrjy5Kd7qjF22Y", "比赛赛程提醒"),
    TREATY_WAR("Ixgc13ZUMPrOZbR05EvdicqpsH88WMByfXULm7IGrmQ", "约战准备提醒"),
    TREATY_WAR_SUCCESS("Ixgc13ZUMPrOZbR05EvdicqpsH88WMByfXULm7IGrmQ", "约战成功提醒"),
    ACCEPT_WAR ("QfLtKHzgj7R-Qa73x1loykHbcy4FkiAGLWeZPpSqEQk", "应战提醒");

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String desc;

    WxAppletsTemplateIdsEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}