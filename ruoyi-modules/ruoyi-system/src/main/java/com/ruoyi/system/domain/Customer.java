package com.ruoyi.system.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 客户信息对象 f_customer
 * 
 * @author ruoyi
 * @date 2023-05-06
 */
@Data
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户ID */
    private Long id;

    /** 客户名 */
    @Excel(name = "客户名")
    private String userName;

    /** 客户昵称 */
    @Excel(name = "客户昵称")
    private String nickName;

    /** 客户级别 */
    @Excel(name = "客户级别")
    private String userType;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phoneNumber;

    /** 客户性别 */
    @Excel(name = "客户性别")
    private String sex;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String avatar;

    /** 线索渠道 */
    @Excel(name = "线索渠道")
    private String clueChannel;

    /** 客户信息来源 */
    @Excel(name = "客户信息来源")
    private String dataSource;

    /** 客户居住 区县/区域 */
    @Excel(name = "客户居住 区县/区域")
    private String liveAddress;
    @Excel(name = "到店状态")
    private String storeStatus;
    /** 客户状态 */
    @Excel(name = "客户状态")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    private String loginIp;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loginDate;

    /** 微信号 */
    @Excel(name = "微信号")
    private String wechat;

    /** 购车类型 */
    @Excel(name = "购车类型")
    private String buyCarType;

    /** 置换/保有车型 */
    @Excel(name = "置换/保有车型")
    private String existModels;

    /** 是否评估 */
    @Excel(name = "是否评估")
    private Integer isAssessment;

    /** 意向车型 */
    @Excel(name = "意向车型")
    private String intentionCarModels;

    /** 对比车型 */
    @Excel(name = "对比车型")
    private String contrastCarModels;

    /** 是否试驾 */
    @Excel(name = "是否试驾")
    private Integer isTestDrive;

    /** 是否报价 */
    @Excel(name = "是否报价")
    private Integer isOffer;

    /** 是否金融 */
    @Excel(name = "是否金融")
    private Integer isFinance;

    /** 未订车原因 */
    @Excel(name = "未订车原因")
    private String unBookingCarReason;

    /** 预计到店时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计到店时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date preToStoreDate;

    /** 最后一次到店日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后一次到店日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastToStoreDate;

    /** 4S店 */
    @Excel(name = "4S店")
    private String storeName;

    /** 下单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 跟进模块-客户跟进记录信息 */
    private List<FollowUp> followUpList;
}
