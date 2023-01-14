package com.ruoyi.potenza.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 贷款周期表
 * @TableName tb_borrower_periods
 */
@TableName(value ="tb_borrower_periods")
@Data
public class TbBorrowerPeriods implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer periodsId;

    /**
     * 周期:3 6 9 12 24
     */
    private Integer periodsName;

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