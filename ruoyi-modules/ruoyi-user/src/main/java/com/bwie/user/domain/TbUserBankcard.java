package com.bwie.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 账户表
 * @TableName tb_user_bankcard
 */
@TableName(value ="tb_user_bankcard")
@Data
public class TbUserBankcard implements Serializable {
    /**
     * 账户ID
     */
    @TableId(type = IdType.AUTO)
    private Integer bankcardId;

    /**
     * 账户昵称
     */
    private String brankcardName;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 账户金额
     */
    private Integer bankcardMoney;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 删除状态0：存在，2：删除
     */
    private Integer delFlag;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}