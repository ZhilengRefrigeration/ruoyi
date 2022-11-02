package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 比赛信息对象 competition
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public class Competition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 主队ID */
    @Excel(name = "主队ID")
    private Long mainTeamId;

    /** 主队名 */
    @Excel(name = "主队名")
    private String mainTeamName;

    /** 客队ID */
    @Excel(name = "客队ID")
    private Long guestTeamId;

    /** 客队名 */
    @Excel(name = "客队名")
    private String guestTeamName;

    /** 赛事编号 */
    @Excel(name = "赛事编号")
    private String competitionCode;

    /** 比赛名称 */
    @Excel(name = "比赛名称")
    private String competitionName;

    /** 是否指定对手 */
    @Excel(name = "是否指定对手")
    private Integer designated;

    /** 比赛类型,1,2,3,4,5,6 */
    @Excel(name = "比赛类型,1,2,3,4,5,6")
    private Long competitionType;

    /** 比赛时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "比赛时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date competitionTime;

    /** 球场ID */
    @Excel(name = "球场ID")
    private Long buildingId;

    /** 球场名称 */
    @Excel(name = "球场名称")
    private String buildingName;

    /** 比赛地址 */
    @Excel(name = "比赛地址")
    private String competitionAddress;

    /** 发起人ID */
    @Excel(name = "发起人ID")
    private Long founder;

    /** 比赛状态 */
    @Excel(name = "比赛状态")
    private Integer status;

    /** 城市编码 */
    @Excel(name = "城市编码")
    private String cityCode;

    /** 城市名称 */
    @Excel(name = "城市名称")
    private String cityName;

    /** 最大参与人数 */
    @Excel(name = "最大参与人数")
    private Long maxPlayer;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 最后修改人 */
    @Excel(name = "最后修改人")
    private String modifiedBy;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Long isDeleted;

    /** 经度 */
    @Excel(name = "经度")
    private Long longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private Long latitude;

    /** 比赛性质（0=约战；1=赛事） */
    @Excel(name = "比赛性质", readConverterExp = "0==约战；1=赛事")
    private Integer competitionNature;

    /** 报名开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enrollBeginTime;

    /** 报名结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enrollEndTime;

    /** 赛事联系人 */
    @Excel(name = "赛事联系人")
    private String contacts;

    /** 赛事联系人电话区号 */
    @Excel(name = "赛事联系人电话区号")
    private String contactsAreaCode;

    /** 赛事联系人电话 */
    @Excel(name = "赛事联系人电话")
    private String contactsTel;

    /** 比赛开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "比赛开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date competitionBeginTime;

    /** 比赛结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "比赛结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date competitionEndTime;

    /** 主办方 */
    @Excel(name = "主办方")
    private String organizer;

    /** 承办方 */
    @Excel(name = "承办方")
    private String undertake;

    /** 赛会背景图 */
    @Excel(name = "赛会背景图")
    private String competitionBackImg;

    /** 创建人userId */
    @Excel(name = "创建人userId")
    private Long createdId;

    /** 审核状态0=待审核；1=已审核；-1=审核不过 */
    @Excel(name = "审核状态0=待审核；1=已审核；-1=审核不过")
    private Long auditStatus;

    /** 身高隐藏  0不隐藏 1=隐藏 */
    @Excel(name = "身高隐藏  0不隐藏 1=隐藏")
    private Integer heightHide;

    /** 赞助商 */
    @Excel(name = "赞助商")
    private String sponsor;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMainTeamId(Long mainTeamId) 
    {
        this.mainTeamId = mainTeamId;
    }

    public Long getMainTeamId() 
    {
        return mainTeamId;
    }
    public void setMainTeamName(String mainTeamName) 
    {
        this.mainTeamName = mainTeamName;
    }

    public String getMainTeamName() 
    {
        return mainTeamName;
    }
    public void setGuestTeamId(Long guestTeamId) 
    {
        this.guestTeamId = guestTeamId;
    }

    public Long getGuestTeamId() 
    {
        return guestTeamId;
    }
    public void setGuestTeamName(String guestTeamName) 
    {
        this.guestTeamName = guestTeamName;
    }

    public String getGuestTeamName() 
    {
        return guestTeamName;
    }
    public void setCompetitionCode(String competitionCode) 
    {
        this.competitionCode = competitionCode;
    }

    public String getCompetitionCode() 
    {
        return competitionCode;
    }
    public void setCompetitionName(String competitionName) 
    {
        this.competitionName = competitionName;
    }

    public String getCompetitionName() 
    {
        return competitionName;
    }
    public void setDesignated(Integer designated) 
    {
        this.designated = designated;
    }

    public Integer getDesignated() 
    {
        return designated;
    }
    public void setCompetitionType(Long competitionType) 
    {
        this.competitionType = competitionType;
    }

    public Long getCompetitionType() 
    {
        return competitionType;
    }
    public void setCompetitionTime(Date competitionTime) 
    {
        this.competitionTime = competitionTime;
    }

    public Date getCompetitionTime() 
    {
        return competitionTime;
    }
    public void setBuildingId(Long buildingId) 
    {
        this.buildingId = buildingId;
    }

    public Long getBuildingId() 
    {
        return buildingId;
    }
    public void setBuildingName(String buildingName) 
    {
        this.buildingName = buildingName;
    }

    public String getBuildingName() 
    {
        return buildingName;
    }
    public void setCompetitionAddress(String competitionAddress) 
    {
        this.competitionAddress = competitionAddress;
    }

    public String getCompetitionAddress() 
    {
        return competitionAddress;
    }
    public void setFounder(Long founder) 
    {
        this.founder = founder;
    }

    public Long getFounder() 
    {
        return founder;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setCityCode(String cityCode) 
    {
        this.cityCode = cityCode;
    }

    public String getCityCode() 
    {
        return cityCode;
    }
    public void setCityName(String cityName) 
    {
        this.cityName = cityName;
    }

    public String getCityName() 
    {
        return cityName;
    }
    public void setMaxPlayer(Long maxPlayer) 
    {
        this.maxPlayer = maxPlayer;
    }

    public Long getMaxPlayer() 
    {
        return maxPlayer;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setLastUpdatedTime(Date lastUpdatedTime) 
    {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Date getLastUpdatedTime() 
    {
        return lastUpdatedTime;
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
    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }
    public void setLongitude(Long longitude) 
    {
        this.longitude = longitude;
    }

    public Long getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(Long latitude) 
    {
        this.latitude = latitude;
    }

    public Long getLatitude() 
    {
        return latitude;
    }
    public void setCompetitionNature(Integer competitionNature) 
    {
        this.competitionNature = competitionNature;
    }

    public Integer getCompetitionNature() 
    {
        return competitionNature;
    }
    public void setEnrollBeginTime(Date enrollBeginTime) 
    {
        this.enrollBeginTime = enrollBeginTime;
    }

    public Date getEnrollBeginTime() 
    {
        return enrollBeginTime;
    }
    public void setEnrollEndTime(Date enrollEndTime) 
    {
        this.enrollEndTime = enrollEndTime;
    }

    public Date getEnrollEndTime() 
    {
        return enrollEndTime;
    }
    public void setContacts(String contacts) 
    {
        this.contacts = contacts;
    }

    public String getContacts() 
    {
        return contacts;
    }
    public void setContactsAreaCode(String contactsAreaCode) 
    {
        this.contactsAreaCode = contactsAreaCode;
    }

    public String getContactsAreaCode() 
    {
        return contactsAreaCode;
    }
    public void setContactsTel(String contactsTel) 
    {
        this.contactsTel = contactsTel;
    }

    public String getContactsTel() 
    {
        return contactsTel;
    }
    public void setCompetitionBeginTime(Date competitionBeginTime) 
    {
        this.competitionBeginTime = competitionBeginTime;
    }

    public Date getCompetitionBeginTime() 
    {
        return competitionBeginTime;
    }
    public void setCompetitionEndTime(Date competitionEndTime) 
    {
        this.competitionEndTime = competitionEndTime;
    }

    public Date getCompetitionEndTime() 
    {
        return competitionEndTime;
    }
    public void setOrganizer(String organizer) 
    {
        this.organizer = organizer;
    }

    public String getOrganizer() 
    {
        return organizer;
    }
    public void setUndertake(String undertake) 
    {
        this.undertake = undertake;
    }

    public String getUndertake() 
    {
        return undertake;
    }
    public void setCompetitionBackImg(String competitionBackImg) 
    {
        this.competitionBackImg = competitionBackImg;
    }

    public String getCompetitionBackImg() 
    {
        return competitionBackImg;
    }
    public void setCreatedId(Long createdId) 
    {
        this.createdId = createdId;
    }

    public Long getCreatedId() 
    {
        return createdId;
    }
    public void setAuditStatus(Long auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() 
    {
        return auditStatus;
    }
    public void setHeightHide(Integer heightHide) 
    {
        this.heightHide = heightHide;
    }

    public Integer getHeightHide() 
    {
        return heightHide;
    }
    public void setSponsor(String sponsor) 
    {
        this.sponsor = sponsor;
    }

    public String getSponsor() 
    {
        return sponsor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mainTeamId", getMainTeamId())
            .append("mainTeamName", getMainTeamName())
            .append("guestTeamId", getGuestTeamId())
            .append("guestTeamName", getGuestTeamName())
            .append("competitionCode", getCompetitionCode())
            .append("competitionName", getCompetitionName())
            .append("designated", getDesignated())
            .append("competitionType", getCompetitionType())
            .append("competitionTime", getCompetitionTime())
            .append("buildingId", getBuildingId())
            .append("buildingName", getBuildingName())
            .append("competitionAddress", getCompetitionAddress())
            .append("founder", getFounder())
            .append("status", getStatus())
            .append("cityCode", getCityCode())
            .append("cityName", getCityName())
            .append("maxPlayer", getMaxPlayer())
            .append("createdTime", getCreatedTime())
            .append("lastUpdatedTime", getLastUpdatedTime())
            .append("createdBy", getCreatedBy())
            .append("modifiedBy", getModifiedBy())
            .append("isDeleted", getIsDeleted())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("remark", getRemark())
            .append("competitionNature", getCompetitionNature())
            .append("enrollBeginTime", getEnrollBeginTime())
            .append("enrollEndTime", getEnrollEndTime())
            .append("contacts", getContacts())
            .append("contactsAreaCode", getContactsAreaCode())
            .append("contactsTel", getContactsTel())
            .append("competitionBeginTime", getCompetitionBeginTime())
            .append("competitionEndTime", getCompetitionEndTime())
            .append("organizer", getOrganizer())
            .append("undertake", getUndertake())
            .append("competitionBackImg", getCompetitionBackImg())
            .append("createdId", getCreatedId())
            .append("auditStatus", getAuditStatus())
            .append("heightHide", getHeightHide())
            .append("sponsor", getSponsor())
            .toString();
    }
}
