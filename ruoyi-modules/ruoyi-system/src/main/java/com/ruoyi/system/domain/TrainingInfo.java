package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 training_info
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public class TrainingInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

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

    /** 培训机构名称 */
    @Excel(name = "培训机构名称")
    private String trainName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String linkman;

    /** 培训公告 */
    @Excel(name = "培训公告")
    private String trainDesc;

    /** 场馆ID */
    @Excel(name = "场馆ID")
    private Long buildId;

    /** 默认图片 */
    @Excel(name = "默认图片")
    private String defaultPicture;

    /** 培训价格 */
    @Excel(name = "培训价格")
    private String price;

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
    public void setTrainName(String trainName) 
    {
        this.trainName = trainName;
    }

    public String getTrainName() 
    {
        return trainName;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setLinkman(String linkman) 
    {
        this.linkman = linkman;
    }

    public String getLinkman() 
    {
        return linkman;
    }
    public void setTrainDesc(String trainDesc) 
    {
        this.trainDesc = trainDesc;
    }

    public String getTrainDesc() 
    {
        return trainDesc;
    }
    public void setBuildId(Long buildId) 
    {
        this.buildId = buildId;
    }

    public Long getBuildId() 
    {
        return buildId;
    }
    public void setDefaultPicture(String defaultPicture) 
    {
        this.defaultPicture = defaultPicture;
    }

    public String getDefaultPicture() 
    {
        return defaultPicture;
    }
    public void setPrice(String price) 
    {
        this.price = price;
    }

    public String getPrice() 
    {
        return price;
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
            .append("trainName", getTrainName())
            .append("phone", getPhone())
            .append("linkman", getLinkman())
            .append("trainDesc", getTrainDesc())
            .append("buildId", getBuildId())
            .append("defaultPicture", getDefaultPicture())
            .append("price", getPrice())
            .toString();
    }
}
