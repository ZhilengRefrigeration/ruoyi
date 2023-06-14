package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 微信用户小程序二维码对象 user_wx_aqr_code
 *
 * @author ruoyi
 * @date 2022-10-20
 */
public class UserWxAqrCode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 删除表示 */
    @Excel(name = "删除表示")
    private Long isDeleted;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 修改人 */
    @Excel(name = "修改人")
    private String modifiedBy;

    /** 最新更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最新更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedTime;

    /** 参数 */
    @Excel(name = "参数")
    private String scene;

    /** 二维码地址 */
    @Excel(name = "二维码地址")
    private String codeImgUrl;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 业务分类枚举 */
    @Excel(name = "业务分类枚举")
    private String busType;

    /** 页面路径 */
    @Excel(name = "页面路径")
    private String page;

    /** 宽度 */
    @Excel(name = "宽度")
    private Integer width;

    /** 用途说明 */
    @Excel(name = "用途说明")
    private String useDesc;

    /** 球衣号 */
    @Excel(name = "球衣号")
    private String jerseyNo;

    /** 球队ID */
    @Excel(name = "球队ID")
    private Long teamId;

    @Excel(name = "赛会参赛人员ID")
    private Long competitionMembersId;
    @Excel(name = "赛会参赛球队ID")
    private Long competitionOfTeamId;

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
    public void setScene(String scene)
    {
        this.scene = scene;
    }

    public String getScene()
    {
        return scene;
    }
    public void setCodeImgUrl(String codeImgUrl)
    {
        this.codeImgUrl = codeImgUrl;
    }

    public String getCodeImgUrl()
    {
        return codeImgUrl;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setBusType(String busType)
    {
        this.busType = busType;
    }

    public String getBusType()
    {
        return busType;
    }
    public void setPage(String page)
    {
        this.page = page;
    }

    public String getPage()
    {
        return page;
    }
    public void setWidth(Integer width)
    {
        this.width = width;
    }

    public Integer getWidth()
    {
        return width;
    }
    public void setUseDesc(String useDesc)
    {
        this.useDesc = useDesc;
    }

    public String getUseDesc()
    {
        return useDesc;
    }
    public void setJerseyNo(String jerseyNo)
    {
        this.jerseyNo = jerseyNo;
    }

    public String getJerseyNo()
    {
        return jerseyNo;
    }
    public void setTeamId(Long teamId)
    {
        this.teamId = teamId;
    }

    public Long getTeamId()
    {
        return teamId;
    }

    public Long getCompetitionMembersId() {
        return competitionMembersId;
    }

    public void setCompetitionMembersId(Long competitionMembersId) {
        this.competitionMembersId = competitionMembersId;
    }

    public Long getCompetitionOfTeamId() {
        return competitionOfTeamId;
    }

    public void setCompetitionOfTeamId(Long competitionOfTeamId) {
        this.competitionOfTeamId = competitionOfTeamId;
    }
}
