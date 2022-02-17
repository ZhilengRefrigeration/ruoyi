package com.xjs.business.log.domain;

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
    private String name;

    /** 爬虫地址 */
    private String url;

    /**
     * 复杂度
     */
    private Long complexRate;

    private Integer status;

    /** 请求耗费时间（单位毫秒） */
    private Long requestTime;

    private Date createTime;

}
