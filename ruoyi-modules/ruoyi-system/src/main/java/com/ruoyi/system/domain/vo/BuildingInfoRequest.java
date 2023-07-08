package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.WxBuildingInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by liguanghui on 2020/1/17.
 */
public class BuildingInfoRequest extends WxBuildingInfo {
    @Setter
    @Getter
    private String openId;
    /**
     *
     */
    @Setter
    @Getter
    private Long buildingId;
    /**
     *营业时间
     */
    @Setter
    @Getter
    private String businessHours;

    /**
     *联系人
     */
    @Setter
    @Getter
    private String linkman;

    /**
     *联系电话
     */
    @Setter
    @Getter
    private String linkphone;

    /**
     *收费标准
     */
    @Setter
    @Getter
    private String feeStandard;

    /**
     *球馆标签
     */
    @Setter
    @Getter
    private String labelDesc;
    /**
     *公告
     */
    @Setter
    @Getter
    private String notice;
}
