package com.xjs.apilog.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.xjs.validation.annotation.CheckNumber;
import com.xjs.validation.group.SelectGroup;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
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
    @Size(max = 20, message = "请控制接口名称长度在20字符", groups = { SelectGroup.class})
    private String apiName;

    /** 请求API的url */
    @Excel(name = "请求API的url")
    @Size(max = 100, message = "请控制请求API的url长度在100字符", groups = { SelectGroup.class})
    private String url;

    /** 请求API的方法 */
    @Excel(name = "请求API的方法")
    @Size(max = 10, message = "请控制请求API的方法长度在10字符", groups = { SelectGroup.class})
    private String method;

    /** 请求request */
    @Excel(name = "请求request")
    @Size(max = 1000, message = "请控制请求request长度在1000字符", groups = { SelectGroup.class})
    private String request;

    /** 响应体 */
    @Excel(name = "响应体")
    @Size(max = 15000, message = "请控制响应体长度在15000字符", groups = { SelectGroup.class})
    private String response;

    /** 是否请求成功 */
    @Excel(name = "是否请求成功",readConverterExp = "1=成功,2=失败")
    @CheckNumber(num= {1, 2}, groups = { SelectGroup.class})
    private Integer isSuccess;

    @Excel(name = "创建时间" ,dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endCreateTime;
}
