package com.ruoyi.common.services;

import com.ruoyi.common.services.constants.JobLogStatus;
import com.ruoyi.common.services.domain.JobDataLog;

/**
 * 定时任务数据日志服务接口
 *
 * @author Alan Scipio
 * created on 2024/2/20
 */
public interface IJobDataLogService {

    /**
     * 添加日志
     */
    void addLog(JobDataLog log);

    default void addLog(JobLogStatus status, String logType, String message) {
        JobDataLog log = new JobDataLog();
        log.setStatus(status.getCode() + "");
        log.setLogType(logType);
        log.setMessage(message);
        addLog(log);
    }

    default void addLog(JobLogStatus status, String message) {
        addLog(status, null, message);
    }

}
