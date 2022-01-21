package com.xjs.apilog.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

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
    @Excel(name = "是否请求成功",readConverterExp = "1=成功,2=失败")
    private Integer isSuccess;

    @Excel(name = "创建时间" ,dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endCreateTime;
}
