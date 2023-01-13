package com.ruoyi.potenza.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 贷款表
 * @TableName tb_borrower
 */
@TableName(value ="tb_borrower")
@Data
public class TbBorrower implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer borrowerId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 产品ID 
     */
    private Integer productId;

    /**
     * 默认为最高可以借款金额
     */
    private Double borrowerMoney;

    /**
     * 贷款周期ID
     */
    private Integer periodsId;

    /**
     * 贷款方式ID 0:等额本息 6000   1:等额本金 6000 1000 第一个月60000*利率 第二个月 5000*利率
     */
    private Integer wayId;

    /**
     * 借款用途
     */
    private String borrowerPurpose;

    /**
     *   1： 待审核  2：贷款成功 3:贷款失败
     */
    private Integer borrowerState;

    /**
     * 放款人
     */
    private Integer loanerId;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

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