package com.ruoyi.common.core.mail;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 邮件头信息
 *
 * @since 2022/8/17
 */
@Data
@Accessors(chain = true)
public class MailMessageHead {

    /**
     * 发件人
     */
    private String from;

    /**
     * 收件人
     */
    private String to;

    /**
     * 抄送
     */
    private String cc;

    /**
     * 邮件标题(Subject)
     */
    private String subject;

}
