package com.xjs.business.warning.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiejs
 * @desc  api记录实体类
 * @create 2021-12-31
 */
public class ApiRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** api名称 */
    private String apiName;

    /** api地址 */
    private String apiUrl;

    /** api总请求次数 */
    private Long totalCount;

    /** api限制请求次数每天 */
    private Long limitCount;

    private Date createTime;

    private Date updateTime;

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

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Long limitCount) {
        this.limitCount = limitCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
