package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 球队人员对象 team_members
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public class TeamMembers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 球队ID */
    @Excel(name = "球队ID")
    private Long teamId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 角色编码 */
    @Excel(name = "角色编码")
    private String roleCode;

    /** 球衣号码 */
    @Excel(name = "球衣号码")
    private String jerseyNumber;

    /** 人员状态 */
    @Excel(name = "人员状态")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 删除1 */
    @Excel(name = "删除1")
    private Long isDeleted;

    /** 最后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String modifiedBy;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String roleName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTeamId(Long teamId) 
    {
        this.teamId = teamId;
    }

    public Long getTeamId() 
    {
        return teamId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setRoleCode(String roleCode) 
    {
        this.roleCode = roleCode;
    }

    public String getRoleCode() 
    {
        return roleCode;
    }
    public void setJerseyNumber(String jerseyNumber) 
    {
        this.jerseyNumber = jerseyNumber;
    }

    public String getJerseyNumber() 
    {
        return jerseyNumber;
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
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }
    public void setLastUpdatedTime(Date lastUpdatedTime) 
    {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Date getLastUpdatedTime() 
    {
        return lastUpdatedTime;
    }
    public void setModifiedBy(String modifiedBy) 
    {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedBy() 
    {
        return modifiedBy;
    }
    public void setRoleName(String roleName) 
    {
        this.roleName = roleName;
    }

    public String getRoleName() 
    {
        return roleName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("teamId", getTeamId())
            .append("userId", getUserId())
            .append("roleCode", getRoleCode())
            .append("jerseyNumber", getJerseyNumber())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createdTime", getCreatedTime())
            .append("createdBy", getCreatedBy())
            .append("isDeleted", getIsDeleted())
            .append("lastUpdatedTime", getLastUpdatedTime())
            .append("modifiedBy", getModifiedBy())
            .append("roleName", getRoleName())
            .toString();
    }
}
