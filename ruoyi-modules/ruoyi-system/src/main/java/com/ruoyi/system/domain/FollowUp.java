package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 跟进模块-客户跟进记录对象 f_follow_up
 * 
 * @author ruoyi
 * @date 2023-05-07
 */
public class FollowUp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId;

    /** 跟进日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "跟进日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date followUpDate;

    /** 跟进记录 */
    @Excel(name = "跟进记录")
    private String followUpRecord;

    /** 再次预约到店日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "再次预约到店日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date preToStoreDate;

    /** 级别 */
    @Excel(name = "级别")
    private String followLevel;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }
    public void setFollowUpDate(Date followUpDate) 
    {
        this.followUpDate = followUpDate;
    }

    public Date getFollowUpDate() 
    {
        return followUpDate;
    }
    public void setFollowUpRecord(String followUpRecord) 
    {
        this.followUpRecord = followUpRecord;
    }

    public String getFollowUpRecord() 
    {
        return followUpRecord;
    }
    public void setPreToStoreDate(Date preToStoreDate) 
    {
        this.preToStoreDate = preToStoreDate;
    }

    public Date getPreToStoreDate() 
    {
        return preToStoreDate;
    }
    public void setFollowLevel(String followLevel) 
    {
        this.followLevel = followLevel;
    }

    public String getFollowLevel() 
    {
        return followLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerId", getCustomerId())
            .append("followUpDate", getFollowUpDate())
            .append("followUpRecord", getFollowUpRecord())
            .append("preToStoreDate", getPreToStoreDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("followLevel", getFollowLevel())
            .toString();
    }
}
