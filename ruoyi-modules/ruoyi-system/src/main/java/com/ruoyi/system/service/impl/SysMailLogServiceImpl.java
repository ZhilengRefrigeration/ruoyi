package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.mail.MailMessage;
import com.ruoyi.common.core.mail.MailSendAccount;
import com.ruoyi.common.core.mail.MailSendResult;
import com.ruoyi.common.core.mail.MailSender;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.uuid.snowflake.SnowFlakeIdGenerator;
import com.ruoyi.system.config.MailConfig;
import com.ruoyi.system.domain.SysMailLog;
import com.ruoyi.system.domain.vo.MailVo;
import com.ruoyi.system.mapper.SysMailLogMapper;
import com.ruoyi.system.service.ISysMailLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 邮件发送日志Service业务层处理
 *
 * @author ryas
 * created on 2024-03-01
 */
@Slf4j
@Service
public class SysMailLogServiceImpl implements ISysMailLogService {

    @Resource
    private SysMailLogMapper sysMailLogMapper;

    @Resource
    private MailSender mailSender;

    @Resource
    private MailConfig mailConfig;

    /**
     * 查询邮件发送日志
     *
     * @param mailLogId 邮件发送日志主键
     * @return 邮件发送日志
     */
    @Override
    public SysMailLog selectSysMailLogByMailLogId(Long mailLogId) {
        return sysMailLogMapper.selectSysMailLogByMailLogId(mailLogId);
    }

    /**
     * 查询邮件发送日志列表
     *
     * @param sysMailLog 邮件发送日志
     * @return 邮件发送日志
     */
    @Override
    public List<SysMailLog> selectSysMailLogList(SysMailLog sysMailLog) {
        return sysMailLogMapper.selectSysMailLogList(sysMailLog);
    }

    /**
     * 新增邮件发送日志
     *
     * @param sysMailLog 邮件发送日志
     * @return 结果
     */
    @Override
    public int insertSysMailLog(SysMailLog sysMailLog) {
        sysMailLog.setCreateTime(DateUtils.getNowDate());
        if (sysMailLog.getMailLogId() == null) {
            sysMailLog.setMailLogId(SnowFlakeIdGenerator.nextIdLong());
        }
        if (StringUtils.isNotBlank(sysMailLog.getMsg()) && sysMailLog.getMsg().length() > 500) {
            sysMailLog.setMsg(sysMailLog.getMsg().substring(0, 500));
        }
        return sysMailLogMapper.insertSysMailLog(sysMailLog);
    }

    /**
     * 修改邮件发送日志
     *
     * @param sysMailLog 邮件发送日志
     * @return 结果
     */
    @Override
    public int updateSysMailLog(SysMailLog sysMailLog) {
        if (StringUtils.isNotBlank(sysMailLog.getMsg()) && sysMailLog.getMsg().length() > 500) {
            sysMailLog.setMsg(sysMailLog.getMsg().substring(0, 500));
        }
        return sysMailLogMapper.updateSysMailLog(sysMailLog);
    }

    /**
     * 批量删除邮件发送日志
     *
     * @param mailLogIds 需要删除的邮件发送日志主键
     * @return 结果
     */
    @Override
    public int deleteSysMailLogByMailLogIds(Long[] mailLogIds) {
        return sysMailLogMapper.deleteSysMailLogByMailLogIds(mailLogIds);
    }

    /**
     * 删除邮件发送日志信息
     *
     * @param mailLogId 邮件发送日志主键
     * @return 结果
     */
    @Override
    public int deleteSysMailLogByMailLogId(Long mailLogId) {
        return sysMailLogMapper.deleteSysMailLogByMailLogId(mailLogId);
    }

    /**
     * 发送邮件 - 简易版
     *
     * @param mailVo 邮件内容
     * @return 结果
     */
    @Override
    public MailSendResult sendSimpleMail(MailVo mailVo) {
        // 参数校验
        if (StringUtils.isBlank(mailVo.getTo())) {
            return MailSendResult.failure("收件人不能为空");
        }
        if (StringUtils.isBlank(mailVo.getSubject())) {
            return MailSendResult.failure("邮件主题不能为空");
        }
        long startTime = System.currentTimeMillis();
        // 保存附件
        List<File> attachments;
        try {
            attachments = saveAttachments(mailVo.getAttachments());
        } catch (Exception e) {
            log.error("Failed to save mail attachments", e);
            return MailSendResult.failure("Failed to save attachments! " + e);
        }
        // 发送邮件
        MailMessage mailMessage = new MailMessage()
                .setFrom(mailVo.getFrom())
                .setTo(mailVo.getTo())
                .setCc(mailVo.getCc())
                .setSubject(mailVo.getSubject())
                .setContent(mailVo.getContent())
                .addAttachments(attachments);
        MailSendResult result = mailSender.sendMail(mailMessage);
        handleMailSendError(result);
        // 记录邮件发送日志
        addMailLog(mailVo, result, startTime);
        return result;
    }

