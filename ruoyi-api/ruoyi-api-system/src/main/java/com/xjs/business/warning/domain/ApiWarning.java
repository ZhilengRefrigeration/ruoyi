package com.xjs.business.warning.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * @author xiejs
 * @desc api预警实体类
 * @create 2022-01-07
 */

public class ApiWarning implements Serializable {
    /** id */
    private Long id;

    /** api名称 */
    private String apiName;

    /** 预警类型 */
    private String warningType;

    /** 预警等级 */
    private String warningLevel;

    /** 预警记录信息 */
    private String warningMessage;

    /** 限定值 */
    private String limitValue;

    /** 实际值 */
    private String realValue;

    /** 是否处理 1 处理 2未处理 */
    private Integer handle;

    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getWarningType() {
        return warningType;
    }

    public void setWarningType(String warningType) {
        this.warningType = warningType;
    }

    public String getWarningLevel() {
        return warningLevel;
    }

    public void setWarningLevel(String warningLevel) {
        this.warningLevel = warningLevel;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public String getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(String limitValue) {
        this.limitValue = limitValue;
    }

    public String getRealValue() {
        return realValue;
    }

    public void setRealValue(String realValue) {
        this.realValue = realValue;
    }

    public Integer getHandle() {
        return handle;
    }

    public void setHandle(Integer handle) {
        this.handle = handle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
