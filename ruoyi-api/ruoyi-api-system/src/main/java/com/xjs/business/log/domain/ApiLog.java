package com.xjs.business.log.domain;




import java.io.Serializable;


public class ApiLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 接口名称
     */
    private String apiName;

    /**
     * 请求API的url
     */
    private String url;

    /**
     * 请求API的方法
     */
    private String method;

    /**
     * 请求request
     */
    private String request;

    /**
     * 响应体
     */
    private String response;

    /**
     * 是否请求成功
     */
    private Integer isSuccess;

    /**
     * 创建时间
     */
    private String createTime;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
