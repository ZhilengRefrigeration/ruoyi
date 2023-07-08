package com.ruoyi.system.domain.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author :
 * @date : 2019/11/30
 */
public enum RoleEnum {

    GUEST("guest", "游客"),

    TEAM_MANAGER("teamManager", "队长"),

    TEAM_MEMBER("teamMember", "队员"),

    REFEREES("referees", "裁判员");

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String desc;

    RoleEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}