    /**
     * 记录邮件发送日志
     *
     * @param mailVo    邮件内容
     * @param result    发送结果
     * @param startTime 发送开始时间
     */
    private void addMailLog(MailVo mailVo, MailSendResult result, Long startTime) {
        SysMailLog mailLog = new SysMailLog();
        mailLog.setBusinessType(mailVo.getBusinessType());
        mailLog.setFrom(mailVo.getFrom());
        mailLog.setTo(mailVo.getTo());
        mailLog.setCc(mailVo.getCc());
        mailLog.setSubject(mailVo.getSubject());
        mailLog.setCostTime(System.currentTimeMillis() - startTime);
        if (result.isSuccess()) {
            mailLog.setStatus(1L);
            mailLog.setMsg("Send successfully");
        } else {
            mailLog.setStatus(2L);
            mailLog.setMsg(result.getErrMsg());
        }
        int affectedRows = insertSysMailLog(mailLog);
        if (affectedRows == 0) {
            log.error("Failed to record mail log: {}", mailLog);
        } else {
            log.info("Mail send successfully, mailLogId: {}", mailLog.getMailLogId());
        }
    }

    /**
     * 保存附件
     * <p>
     * 路径：配置的根路径+年份+月份+文件名，同名文件会覆盖
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private List<File> saveAttachments(MultipartFile[] attachments) throws IOException {
        List<File> files = new ArrayList<>();
        if (attachments != null) {
            File rootDir = mailConfig.getAttachmentsSavedDir();
            LocalDate nowDate = LocalDate.now();
            File savedDir = new File(rootDir, nowDate.getYear() + File.separator + nowDate.getMonthValue());
            for (MultipartFile attachment : attachments) {
                try {
                    File file = new File(savedDir, Objects.requireNonNull(attachment.getOriginalFilename()));
                    if (!savedDir.exists()) {
                        savedDir.mkdirs();
                    }
                    if (file.exists()) {
                        // 删除已存在的同名附件
                        log.warn("Mail attachment file already exists, delete it: [{}]", file.getAbsolutePath());
                        file.delete();
                    }
                    attachment.transferTo(file);
                    log.info("Mail attachment saved to server locally: [{}]", file.getAbsolutePath());
                    files.add(file);
                } catch (IOException e) {
                    // 删除已保存的附件
                    for (File f : files) {
                        f.delete();
                    }
                    throw e;
                }
            }
        }
        return files;
    }

    /**
     * 进一步处理邮件发送异常
     */
    private void handleMailSendError(MailSendResult result) {
        Throwable e = result.getErrObj();
        if (e == null) {
            return;
        }
        log.error("Failed to send mail", e);
        String msg = e.getMessage();
        if (e instanceof MailSendException) {
            if (msg != null && msg.toLowerCase().startsWith("mail server connection failed")) {
                result.setErrMsg("邮件服务器连接失败! 请检查邮件服务器地址和端口是否正确");
            } else if (msg != null && msg.toLowerCase().startsWith("mail server authentication failed")) {
                result.setErrMsg("邮件服务器认证失败! 请检查配置的用户名和密码是否正确");
            } else {
                result.setErrMsg("邮件发送失败: " + msg);
            }
        } else {
            result.setErrMsg("邮件发送失败，未知异常: " + msg);
        }
    }

    /**
     * 获取邮件发送账户信息
     */
    @Override
    public MailSendAccount getMailSenderInfo() {
        MailSendAccount result = new MailSendAccount();
        MailSendAccount senderAccount = mailSender.getSenderAccount();
        result.setHost(senderAccount.getHost());
        result.setPort(senderAccount.getPort());
        result.setUsername(senderAccount.getUsername());
        result.setSslFlag(senderAccount.isSslFlag());
        return result;
    }
}
