package com.ruoyi.system.config;

import com.ruoyi.common.core.mail.MailSendAccount;
import com.ruoyi.common.core.mail.MailSender;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SysConfigUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * 邮件配置
 *
 * @author Alan Scipio
 * created on 2024/2/29
 */
@Slf4j
@Configuration
public class MailConfig {

    @Value("${spring.mail.host:#{null}}")
    private String host;
    @Value("${spring.mail.port:#{null}}")
    private Integer port;
    @Value("${spring.mail.username:#{null}}")
    private String username;
    @Value("${spring.mail.password:#{null}}")
    private String password;

    @Getter
    private File attachmentsSavedDir;

    private MailSender mailSender;


    @Bean
    public MailSender mailSender() {
        MailSendAccount account = new MailSendAccount();

        // 加载配置文件中的邮件配置
        account.setHost(host);
        account.setPort(port);
        account.setUsername(username);
        account.setPassword(password);

        getFromCache(account);
        mailSender = MailSender.build(account, true);
        log.info("Mail configuration has been initialized. smtpHost: [{}], smtpPort: [{}], username: [{}]", account.getHost(), account.getPort(), account.getUsername());

        return mailSender;
    }

    /**
     * 从缓存中获取邮件配置
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void getFromCache(MailSendAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("MailSendAccount is null");
        }
        // 加载数据库中的邮件配置（会缓存到redis中，并覆盖前面的配置）
        String hostCache = SysConfigUtils.getConfigCache("sys.mail.smtpHost");
        String portCache = SysConfigUtils.getConfigCache("sys.mail.smtpPort");
        String usernameCache = SysConfigUtils.getConfigCache("sys.mail.username");
        String passwordCache = SysConfigUtils.getConfigCache("sys.mail.password");
        String attachmentsSavedPath = SysConfigUtils.getConfigCache("sys.mail.attachmentsSavedPath");
        if (StringUtils.isNotBlank(hostCache) && StringUtils.isNotBlank(portCache)) {
            account.setHost(hostCache);
            account.setPort(Integer.parseInt(portCache));
            if (StringUtils.isNotBlank(usernameCache)) {
                account.setUsername(usernameCache);
            }
            if (StringUtils.isNotBlank(passwordCache)) {
                account.setPassword(passwordCache);
            }
            if (StringUtils.isNotBlank(attachmentsSavedPath)) {
                attachmentsSavedDir = new File(attachmentsSavedPath);
                if (!attachmentsSavedDir.exists()) {
                    log.info("Mail attachments saved directory does not exist. Create a new one: [{}]", attachmentsSavedDir.getAbsolutePath());
                    attachmentsSavedDir.mkdirs();
                }
            }
        } else {
            log.warn("Mail configuration from database table 'sys_config' is empty. Use the configuration from application.yaml instead.");
        }

        account.setSslFlag(account.getPassword() != null && !account.getPassword().isEmpty());
    }

    /**
     * 刷新缓存里的邮件配置
     */
    public void refreshCache() {
        MailSendAccount account = mailSender.getSenderAccount();
        getFromCache(account);
        mailSender.resetSenderAccount(account);
    }

}
