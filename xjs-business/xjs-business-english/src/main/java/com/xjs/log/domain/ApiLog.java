package com.xjs.log.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
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
@Data
@TableName("api_log")
public class ApiLog implements Serializable
{
    private static final long serialVersionUID = 1L;

    @TableId
    @JsonSerialize(using= ToStringSerializer.class)
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
}
