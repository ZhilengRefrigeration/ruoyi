package com.xjs.reptileLog.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 爬虫日志实体类
 * @author xiejs
 * @since 2022-02-17
 */
@Data
public class WebmagicLog implements Serializable  {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 爬虫名称 */
    @Excel(name = "爬虫名称")
    private String name;

    /** 爬虫地址 */
    @Excel(name = "爬虫地址")
    private String url;

    /**
     * 复杂度
     */
    @Excel(name = "复杂度")
    private Long complexRate;


    @Excel(name = "执行结果",readConverterExp = "1=成功,2=失败")
    private Integer status;

    /** 请求耗费时间（单位毫秒） */
    @Excel(name = "请求耗费时间")
    private Long requestTime;

    @Excel(name = "创建时间" ,dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private Date endCreateTime;

    /**
     * 查询条件：耗费时间Start
     */
    @TableField(exist = false)
    private Long beginRequestTime;

    /**
     * 查询条件：耗费时间End
     */
    @TableField(exist = false)
    private Long endRequestTime;

}
