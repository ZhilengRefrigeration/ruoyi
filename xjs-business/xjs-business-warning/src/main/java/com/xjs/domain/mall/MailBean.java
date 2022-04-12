package com.xjs.domain.mall;

import lombok.Data;

import java.io.Serializable;

/**
 * 邮箱发送实体
 *
 * @author xiejs
 * @since 2022-04-13
 */
@Data
public class MailBean implements Serializable {

    private static final long serialVersionUID = -2116367492649751914L;

    /**
     * 邮件接收人
     */
    private String recipient;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;

    /**
     * 附件地址
     */
    private String absolutePath;

}
