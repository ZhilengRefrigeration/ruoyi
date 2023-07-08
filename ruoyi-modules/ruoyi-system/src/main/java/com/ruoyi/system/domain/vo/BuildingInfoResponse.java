package com.ruoyi.system.domain.vo;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.system.domain.BuildingInfoDetail;
import com.ruoyi.system.domain.FeatureLabel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@ApiModel(value="球馆全部详情")
public class BuildingInfoResponse extends BuildingInfoDetail {
    @ApiModelProperty(value="球馆ID",required=false)
    private Long id;
    /**
     *名称
     */

    @ApiModelProperty(value="球馆名称",required=false)
    private String buildingName;
    /**
     *
     */
    @ApiModelProperty(value="球馆地址",required=false)
    private String address;
    /**
     *经度
     */
    @ApiModelProperty(value="球馆经度",required=false)
    private BigDecimal longitude;

    /**
     *纬度
     */
    @ApiModelProperty(value="球馆维度",required=false)
    private BigDecimal latitude;
    /**
     *省
     */
    @ApiModelProperty(value="省",required=false)
    private String provinceCode;
    /**
     *市
     */
    @ApiModelProperty(value="市",required=false)
    private String cityCode;
    /**
     *区县编码
     */
    @ApiModelProperty(value="区县编码",required=false)
    private String countyCode;
    /**
     *备注
     */
    @ApiModelProperty(value="备注",required=false)
    private String desc;

    @ApiModelProperty(value="备注1",required=false)
    private String remark;
    /**
     *场馆特征
     */
    @ApiModelProperty(value="场馆特征",required=false)
    private List<FeatureLabel> labels;
    /**
     * 场馆照片
     */
    @ApiModelProperty(value="场馆照片",required=false)
    private String defaultPicture;

    /**
     * 球场距离
     */
    @ApiModelProperty(value="球场距离",required=false)
    private Double distance;
    /**
     * 球馆状态
     */
    @ApiModelProperty(value="球馆状态",required=false)
    private Long status;
    /**
     * 拒绝原因
     */
    @ApiModelProperty(value="拒绝原因",required=false)
    private String rejectReason;

    /**
     * 是否在线球场
     */
    @ApiModelProperty(value="是否在线球场",required=false)
    private String isSupportlive;
    /**
     *市名称
     */
    @ApiModelProperty(value="城市名称",required=false)
    private String cityName;
    /**
     * 球馆状态中文
     */
    @ApiModelProperty(value="球馆状态中文",required=false)
    private String statusName;


    private String mittelkurs;
    @Excel(name = "创建人ID")
    private Long createdId;

    private String chatGroupUrl;
}
