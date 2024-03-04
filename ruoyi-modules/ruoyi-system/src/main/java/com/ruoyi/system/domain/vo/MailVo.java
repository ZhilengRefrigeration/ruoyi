package com.ruoyi.system.domain.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Alan Scipio
 * created on 2024/3/4
 */
@Data
public class MailVo {

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 发送人
     */
    private String from;

    /**
     * 接收人
     */
    private String to;

    /**
     * 抄送人
     */
    private String cc;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 附件
     */
    private MultipartFile[] attachments;

}
