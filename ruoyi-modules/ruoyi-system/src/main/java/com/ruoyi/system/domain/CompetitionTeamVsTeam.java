package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 赛会中-球队VS球队关系对象 competition_team_vs_team
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public class CompetitionTeamVsTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 赛事id(competition的ID) */
    @Excel(name = "赛事id(competition的ID)")
    private Long competitionId;

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

    /** 比赛分组(A,B,C) */
    @Excel(name = "比赛分组(A,B,C)")
    private String competitionGroup;

    /** 状态-1=已取消； 0=报名中，1=比赛中；2=已结束 */
    @Excel(name = "状态-1=已取消； 0=报名中，1=比赛中；2=已结束")
    private Integer status;

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

    /** 主队得分 */
    @Excel(name = "主队得分")
    private Long mainTeamScore;

    /** 客队得分 */
    @Excel(name = "客队得分")
    private Long guestTeamScore;

    /** 比赛类型：循环赛，淘汰赛 */
    @Excel(name = "比赛类型：循环赛，淘汰赛")
    private String vsType;

    /** 系统生成的赛程的批次号 */
    @Excel(name = "系统生成的赛程的批次号")
    private String batchNumber;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCompetitionId(Long competitionId) 
    {
        this.competitionId = competitionId;
    }

    public Long getCompetitionId() 
    {
        return competitionId;
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
    public void setCompetitionGroup(String competitionGroup) 
    {
        this.competitionGroup = competitionGroup;
    }

    public String getCompetitionGroup() 
    {
        return competitionGroup;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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
    public void setMainTeamScore(Long mainTeamScore) 
    {
        this.mainTeamScore = mainTeamScore;
    }

    public Long getMainTeamScore() 
    {
        return mainTeamScore;
    }
    public void setGuestTeamScore(Long guestTeamScore) 
    {
        this.guestTeamScore = guestTeamScore;
    }

    public Long getGuestTeamScore() 
    {
        return guestTeamScore;
    }
    public void setVsType(String vsType) 
    {
        this.vsType = vsType;
    }

    public String getVsType() 
    {
        return vsType;
    }
    public void setBatchNumber(String batchNumber) 
    {
        this.batchNumber = batchNumber;
    }

    public String getBatchNumber() 
    {
        return batchNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("competitionId", getCompetitionId())
            .append("mainTeamId", getMainTeamId())
            .append("mainTeamName", getMainTeamName())
            .append("guestTeamId", getGuestTeamId())
            .append("guestTeamName", getGuestTeamName())
            .append("competitionTime", getCompetitionTime())
            .append("buildingId", getBuildingId())
            .append("buildingName", getBuildingName())
            .append("competitionAddress", getCompetitionAddress())
            .append("competitionGroup", getCompetitionGroup())
            .append("status", getStatus())
            .append("createdTime", getCreatedTime())
            .append("lastUpdatedTime", getLastUpdatedTime())
            .append("createdBy", getCreatedBy())
            .append("modifiedBy", getModifiedBy())
            .append("isDeleted", getIsDeleted())
            .append("remark", getRemark())
            .append("mainTeamScore", getMainTeamScore())
            .append("guestTeamScore", getGuestTeamScore())
            .append("vsType", getVsType())
            .append("batchNumber", getBatchNumber())
            .toString();
    }
}
