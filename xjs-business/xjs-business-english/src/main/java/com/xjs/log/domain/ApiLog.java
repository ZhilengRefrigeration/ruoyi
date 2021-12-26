package com.xjs.log.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

import java.io.Serializable;

/**
 * 日志对象 api_log
 * 
 * @author xjs
 * @date 2021-12-26
 */
public class ApiLog implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 接口名称 */
    @Excel(name = "接口名称")
    private String apiName;

    /** 请求API的url */
    @Excel(name = "请求API的url")
    private String url;

    /** 请求API的方法 */
    @Excel(name = "请求API的方法")
    private String method;

    /** 请求request */
    @Excel(name = "请求request")
    private String request;

    /** 响应体 */
    @Excel(name = "响应体")
    private String response;

    /** 是否请求成功 */
    @Excel(name = "是否请求成功")
    private Integer isSuccess;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setApiName(String apiName) 
    {
        this.apiName = apiName;
    }

    public String getApiName() 
    {
        return apiName;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setMethod(String method) 
    {
        this.method = method;
    }

    public String getMethod() 
    {
        return method;
    }
    public void setRequest(String request) 
    {
        this.request = request;
    }

    public String getRequest() 
    {
        return request;
    }
    public void setResponse(String response) 
    {
        this.response = response;
    }

    public String getResponse() 
    {
        return response;
    }
    public void setIsSuccess(Integer isSuccess) 
    {
        this.isSuccess = isSuccess;
    }

    public Integer getIsSuccess() 
    {
        return isSuccess;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("apiName", getApiName())
            .append("url", getUrl())
            .append("method", getMethod())
            .append("request", getRequest())
            .append("response", getResponse())
            .append("isSuccess", getIsSuccess())
            .toString();
    }
}
