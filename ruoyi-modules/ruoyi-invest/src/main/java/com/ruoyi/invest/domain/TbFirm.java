package com.ruoyi.invest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 企业表
 * @TableName tb_firm
 */
@TableName(value ="tb_firm")
@Data
public class TbFirm implements Serializable {
    /**
     * 企业id
     */
    @TableId(type = IdType.AUTO)
    private Integer firmId;

    /**
     * 企业名称
     */
    private String firmName;

    /**
     * 企业logo
     */
    private String firmPic;

    /**
     * 企业市值
     */
    private Double firmMarket;

    /**
     * 企业描述
     */
    private String firmDesc;

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