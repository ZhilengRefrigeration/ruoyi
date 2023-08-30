package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 【请填写功能名称】对象 message
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Data
@TableName("message")
public class Message extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
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

}
