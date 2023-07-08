package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 sh_area
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public class ShArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 父id */
    @Excel(name = "父id")
    private Long pid;

    /** 简称 */
    @Excel(name = "简称")
    private String shortname;

    /** 名称 */
    @Excel(name = "名称")
    private String cityname;

    /** 全称 */
    @Excel(name = "全称")
    private String mergerName;

    /** 层级 0 1 2 省市区县 */
    @Excel(name = "层级 0 1 2 省市区县")
    private Integer level;

    /** 拼音 */
    @Excel(name = "拼音")
    private String pinyin;

    /** 长途区号 */
    @Excel(name = "长途区号")
    private String code;

    /** 邮编 */
    @Excel(name = "邮编")
    private String zipCode;

    /** 首字母 */
    @Excel(name = "首字母")
    private String first;

    /** 经度 */
    @Excel(name = "经度")
    private String longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String latitude;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long isDeleted;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date createdTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createdBy;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String modifiedBy;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date lastUpdatedTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setShortname(String shortname) 
    {
        this.shortname = shortname;
    }

    public String getShortname() 
    {
        return shortname;
    }
    public void setCityname(String cityname) 
    {
        this.cityname = cityname;
    }

    public String getCityname() 
    {
        return cityname;
    }
    public void setMergerName(String mergerName) 
    {
        this.mergerName = mergerName;
    }

    public String getMergerName() 
    {
        return mergerName;
    }
    public void setLevel(Integer level) 
    {
        this.level = level;
    }

    public Integer getLevel() 
    {
        return level;
    }
    public void setPinyin(String pinyin) 
    {
        this.pinyin = pinyin;
    }

    public String getPinyin() 
    {
        return pinyin;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setZipCode(String zipCode) 
    {
        this.zipCode = zipCode;
    }

    public String getZipCode() 
    {
        return zipCode;
    }
    public void setFirst(String first) 
    {
        this.first = first;
    }

    public String getFirst() 
    {
        return first;
    }
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("shortname", getShortname())
            .append("cityname", getCityname())
            .append("mergerName", getMergerName())
            .append("level", getLevel())
            .append("pinyin", getPinyin())
            .append("code", getCode())
            .append("zipCode", getZipCode())
            .append("first", getFirst())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("isDeleted", getIsDeleted())
            .append("createdTime", getCreatedTime())
            .append("createdBy", getCreatedBy())
            .append("modifiedBy", getModifiedBy())
            .append("lastUpdatedTime", getLastUpdatedTime())
            .toString();
    }
}
