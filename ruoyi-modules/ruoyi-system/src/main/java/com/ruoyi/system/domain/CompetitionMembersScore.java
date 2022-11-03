package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 赛会中-赛程-人员得分对象 competition_members_score
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public class CompetitionMembersScore extends BaseEntity
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

    /** 比赛节数 */
    @Excel(name = "比赛节数")
    private Long nodeNum;

    /** 球队人员ID */
    @Excel(name = "球队人员ID")
    private Long teamUserId;

    /** 球衣号 */
    @Excel(name = "球衣号")
    private String jerseyNumber;

    /** 总得分 */
    @Excel(name = "总得分")
    private Long totalScore;

    /** 2分球 */
    @Excel(name = "2分球")
    private Long twoPoints;

    /** 3分球 */
    @Excel(name = "3分球")
    private Long threePoints;

    /** 罚球 */
    @Excel(name = "罚球")
    private Long penalty;

    /** 篮板 */
    @Excel(name = "篮板")
    private Long backboard;

    /** 前板 */
    @Excel(name = "前板")
    private Long frontPlate;

    /** 后板 */
    @Excel(name = "后板")
    private Long backPlate;

    /** 助攻 */
    @Excel(name = "助攻")
    private Long assists;

    /** 抢断 */
    @Excel(name = "抢断")
    private Long snatch;

    /** 盖帽 */
    @Excel(name = "盖帽")
    private Long block;

    /** 失误 */
    @Excel(name = "失误")
    private Long fault;

    /** 犯规 */
    @Excel(name = "犯规")
    private Long breaks;

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

    /** 是否首发球员 */
    @Excel(name = "是否首发球员")
    private Integer isFirstLaunch;

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
    public void setNodeNum(Long nodeNum) 
    {
        this.nodeNum = nodeNum;
    }

    public Long getNodeNum() 
    {
        return nodeNum;
    }
    public void setTeamUserId(Long teamUserId) 
    {
        this.teamUserId = teamUserId;
    }

    public Long getTeamUserId() 
    {
        return teamUserId;
    }
    public void setJerseyNumber(String jerseyNumber) 
    {
        this.jerseyNumber = jerseyNumber;
    }

    public String getJerseyNumber() 
    {
        return jerseyNumber;
    }
    public void setTotalScore(Long totalScore) 
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore() 
    {
        return totalScore;
    }
    public void setTwoPoints(Long twoPoints) 
    {
        this.twoPoints = twoPoints;
    }

    public Long getTwoPoints() 
    {
        return twoPoints;
    }
    public void setThreePoints(Long threePoints) 
    {
        this.threePoints = threePoints;
    }

    public Long getThreePoints() 
    {
        return threePoints;
    }
    public void setPenalty(Long penalty) 
    {
        this.penalty = penalty;
    }

    public Long getPenalty() 
    {
        return penalty;
    }
    public void setBackboard(Long backboard) 
    {
        this.backboard = backboard;
    }

    public Long getBackboard() 
    {
        return backboard;
    }
    public void setFrontPlate(Long frontPlate) 
    {
        this.frontPlate = frontPlate;
    }

    public Long getFrontPlate() 
    {
        return frontPlate;
    }
    public void setBackPlate(Long backPlate) 
    {
        this.backPlate = backPlate;
    }

    public Long getBackPlate() 
    {
        return backPlate;
    }
    public void setAssists(Long assists) 
    {
        this.assists = assists;
    }

    public Long getAssists() 
    {
        return assists;
    }
    public void setSnatch(Long snatch) 
    {
        this.snatch = snatch;
    }

    public Long getSnatch() 
    {
        return snatch;
    }
    public void setBlock(Long block) 
    {
        this.block = block;
    }

    public Long getBlock() 
    {
        return block;
    }
    public void setFault(Long fault) 
    {
        this.fault = fault;
    }

    public Long getFault() 
    {
        return fault;
    }
    public void setBreaks(Long breaks) 
    {
        this.breaks = breaks;
    }

    public Long getBreaks() 
    {
        return breaks;
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
    public void setIsFirstLaunch(Integer isFirstLaunch) 
    {
        this.isFirstLaunch = isFirstLaunch;
    }

    public Integer getIsFirstLaunch() 
    {
        return isFirstLaunch;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("competitionId", getCompetitionId())
            .append("competitionVsId", getCompetitionVsId())
            .append("teamId", getTeamId())
            .append("teamName", getTeamName())
            .append("nodeNum", getNodeNum())
            .append("teamUserId", getTeamUserId())
            .append("jerseyNumber", getJerseyNumber())
            .append("totalScore", getTotalScore())
            .append("twoPoints", getTwoPoints())
            .append("threePoints", getThreePoints())
            .append("penalty", getPenalty())
            .append("backboard", getBackboard())
            .append("frontPlate", getFrontPlate())
            .append("backPlate", getBackPlate())
            .append("assists", getAssists())
            .append("snatch", getSnatch())
            .append("block", getBlock())
            .append("fault", getFault())
            .append("breaks", getBreaks())
            .append("createdTime", getCreatedTime())
            .append("lastUpdatedTime", getLastUpdatedTime())
            .append("createdBy", getCreatedBy())
            .append("modifiedBy", getModifiedBy())
            .append("isDeleted", getIsDeleted())
            .append("remark", getRemark())
            .append("isFirstLaunch", getIsFirstLaunch())
            .toString();
    }
}
