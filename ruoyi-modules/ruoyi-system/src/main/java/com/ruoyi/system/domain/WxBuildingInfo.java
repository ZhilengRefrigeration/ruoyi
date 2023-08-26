package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Table;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 球场管理对象 building_info
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
@Data
@Table("building_info")
public class WxBuildingInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 删除 */
    @Excel(name = "删除")
    private Integer isDeleted;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 更新人 */
    @Excel(name = "更新人")
    private String modifiedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedTime;

    /** 名称 */
    @Excel(name = "名称")
    private String buildingName;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private BigDecimal latitude;

    /** 省 */
    @Excel(name = "省")
    private String provinceCode;

    /** 市 */
    @Excel(name = "市")
    private String cityCode;

    /** 区县编码 */
    @Excel(name = "区县编码")
    private String countyCode;

    /** 城市 */
    @Excel(name = "城市")
    private String cityName;

    /** 默认图片 */
    @Excel(name = "默认图片")
    private String defaultPicture;

    /** 是否支持在线 */
    @Excel(name = "支持在线")
    private Boolean isSupportlive;

    /** 球馆状态 */
    @Excel(name = "球馆状态")
    private Integer status;

    /** 拒绝原因 */
    @Excel(name = "拒绝原因")
    private String rejectReason;

    /** 是否开放 */
    @Excel(name = "是否开放")
    private Boolean isOpen;

    /** 人均价格 */
    @Excel(name = "人均价格")
    private String mittelkurs;

    /** 微信管理员二维码路径 */
    @Excel(name = "微信管理员二维码路径")
    private String chatGroupUrl;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long createdId;

    /** 描述 */
    @Excel(name = "描述")
    private String desc;

}
