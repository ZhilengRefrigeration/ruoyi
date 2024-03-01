package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysMailLog;

import java.util.List;

/**
 * 邮件发送日志Mapper接口
 *
 * @author ryas
 * created on 2024-03-01
 */
public interface SysMailLogMapper {
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
     * 删除邮件发送日志
     *
     * @param mailLogId 邮件发送日志主键
     * @return 结果
     */
    int deleteSysMailLogByMailLogId(Long mailLogId);

    /**
     * 批量删除邮件发送日志
     *
     * @param mailLogIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysMailLogByMailLogIds(Long[] mailLogIds);
}
