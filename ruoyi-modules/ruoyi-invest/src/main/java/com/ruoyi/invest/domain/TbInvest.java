package com.ruoyi.invest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 投资表
 * @TableName tb_invest
 */
@TableName(value ="tb_invest")
@Data
public class TbInvest implements Serializable {
    /**
     * 投资id
     */
    @TableId(type = IdType.AUTO)
    private Integer investId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 企业id
     */
    private Integer firmId;

    /**
     * 投资金额
     */
    private Double investAmount;

    /**
     * 投资收益
     */
    private Double investYield;

    /**
     * 投资状态0:手动投资1:自动投资
     */
    private Integer investState;

    /**
     * 删除状态0:存在2:删除
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}