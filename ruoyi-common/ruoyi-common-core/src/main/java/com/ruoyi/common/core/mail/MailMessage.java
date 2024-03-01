package com.ruoyi.common.core.mail;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 邮件发送参数
 *
 * @since 2022/8/17
 */
@Data
@Accessors(chain = true)
public class MailMessage {

    /**
     * 邮件头信息（标题、收件人、抄送等）
     */
    private MailMessageHead headInfo = new MailMessageHead();

    /**
     * 正文
     */
    private String content;

    /**
     * 正文里的多媒体文件
     */
    private Map<String, File> inLineMap;

    /**
     * 正文是否为html。true代表是
     */
    private boolean isHtml = false;

    /**
     * 附件
     */
    private final Map<String, File> attachments = new LinkedHashMap<>();

    public void clearHeadInfo() {
        headInfo.setFrom(null);
        headInfo.setTo(null);
        headInfo.setCc(null);
        headInfo.setSubject(null);
    }

    public MailMessage setFrom(String fromAddress) {
        headInfo.setFrom(fromAddress);
        return this;
    }

    public MailMessage setTo(String toAddress) {
        headInfo.setTo(toAddress);
        return this;
    }

    public MailMessage setCc(String cc) {
        headInfo.setCc(cc);
        return this;
    }

    public MailMessage setSubject(String subject) {
        headInfo.setSubject(subject);
        return this;
    }

    public String getFrom() {
        return headInfo.getFrom();
    }

    public String getTo() {
        return headInfo.getTo();
    }

    public String getCc() {
        return headInfo.getCc();
    }

    public String getSubject() {
        return headInfo.getSubject();
    }

    public MailMessage addAttachment(String fileKey, File attachment) {
        attachments.put(fileKey, attachment);
        return this;
    }

    public MailMessage addAttachment(File attachment) {
        attachments.put(attachment.getName(), attachment);
        return this;
    }

    public MailMessage addAttachments(List<File> attachments) {
        if (attachments != null) {
            for (File attachment : attachments) {
                this.attachments.put(attachment.getName(), attachment);
            }
        }
        return this;
    }

    public void clearAttachments() {
        attachments.clear();
    }

    public File getAttachment(String fileKey) {
        return attachments.get(fileKey);
    }

    public List<File> getAttachmentsList() {
        List<File> list = new ArrayList<>();
        for (Map.Entry<String, File> entry : attachments.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

}
