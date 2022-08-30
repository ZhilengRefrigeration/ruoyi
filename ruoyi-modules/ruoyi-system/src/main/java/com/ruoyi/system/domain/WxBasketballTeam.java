package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 球队管理对象 basketball_team
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
public class WxBasketballTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID")
    private Long id;

    /** 删除 */
    private String isDeleted;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 更新人 */
    private String modifiedBy;

    /** 更新时间 */
    private Date lastUpdatedTime;

    /** 球队名称 */
    @Excel(name = "球队名称")
    private String teamName;

    /** 球队简介 */
    @Excel(name = "球队简介")
    private String teamDes;

    /** 球馆id */
    @Excel(name = "球馆id")
    private Long buildId;

    /** 球队图片 */
    private String defaultPicture;

    /** 球馆名称 */
    @Excel(name = "球馆名称")
    private String buildingName;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long createdId;

    /** 球队联系人电话 */
    @Excel(name = "球队联系人电话")
    private String contactTel;

    /** 球队logo */
    private String teamLogo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setIsDeleted(String isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() 
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
    public void setTeamName(String teamName) 
    {
        this.teamName = teamName;
    }

    public String getTeamName() 
    {
        return teamName;
    }
    public void setTeamDes(String teamDes) 
    {
        this.teamDes = teamDes;
    }

    public String getTeamDes() 
    {
        return teamDes;
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
    public void setBuildingName(String buildingName) 
    {
        this.buildingName = buildingName;
    }

    public String getBuildingName() 
    {
        return buildingName;
    }
    public void setCreatedId(Long createdId) 
    {
        this.createdId = createdId;
    }

    public Long getCreatedId() 
    {
        return createdId;
    }
    public void setContactTel(String contactTel) 
    {
        this.contactTel = contactTel;
    }

    public String getContactTel() 
    {
        return contactTel;
    }
    public void setTeamLogo(String teamLogo) 
    {
        this.teamLogo = teamLogo;
    }

    public String getTeamLogo() 
    {
        return teamLogo;
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
            .append("teamName", getTeamName())
            .append("teamDes", getTeamDes())
            .append("remark", getRemark())
            .append("buildId", getBuildId())
            .append("defaultPicture", getDefaultPicture())
            .append("buildingName", getBuildingName())
            .append("createdId", getCreatedId())
            .append("contactTel", getContactTel())
            .append("teamLogo", getTeamLogo())
            .toString();
    }
}
