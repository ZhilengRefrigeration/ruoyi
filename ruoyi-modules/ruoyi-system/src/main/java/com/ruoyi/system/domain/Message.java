package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 message
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public class Message extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createdBy;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date createdTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String modifiedBy;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date lastUpdatedTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer isDeleted;

    /** 消息标题 */
    @Excel(name = "消息标题")
    private String messageTitle;

    /** 消息类型【字典】 */
    @Excel(name = "消息类型【字典】")
    private String messageType;

    /** 业务标识 */
    @Excel(name = "业务标识")
    private String flowType;

    /** 审核人 */
    @Excel(name = "审核人")
    private Long auditor;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditDate;

    /** 来源id */
    @Excel(name = "来源id")
    private Long sourceId;

    /** 是否同意【0.拒绝 1.同意】 */
    @Excel(name = "是否同意【0.拒绝 1.同意】")
    private Integer agreeFlag;

    /** 流程参数【json】 */
    @Excel(name = "流程参数【json】")
    private String flowEntity;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
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
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }
    public void setMessageTitle(String messageTitle) 
    {
        this.messageTitle = messageTitle;
    }

    public String getMessageTitle() 
    {
        return messageTitle;
    }
    public void setMessageType(String messageType) 
    {
        this.messageType = messageType;
    }

    public String getMessageType() 
    {
        return messageType;
    }
    public void setFlowType(String flowType) 
    {
        this.flowType = flowType;
    }

    public String getFlowType() 
    {
        return flowType;
    }
    public void setAuditor(Long auditor) 
    {
        this.auditor = auditor;
    }

    public Long getAuditor() 
    {
        return auditor;
    }
    public void setAuditDate(Date auditDate) 
    {
        this.auditDate = auditDate;
    }

    public Date getAuditDate() 
    {
        return auditDate;
    }
    public void setSourceId(Long sourceId) 
    {
        this.sourceId = sourceId;
    }

    public Long getSourceId() 
    {
        return sourceId;
    }
    public void setAgreeFlag(Integer agreeFlag) 
    {
        this.agreeFlag = agreeFlag;
    }

    public Integer getAgreeFlag() 
    {
        return agreeFlag;
    }
    public void setFlowEntity(String flowEntity) 
    {
        this.flowEntity = flowEntity;
    }

    public String getFlowEntity() 
    {
        return flowEntity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("modifiedBy", getModifiedBy())
            .append("lastUpdatedTime", getLastUpdatedTime())
            .append("isDeleted", getIsDeleted())
            .append("messageTitle", getMessageTitle())
            .append("messageType", getMessageType())
            .append("flowType", getFlowType())
            .append("auditor", getAuditor())
            .append("auditDate", getAuditDate())
            .append("sourceId", getSourceId())
            .append("agreeFlag", getAgreeFlag())
            .append("flowEntity", getFlowEntity())
            .toString();
    }
}
