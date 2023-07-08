package com.ruoyi.system.domain.enums;

import lombok.Getter;
import lombok.Setter;

/** : 比赛状态
 * @author :
 * @date : 2019/11/30
 */
public enum CompetitionStatusEnum {
    TREATY_WAR("treatyWar",0, "约战中"),
    ACCEPT_WAR("acceptWar", 1,"已应战"),
    WAREND("warEnd",2 ,"已结束");

    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private Integer value;
    @Getter
    @Setter
    private String desc;

    CompetitionStatusEnum(String code, Integer value, String desc) {
        this.code = code;
        this.desc = desc;
        this.value = value;
    }

}