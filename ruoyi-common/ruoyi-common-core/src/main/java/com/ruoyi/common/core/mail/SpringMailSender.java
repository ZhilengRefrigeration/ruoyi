package com.ruoyi.common.core.mail;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;
import java.util.Map;
import java.util.Properties;

/**
 * 邮件发送器
 *
 * @since 2021/8/18
 */
public class SpringMailSender implements MailSender {

    //发送执行者
    private final JavaMailSenderImpl executor;

    private MailSendAccount senderAccount;

    public SpringMailSender(MailSendAccount senderAccount) {
        this(senderAccount, 20000, false);
    }

    public SpringMailSender(MailSendAccount senderAccount, int timeout, boolean enableDebug) {
        this.senderAccount = senderAccount;
        //创建发送执行者
        executor = buildExecutor(senderAccount, timeout, enableDebug);
    }

    /**
     * 创建发送执行者
     *
     * @param account 发送账号
     * @return 发送执行者
     */
    private JavaMailSenderImpl buildExecutor(MailSendAccount account, int timeout, boolean enableDebug) {
        //邮箱设定
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        //邮件发送时输出debug信息
        mailProperties.setProperty("mail.debug", enableDebug ? "true" : "false");
        //发送服务器需要身份验证
        mailProperties.setProperty("mail.smtp.auth", "true");
        //发送邮件协议名称 这里使用的是smtp协议
        mailProperties.setProperty("mail.transport.protocol", "smtp");
        //默认开启starttls
        mailProperties.setProperty("mail.smtp.starttls.enable", "true");
        //超时设置
        mailProperties.setProperty("mail.smtp.connectiontimeout", timeout + "");//与邮件服务器建立连接的超时
        mailProperties.setProperty("mail.smtp.writetimeout", timeout + "");//邮件发送时间限制
        javaMailSender.setDefaultEncoding("UTF-8");
        System.setProperty("mail.mime.splitlongparameters", "false"); //注意：不截断base64编码后的长附件名
        //设置认证信息
        setAuthInfo(javaMailSender, account, mailProperties);
        return javaMailSender;
    }

    private void setAuthInfo(JavaMailSenderImpl executor, MailSendAccount account, Properties mailProperties) {
        if (mailProperties == null) {
            mailProperties = executor.getJavaMailProperties();
        }
        if (account.isSslFlag()) {
            //开启ssl
            System.out.println("****** enable ssl for mail send ******");
            mailProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            mailProperties.setProperty("mail.smtp.socketFactory.port", account.getPort().toString());
            mailProperties.setProperty("mail.smtp.ssl.enable", "true");
        } else {
            System.out.println("****** disable ssl for mail send ******");
            executor.setPort(account.getPort() == null ? 25 : account.getPort());
        }
        executor.setJavaMailProperties(mailProperties);
        executor.setHost(account.getHost());
        executor.setUsername(account.getUsername());
        executor.setPassword(account.getPassword());
    }

    /**
     * 创建邮件信息
     *
     * @param executor 发送执行者
     * @param form     发送参数
     * @return 邮件信息
     * @throws MessagingException 邮件信息创建失败
     */
    private MimeMessage createMimeMessage(JavaMailSenderImpl executor, MailMessage form) throws MessagingException {
        MailMessageHead headInfo = form.getHeadInfo();
        Map<String, File> inLineMap = form.getInLineMap();
        Map<String, File> attachments = form.getAttachments();

        MimeMessage mimeMessage = executor.createMimeMessage();
        //创建发送MIME邮件的工具类
        MimeMessageHelper messageHelper = getMimeMessageHelper(form, mimeMessage, headInfo);
        //设置正文多媒体信息
        if (inLineMap != null && !inLineMap.isEmpty()) {
            for (Map.Entry<String, File> inLine : inLineMap.entrySet()) {
                messageHelper.addInline(inLine.getKey(), inLine.getValue());
            }
        }
        //添加附件
        if (attachments != null && !attachments.isEmpty()) {
            for (Map.Entry<String, File> entry : attachments.entrySet()) {
                String fileName = entry.getKey();
                File file = entry.getValue();
                messageHelper.addAttachment(fileName, file);
            }
        }
        return mimeMessage;
    }

    private MimeMessageHelper getMimeMessageHelper(MailMessage form, MimeMessage mimeMessage, MailMessageHead headInfo) throws MessagingException {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        //发件人
        if (form.getFrom() == null || form.getFrom().isEmpty()) {
            form.setFrom(senderAccount.getUsername());
        }
        messageHelper.setFrom(form.getFrom());
        //收件人 这里的参数可以是多个收件人，用英文分号分割
        messageHelper.setTo(headInfo.getTo().split(ADDRESS_SPLIT));
        //抄送 这里的参数可以是多个抄送人，用英文分号分割
        if (headInfo.getCc() != null && !headInfo.getCc().isEmpty()) {
            messageHelper.setCc(headInfo.getCc().split(ADDRESS_SPLIT));
        }
        //邮件主题
        messageHelper.setSubject(headInfo.getSubject());
        //邮件正文
        if (form.getContent() != null && !form.getContent().isEmpty()) {
            messageHelper.setText(form.getContent(), form.isHtml());
        } else {
            messageHelper.setText("");
        }
        return messageHelper;
    }

    /**
     * 发送邮件
     *
     * @param form 发送参数
     * @return 发送结果
     */
    @Override
    public MailSendResult sendMail(MailMessage form) {
        try {
            MimeMessage mimeMessage = createMimeMessage(executor, form);
            executor.send(mimeMessage);
            return MailSendResult.success();
        } catch (Exception e) {
            return MailSendResult.failure(e);
        }
    }

    @Override
    public MailSendAccount getSenderAccount() {
        return senderAccount;
    }

    @Override
    public JavaMailSenderImpl getExecutor() {
        return executor;
    }

    @Override
    public void resetSenderAccount(MailSendAccount senderAccount) {
        checkAccount(senderAccount);
        this.senderAccount = senderAccount;
        setAuthInfo(executor, senderAccount, null);
    }

    private void checkAccount(MailSendAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        }
        if (account.getHost() == null || account.getHost().isEmpty()) {
            throw new IllegalArgumentException("host is blank");
        }
        if (account.getPort() == null) {
            throw new IllegalArgumentException("port is null");
        }
        if (account.getPort() <= 0 || account.getPort() > 65535) {
            throw new IllegalArgumentException("port is invalid (0-65535)");
        }
    }

    @Override
    public boolean available() {
        return (senderAccount.getHost() != null && !senderAccount.getHost().isEmpty() ) && senderAccount.getPort() != null;
    }
}
