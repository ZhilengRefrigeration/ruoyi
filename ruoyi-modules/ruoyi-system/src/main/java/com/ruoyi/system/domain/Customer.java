package com.ruoyi.system.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setUserType(String userType) 
    {
        this.userType = userType;
    }

    public String getUserType() 
    {
        return userType;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setClueChannel(String clueChannel) 
    {
        this.clueChannel = clueChannel;
    }

    public String getClueChannel() 
    {
        return clueChannel;
    }
    public void setDataSource(String dataSource) 
    {
        this.dataSource = dataSource;
    }

    public String getDataSource() 
    {
        return dataSource;
    }
    public void setLiveAddress(String liveAddress) 
    {
        this.liveAddress = liveAddress;
    }

    public String getLiveAddress() 
    {
        return liveAddress;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setLoginIp(String loginIp) 
    {
        this.loginIp = loginIp;
    }

    public String getLoginIp() 
    {
        return loginIp;
    }
    public void setLoginDate(Date loginDate) 
    {
        this.loginDate = loginDate;
    }

    public Date getLoginDate() 
    {
        return loginDate;
    }
    public void setWechat(String wechat) 
    {
        this.wechat = wechat;
    }

    public String getWechat() 
    {
        return wechat;
    }
    public void setBuyCarType(String buyCarType) 
    {
        this.buyCarType = buyCarType;
    }

    public String getBuyCarType() 
    {
        return buyCarType;
    }
    public void setExistModels(String existModels) 
    {
        this.existModels = existModels;
    }

    public String getExistModels() 
    {
        return existModels;
    }
    public void setIsAssessment(Integer isAssessment) 
    {
        this.isAssessment = isAssessment;
    }

    public Integer getIsAssessment() 
    {
        return isAssessment;
    }
    public void setIntentionCarModels(String intentionCarModels) 
    {
        this.intentionCarModels = intentionCarModels;
    }

    public String getIntentionCarModels() 
    {
        return intentionCarModels;
    }
    public void setContrastCarModels(String contrastCarModels) 
    {
        this.contrastCarModels = contrastCarModels;
    }

    public String getContrastCarModels() 
    {
        return contrastCarModels;
    }
    public void setIsTestDrive(Integer isTestDrive) 
    {
        this.isTestDrive = isTestDrive;
    }

    public Integer getIsTestDrive() 
    {
        return isTestDrive;
    }
    public void setIsOffer(Integer isOffer) 
    {
        this.isOffer = isOffer;
    }

    public Integer getIsOffer() 
    {
        return isOffer;
    }
    public void setIsFinance(Integer isFinance) 
    {
        this.isFinance = isFinance;
    }

    public Integer getIsFinance() 
    {
        return isFinance;
    }
    public void setUnBookingCarReason(String unBookingCarReason) 
    {
        this.unBookingCarReason = unBookingCarReason;
    }

    public String getUnBookingCarReason() 
    {
        return unBookingCarReason;
    }
    public void setPreToStoreDate(Date preToStoreDate) 
    {
        this.preToStoreDate = preToStoreDate;
    }

    public Date getPreToStoreDate() 
    {
        return preToStoreDate;
    }
    public void setLastToStoreDate(Date lastToStoreDate) 
    {
        this.lastToStoreDate = lastToStoreDate;
    }

    public Date getLastToStoreDate() 
    {
        return lastToStoreDate;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
    }
    public void setOrderDate(Date orderDate) 
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() 
    {
        return orderDate;
    }

    public List<FollowUp> getFollowUpList()
    {
        return followUpList;
    }

    public void setFollowUpList(List<FollowUp> followUpList)
    {
        this.followUpList = followUpList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("nickName", getNickName())
            .append("userType", getUserType())
            .append("email", getEmail())
            .append("phoneNumber", getPhoneNumber())
            .append("sex", getSex())
            .append("avatar", getAvatar())
            .append("clueChannel", getClueChannel())
            .append("dataSource", getDataSource())
            .append("liveAddress", getLiveAddress())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("loginIp", getLoginIp())
            .append("loginDate", getLoginDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("wechat", getWechat())
            .append("buyCarType", getBuyCarType())
            .append("existModels", getExistModels())
            .append("isAssessment", getIsAssessment())
            .append("intentionCarModels", getIntentionCarModels())
            .append("contrastCarModels", getContrastCarModels())
            .append("isTestDrive", getIsTestDrive())
            .append("isOffer", getIsOffer())
            .append("isFinance", getIsFinance())
            .append("unBookingCarReason", getUnBookingCarReason())
            .append("preToStoreDate", getPreToStoreDate())
            .append("lastToStoreDate", getLastToStoreDate())
            .append("storeName", getStoreName())
            .append("orderDate", getOrderDate())
            .append("followUpList", getFollowUpList())
            .toString();
    }
}
