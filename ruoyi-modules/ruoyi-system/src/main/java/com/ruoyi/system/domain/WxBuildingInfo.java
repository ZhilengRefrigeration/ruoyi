package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 球场管理对象 building_info
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
public class WxBuildingInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 删除 */
    @Excel(name = "删除")
    private Long isDeleted;

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
    @Excel(name = "是否支持在线")
    private Long isSupportlive;

    /** 球馆状态 */
    @Excel(name = "球馆状态")
    private Long status;

    /** 拒绝原因 */
    @Excel(name = "拒绝原因")
    private String rejectReason;

    /** 是否开放 */
    @Excel(name = "是否开放")
    private Long isOpen;

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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setModifiedBy(String modifiedBy) 
    {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedBy() 
    {
        return modifiedBy;
    }
    public void setLastUpdatedTime(Date lastUpdatedTime) 
    {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Date getLastUpdatedTime() 
    {
        return lastUpdatedTime;
    }
    public void setBuildingName(String buildingName) 
    {
        this.buildingName = buildingName;
    }

    public String getBuildingName() 
    {
        return buildingName;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setLongitude(BigDecimal longitude) 
    {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(BigDecimal latitude) 
    {
        this.latitude = latitude;
    }

    public BigDecimal getLatitude() 
    {
        return latitude;
    }
    public void setProvinceCode(String provinceCode) 
    {
        this.provinceCode = provinceCode;
    }

    public String getProvinceCode() 
    {
        return provinceCode;
    }
    public void setCityCode(String cityCode) 
    {
        this.cityCode = cityCode;
    }

    public String getCityCode() 
    {
        return cityCode;
    }
    public void setCountyCode(String countyCode) 
    {
        this.countyCode = countyCode;
    }

    public String getCountyCode() 
    {
        return countyCode;
    }
    public void setCityName(String cityName) 
    {
        this.cityName = cityName;
    }

    public String getCityName() 
    {
        return cityName;
    }
    public void setDefaultPicture(String defaultPicture) 
    {
        this.defaultPicture = defaultPicture;
    }

    public String getDefaultPicture() 
    {
        return defaultPicture;
    }
    public void setIsSupportlive(Long isSupportlive) 
    {
        this.isSupportlive = isSupportlive;
    }

    public Long getIsSupportlive() 
    {
        return isSupportlive;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setRejectReason(String rejectReason) 
    {
        this.rejectReason = rejectReason;
    }

    public String getRejectReason() 
    {
        return rejectReason;
    }
    public void setIsOpen(Long isOpen) 
    {
        this.isOpen = isOpen;
    }

    public Long getIsOpen() 
    {
        return isOpen;
    }
    public void setMittelkurs(String mittelkurs) 
    {
        this.mittelkurs = mittelkurs;
    }

    public String getMittelkurs() 
    {
        return mittelkurs;
    }
    public void setChatGroupUrl(String chatGroupUrl) 
    {
        this.chatGroupUrl = chatGroupUrl;
    }

    public String getChatGroupUrl() 
    {
        return chatGroupUrl;
    }
    public void setCreatedId(Long createdId) 
    {
        this.createdId = createdId;
    }

    public Long getCreatedId() 
    {
        return createdId;
    }
    public void setDesc(String desc) 
    {
        this.desc = desc;
    }

    public String getDesc() 
    {
        return desc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("isDeleted", getIsDeleted())
            .append("createdTime", getCreatedTime())
            .append("createdBy", getCreatedBy())
            .append("modifiedBy", getModifiedBy())
            .append("lastUpdatedTime", getLastUpdatedTime())
            .append("buildingName", getBuildingName())
            .append("address", getAddress())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("provinceCode", getProvinceCode())
            .append("cityCode", getCityCode())
            .append("countyCode", getCountyCode())
            .append("remark", getRemark())
            .append("cityName", getCityName())
            .append("defaultPicture", getDefaultPicture())
            .append("isSupportlive", getIsSupportlive())
            .append("status", getStatus())
            .append("rejectReason", getRejectReason())
            .append("isOpen", getIsOpen())
            .append("mittelkurs", getMittelkurs())
            .append("chatGroupUrl", getChatGroupUrl())
            .append("createdId", getCreatedId())
            .append("desc", getDesc())
            .toString();
    }
}
