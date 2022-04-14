package com.xjs.domain.mall;

import java.io.Serializable;

/**
 * 邮件数据传输对象
 * @author xiejs
 * @since 2022-04-14
 */

public class MailVo implements Serializable {
    private static final long serialVersionUID = -1L;

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

    @Override
    public String toString() {
        return "MailVo{" +
                "recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
