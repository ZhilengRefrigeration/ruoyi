package com.xjs.business.log.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件日志实体类
 * @author xiejs
 * @since 2022-04-13
 */
@Data
public class MailLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 邮件标题 */
    private String title;

    /** 邮件内容 */
    private String content;

    /** 收件人 */
    private String recipient;

    /**
     * 邮件类型
     */
    private String mailType;

    /** 请求时间 */
    private Long requestTime;

    /**
     * 创建时间
     */
    private Date createTime;
}
