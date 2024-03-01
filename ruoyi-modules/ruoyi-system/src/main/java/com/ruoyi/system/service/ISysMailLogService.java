package com.ruoyi.system.service;

import com.ruoyi.common.core.mail.MailMessage;
import com.ruoyi.common.core.mail.MailSendResult;
import com.ruoyi.system.domain.SysMailLog;

import java.util.List;

/**
 * 邮件发送日志Service接口
 *
 * @author ryas
 * created on 2024-03-01
 */
public interface ISysMailLogService {
    /**
     * 查询邮件发送日志
     *
     * @param mailLogId 邮件发送日志主键
     * @return 邮件发送日志
     */
    SysMailLog selectSysMailLogByMailLogId(Long mailLogId);

    /**
     * 查询邮件发送日志列表
     *
     * @param sysMailLog 邮件发送日志
     * @return 邮件发送日志集合
     */
    List<SysMailLog> selectSysMailLogList(SysMailLog sysMailLog);

    /**
     * 新增邮件发送日志
     *
     * @param sysMailLog 邮件发送日志
     * @return 结果
     */
    int insertSysMailLog(SysMailLog sysMailLog);

    /**
     * 修改邮件发送日志
     *
     * @param sysMailLog 邮件发送日志
     * @return 结果
     */
    int updateSysMailLog(SysMailLog sysMailLog);

    /**
     * 批量删除邮件发送日志
     *
     * @param mailLogIds 需要删除的邮件发送日志主键集合
     * @return 结果
     */
    int deleteSysMailLogByMailLogIds(Long[] mailLogIds);

    /**
     * 删除邮件发送日志信息
     *
     * @param mailLogId 邮件发送日志主键
     * @return 结果
     */
    int deleteSysMailLogByMailLogId(Long mailLogId);

    /**
     * 发送临时邮件
     *
     * @param message 邮件内容
     * @return 结果
     */
    MailSendResult sendTempMail(MailMessage message);
}
