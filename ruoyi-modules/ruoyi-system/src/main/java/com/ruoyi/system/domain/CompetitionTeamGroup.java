package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 赛会中-分组对象 competition_team_group
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public class CompetitionTeamGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 赛事id(competition的ID) */
    @Excel(name = "赛事id(competition的ID)")
    private Long competitionId;

    /** 球队所属的组 */
    @Excel(name = "球队所属的组")
    private String competitionGroup;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 状态 0=申请，1=同意；-1=驳回 */
    @Excel(name = "状态 0=申请，1=同意；-1=驳回")
    private Integer status;

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

    /** 循环赛编排状态 0=未编排，1=已编排 */
    @Excel(name = "循环赛编排状态 0=未编排，1=已编排")
    private Long isCycle;

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
    public void setCompetitionGroup(String competitionGroup) 
    {
        this.competitionGroup = competitionGroup;
    }

    public String getCompetitionGroup() 
    {
        return competitionGroup;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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
    public void setIsCycle(Long isCycle) 
    {
        this.isCycle = isCycle;
    }

    public Long getIsCycle() 
    {
        return isCycle;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("competitionId", getCompetitionId())
            .append("competitionGroup", getCompetitionGroup())
            .append("createdTime", getCreatedTime())
            .append("status", getStatus())
            .append("lastUpdatedTime", getLastUpdatedTime())
            .append("createdBy", getCreatedBy())
            .append("modifiedBy", getModifiedBy())
            .append("isDeleted", getIsDeleted())
            .append("remark", getRemark())
            .append("isCycle", getIsCycle())
            .toString();
    }
}
