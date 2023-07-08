package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.WxBasketballTeam;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by liguanghui on 2019/12/26.
 */
public class BasketballTeamResponse extends WxBasketballTeam {
    /**
     *名称
     */
    @Setter
    @Getter
    private String buildingName;
    /**
     *
     */
    @Setter
    @Getter
    private String address;
    /**
     *经度
     */
    @Setter
    @Getter
    private BigDecimal longitude;

    /**
     *纬度
     */
    @Setter
    @Getter
    private BigDecimal latitude;
    /**
     * 场馆照片
     */
    @Setter
    @Getter
    private String defaultPicture;
    /**
     *备注
     */
    @Setter
    @Getter
    private String desc;
}
