package com.ruoyi.common.core.mail;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.File;
import java.util.List;

/**
 * 邮件发送工具
 * date 2021/8/18
 */
public interface MailSender {

    String ADDRESS_SPLIT = ";";

    //======================================= ↓↓↓ 快速构建 ↓↓↓ =======================================

    /**
     * 构建发送工具
     *
     * @param senderAccount 发送账号
     * @param enableDebug   是否开启debug信息输出
     */
    static MailSender build(MailSendAccount senderAccount, int timeout, boolean enableDebug) {
        return new SpringMailSender(senderAccount, timeout, enableDebug);
    }

    static MailSender build(MailSendAccount senderAccount, boolean enableDebug) {
        return new SpringMailSender(senderAccount, 20000, enableDebug);
    }

    static MailSender build(MailSendAccount senderAccount) {
        return new SpringMailSender(senderAccount);
    }

    //======================================= ↓↓↓ 主要API ↓↓↓ =======================================

    /**
     * 发送MIME邮件
     *
     * @param form 发送参数
     * @return 发送结果
     */
    MailSendResult sendMail(MailMessage form);

    default MailSendResult sendMail(String from, String to, String cc, String subject, String text, List<File> attachments) {
        MailMessage message = new MailMessage()
                .setFrom(from)
                .setTo(to)
                .setCc(cc)
                .setSubject(subject)
                .setContent(text)
                .addAttachments(attachments);
        return sendMail(message);
    }

    default MailSendResult sendMail(String to, String cc, String subject, String text, List<File> attachments) {
        return sendMail(null, to, cc, subject, text, attachments);
    }

    default MailSendResult sendMail(String to, String subject, String text, List<File> attachments) {
        return sendMail(null, to, null, subject, text, attachments);
    }

    default MailSendResult sendMail(String to, String cc, String subject, String text) {
        return sendMail(null, to, cc, subject, text, null);
    }

    default MailSendResult sendMail(String to, String subject, String text) {
        return sendMail(null, to, null, subject, text, null);
    }

    //======================================= ↓↓↓ 其他API ↓↓↓ =======================================

    MailSendAccount getSenderAccount();

    JavaMailSenderImpl getExecutor();

    void resetSenderAccount(MailSendAccount senderAccount);

    default void resetSenderAccount(String host, int port, String username, String password) {
        resetSenderAccount(new MailSendAccount(host, port, username, password, (password != null && !password.isEmpty())));
    }

    boolean available();

}
