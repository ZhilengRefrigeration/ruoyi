package com.xjs.server;

import com.xjs.domain.mall.MailBean;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮箱发送工具
 *
 * @author xiejs
 * @since 2022-04-13
 */
@Component
@Log4j2
public class MailServer {

    @Value("${spring.mail.username}")
    //邮件发送者
    private String MAIL_SENDER;

    @Resource
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;


    // todo 优化 邮箱发送失败重试机制、防止邮件被识别为垃圾邮件，固定时间内发送邮件的限制等。


    /**
     * 发送文本邮件
     *
     * @param mailBean 邮箱实体
     */
    public void sendSimpleMail(MailBean mailBean) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(MAIL_SENDER);
            mailMessage.setTo(mailBean.getRecipient());
            mailMessage.setSubject(mailBean.getSubject());
            mailMessage.setText(mailBean.getContent());

            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            log.error("文本邮件发送失败:{}", e.getMessage());
        }
    }

    /**
     * 发送HTML格式邮件
     *
     * @param mailBean 邮箱实体
     */
    public void sendHTMLMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            //true 表示需要创建一个multipart message
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            //邮件抄送
            //mimeMessageHelper.addCc("抄送人");
            mimeMessageHelper.setText(mailBean.getContent(), true);
            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            log.error("HTML格式邮件发送失败:{}", e.getMessage());
        }
    }


    /**
     * 附件格式邮件发送
     *
     * @param mailBean 邮箱实体
     */
    public void sendAttachmentMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            //true 表示需要创建一个multipart message
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent());
            //文件路径 目前写死在代码中，之后可以当参数传过来，或者在MailBean中添加属性absolutePath
            FileSystemResource file = new FileSystemResource(new File(mailBean.getAbsolutePath()));
            //FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/email.png"));
            String fileName = mailBean.getAbsolutePath().substring(mailBean.getAbsolutePath().lastIndexOf(File.separator));
            //添加附件,第一个参数表示添加到 Email 中附件的名称，第二个参数是图片资源
            mimeMessageHelper.addAttachment(fileName, file);
            //多个附件
            //mimeMessageHelper.addAttachment(fileName1, file1);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            log.error("附件格式邮件发送失败:{}", e.getMessage());
        }
    }


    /**
     * 静态资源格式邮件发送
     *
     * @param mailBean 邮箱实体
     */
    public void sendInlineMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent(), true);
            //文件路径
            FileSystemResource file = new FileSystemResource(new File(mailBean.getAbsolutePath()));
            //FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/email.png"))
            //添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和 mimeMessageHelper.addInline(rscId, res) 来实现
            mimeMessageHelper.addInline("picture", file);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            log.error("静态资源格式邮件发送失败:{}", e.getMessage());
        }
    }

    /**
     * 发送Thymeleaf模版邮件
     *
     * @param recipient 接受者邮箱
     * @param name      用户名称
     * @param title     主体
     */
    public void sendTempLateMail(String recipient, String name, String title) {
        //注意：Context 类是在org.thymeleaf.context.Context包下的。
        Context context = new Context();
        //html中填充动态属性值
        context.setVariable("username", name);
        context.setVariable("url", "#");
        //注意：process第一个参数名称要和templates下的模板名称一致。要不然会报错
        //org.thymeleaf.exceptions.TemplateInputException: Error resolving template [email]
        String emailContent = templateEngine.process("email", context);
        MailBean mailBean = new MailBean();
        mailBean.setRecipient(recipient);
        mailBean.setSubject(title);
        mailBean.setContent(emailContent);
        this.sendHTMLMail(mailBean);
    }


}
