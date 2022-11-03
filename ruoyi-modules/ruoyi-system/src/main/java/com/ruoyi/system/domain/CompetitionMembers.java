package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 比赛参与人员对象 competition_members
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public class CompetitionMembers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 角色编码 */
    @Excel(name = "角色编码")
    private String roleCode;

    /** 比赛ID */
    @Excel(name = "比赛ID")
    private Long competitionId;

    /** 参赛球队id */
    @Excel(name = "参赛球队id")
    private Long competitionTeamId;

    /** 比赛得分 */
    @Excel(name = "比赛得分")
    private Integer score;

    /** 总罚球 */
    @Excel(name = "总罚球")
    private Integer penalty;

    /** 2分球 */
    @Excel(name = "2分球")
    private Integer twoPoints;

    /** 3分球 */
    @Excel(name = "3分球")
    private Integer threePoints;

    /** 总犯规 */
    @Excel(name = "总犯规")
    private Integer breaks;

    /** 总篮板球 */
    @Excel(name = "总篮板球")
    private Integer rebound;

    /** 总盖帽 */
    @Excel(name = "总盖帽")
    private Integer block;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 最后修改人 */
    @Excel(name = "最后修改人")
    private String modifiedBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedTime;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 人员类型：0=球员；1=队长 */
    @Excel(name = "人员类型：0=球员；1=队长")
    private Integer userType;

    /** competition_of_team主键ID */
    @Excel(name = "competition_of_team主键ID")
    private Long competitionOfTeamId;

    /** 比赛性质（0=约战；1=赛事） */
    @Excel(name = "比赛性质", readConverterExp = "0==约战；1=赛事")
    private Long competitionNature;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 球衣号 */
    @Excel(name = "球衣号")
    private String jerseyNumber;

    /** 证件类型（身份证） */
    @Excel(name = "证件类型", readConverterExp = "身=份证")
    private String idType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idCardNo;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactsTel;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contacts;

    /** 联系人区号 */
    @Excel(name = "联系人区号")
    private String contactsAreaCode;

    /** 个人照片（最新） */
    @Excel(name = "个人照片", readConverterExp = "最=新")
    private String personalPhoto;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer isFirstLaunch;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setCompetitionId(Long competitionId) 
    {
        this.competitionId = competitionId;
    }

    public Long getCompetitionId() 
    {
        return competitionId;
    }
    public void setCompetitionTeamId(Long competitionTeamId) 
    {
        this.competitionTeamId = competitionTeamId;
    }

    public Long getCompetitionTeamId() 
    {
        return competitionTeamId;
    }
    public void setScore(Integer score) 
    {
        this.score = score;
    }

    public Integer getScore() 
    {
        return score;
    }
    public void setPenalty(Integer penalty) 
    {
        this.penalty = penalty;
    }

    public Integer getPenalty() 
    {
        return penalty;
    }
    public void setTwoPoints(Integer twoPoints) 
    {
        this.twoPoints = twoPoints;
    }

    public Integer getTwoPoints() 
    {
        return twoPoints;
    }
    public void setThreePoints(Integer threePoints) 
    {
        this.threePoints = threePoints;
    }

    public Integer getThreePoints() 
    {
        return threePoints;
    }
    public void setBreaks(Integer breaks) 
    {
        this.breaks = breaks;
    }

    public Integer getBreaks() 
    {
        return breaks;
    }
    public void setRebound(Integer rebound) 
    {
        this.rebound = rebound;
    }

    public Integer getRebound() 
    {
        return rebound;
    }
    public void setBlock(Integer block) 
    {
        this.block = block;
    }

    public Integer getBlock() 
    {
        return block;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
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
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setUserType(Integer userType) 
    {
        this.userType = userType;
    }

    public Integer getUserType() 
    {
        return userType;
    }
    public void setCompetitionOfTeamId(Long competitionOfTeamId) 
    {
        this.competitionOfTeamId = competitionOfTeamId;
    }

    public Long getCompetitionOfTeamId() 
    {
        return competitionOfTeamId;
    }
    public void setCompetitionNature(Long competitionNature) 
    {
        this.competitionNature = competitionNature;
    }

    public Long getCompetitionNature() 
    {
        return competitionNature;
    }
    public void setRealName(String realName) 
    {
        this.realName = realName;
    }

    public String getRealName() 
    {
        return realName;
    }
    public void setJerseyNumber(String jerseyNumber) 
    {
        this.jerseyNumber = jerseyNumber;
    }

    public String getJerseyNumber() 
    {
        return jerseyNumber;
    }
    public void setIdType(String idType) 
    {
        this.idType = idType;
    }

    public String getIdType() 
    {
        return idType;
    }
    public void setIdCardNo(String idCardNo) 
    {
        this.idCardNo = idCardNo;
    }

    public String getIdCardNo() 
    {
        return idCardNo;
    }
    public void setContactsTel(String contactsTel) 
    {
        this.contactsTel = contactsTel;
    }

    public String getContactsTel() 
    {
        return contactsTel;
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
    public void setPersonalPhoto(String personalPhoto) 
    {
        this.personalPhoto = personalPhoto;
    }

    public String getPersonalPhoto() 
    {
        return personalPhoto;
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
            .append("userId", getUserId())
            .append("roleCode", getRoleCode())
            .append("competitionId", getCompetitionId())
            .append("competitionTeamId", getCompetitionTeamId())
            .append("score", getScore())
            .append("penalty", getPenalty())
            .append("twoPoints", getTwoPoints())
            .append("threePoints", getThreePoints())
            .append("breaks", getBreaks())
            .append("rebound", getRebound())
            .append("block", getBlock())
            .append("isDeleted", getIsDeleted())
            .append("createdBy", getCreatedBy())
            .append("modifiedBy", getModifiedBy())
            .append("createdTime", getCreatedTime())
            .append("lastUpdatedTime", getLastUpdatedTime())
            .append("status", getStatus())
            .append("userType", getUserType())
            .append("competitionOfTeamId", getCompetitionOfTeamId())
            .append("competitionNature", getCompetitionNature())
            .append("realName", getRealName())
            .append("jerseyNumber", getJerseyNumber())
            .append("idType", getIdType())
            .append("idCardNo", getIdCardNo())
            .append("contactsTel", getContactsTel())
            .append("contacts", getContacts())
            .append("contactsAreaCode", getContactsAreaCode())
            .append("personalPhoto", getPersonalPhoto())
            .append("isFirstLaunch", getIsFirstLaunch())
            .toString();
    }
}
