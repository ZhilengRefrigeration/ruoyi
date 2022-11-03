package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 赛会中-赛程结果记录对象 competition_result
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public class CompetitionResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 赛事id(competition的ID) */
    @Excel(name = "赛事id(competition的ID)")
    private Long competitionId;

    /** 赛程id(competition_team_vs_team的ID) */
    @Excel(name = "赛程id(competition_team_vs_team的ID)")
    private Long competitionVsId;

    /** 球队ID */
    @Excel(name = "球队ID")
    private Long teamId;

    /** 球队名 */
    @Excel(name = "球队名")
    private String teamName;

    /** 比赛节数1计分 */
    @Excel(name = "比赛节数1计分")
    private Long oneNodeScore;

    /** 比赛节数2计分 */
    @Excel(name = "比赛节数2计分")
    private Long twoNodeScore;

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

    /** 比赛节数3计分 */
    @Excel(name = "比赛节数3计分")
    private Long threeNodeScore;

    /** 比赛节数4计分 */
    @Excel(name = "比赛节数4计分")
    private Long fourNodeScore;

    /** 比赛节数5计分 */
    @Excel(name = "比赛节数5计分")
    private Long fiveNodeScore;

    /** 比赛节数6计分 */
    @Excel(name = "比赛节数6计分")
    private Long sixNodeScore;

    /** 比赛积分 */
    @Excel(name = "比赛积分")
    private Long integral;

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
    public void setCompetitionVsId(Long competitionVsId) 
    {
        this.competitionVsId = competitionVsId;
    }

    public Long getCompetitionVsId() 
    {
        return competitionVsId;
    }
    public void setTeamId(Long teamId) 
    {
        this.teamId = teamId;
    }

    public Long getTeamId() 
    {
        return teamId;
    }
    public void setTeamName(String teamName) 
    {
        this.teamName = teamName;
    }

    public String getTeamName() 
    {
        return teamName;
    }
    public void setOneNodeScore(Long oneNodeScore) 
    {
        this.oneNodeScore = oneNodeScore;
    }

    public Long getOneNodeScore() 
    {
        return oneNodeScore;
    }
    public void setTwoNodeScore(Long twoNodeScore) 
    {
        this.twoNodeScore = twoNodeScore;
    }

    public Long getTwoNodeScore() 
    {
        return twoNodeScore;
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
    public void setThreeNodeScore(Long threeNodeScore) 
    {
        this.threeNodeScore = threeNodeScore;
    }

    public Long getThreeNodeScore() 
    {
        return threeNodeScore;
    }
    public void setFourNodeScore(Long fourNodeScore) 
    {
        this.fourNodeScore = fourNodeScore;
    }

    public Long getFourNodeScore() 
    {
        return fourNodeScore;
    }
    public void setFiveNodeScore(Long fiveNodeScore) 
    {
        this.fiveNodeScore = fiveNodeScore;
    }

    public Long getFiveNodeScore() 
    {
        return fiveNodeScore;
    }
    public void setSixNodeScore(Long sixNodeScore) 
    {
        this.sixNodeScore = sixNodeScore;
    }

    public Long getSixNodeScore() 
    {
        return sixNodeScore;
    }
    public void setIntegral(Long integral) 
    {
        this.integral = integral;
    }

    public Long getIntegral() 
    {
        return integral;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("competitionId", getCompetitionId())
            .append("competitionVsId", getCompetitionVsId())
            .append("teamId", getTeamId())
            .append("teamName", getTeamName())
            .append("oneNodeScore", getOneNodeScore())
            .append("twoNodeScore", getTwoNodeScore())
            .append("competitionGroup", getCompetitionGroup())
            .append("status", getStatus())
            .append("createdTime", getCreatedTime())
            .append("lastUpdatedTime", getLastUpdatedTime())
            .append("createdBy", getCreatedBy())
            .append("modifiedBy", getModifiedBy())
            .append("isDeleted", getIsDeleted())
            .append("remark", getRemark())
            .append("threeNodeScore", getThreeNodeScore())
            .append("fourNodeScore", getFourNodeScore())
            .append("fiveNodeScore", getFiveNodeScore())
            .append("sixNodeScore", getSixNodeScore())
            .append("integral", getIntegral())
            .toString();
    }
}
