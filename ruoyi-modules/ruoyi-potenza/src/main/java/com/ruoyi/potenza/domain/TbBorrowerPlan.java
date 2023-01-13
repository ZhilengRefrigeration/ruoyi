package com.ruoyi.potenza.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 计划表
 * @TableName tb_borrower_plan
 */
@TableName(value ="tb_borrower_plan")
@Data
public class TbBorrowerPlan implements Serializable {
    /**
     * 还款结果ID
     */
    @TableId(type = IdType.AUTO)
    private Integer planId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 共几期
     */
    private Integer borrowerPeriods;

    /**
     * 每期应还金额
     */
    private Double periodsMoney;

    /**
     * 月供本金
     */
    private Double periodsCapital;

    /**
     * 月供利息
     */
    private Double periodsInterests;

    /**
     * 计划还款日期
     */
    private Date planDate;

    /**
     * 0:本月未还   1:提前还款  2:按时还款 3:逾期还款
     */
    private Integer planState;

    /**
     * 删除状态0：存在，2：删除
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}