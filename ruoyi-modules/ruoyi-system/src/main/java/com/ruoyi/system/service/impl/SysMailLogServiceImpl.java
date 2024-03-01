package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.mail.MailMessage;
import com.ruoyi.common.core.mail.MailSendResult;
import com.ruoyi.common.core.mail.MailSender;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.uuid.snowflake.SnowFlakeIdGenerator;
import com.ruoyi.system.domain.SysMailLog;
import com.ruoyi.system.mapper.SysMailLogMapper;
import com.ruoyi.system.service.ISysMailLogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 邮件发送日志Service业务层处理
 *
 * @author ryas
 * created on 2024-03-01
 */
@Service
public class SysMailLogServiceImpl implements ISysMailLogService {

    @Resource
    private SysMailLogMapper sysMailLogMapper;

    @Resource
    private MailSender mailSender;

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
     * 发送临时邮件
     *
     * @param message 邮件内容
     * @return 结果
     */
    @Override
    public MailSendResult sendTempMail(MailMessage message) {
        //TODO 待完成
        return null;
    }
}
