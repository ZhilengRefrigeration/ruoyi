package com.ruoyi.common.services;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.uuid.snowflake.SnowFlakeIdGenerator;
import com.ruoyi.common.services.domain.JobDataLog;
import com.ruoyi.common.services.mapper.JobDataLogMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 定时任务数据日志服务接口
 *
 * @author Alan Scipio
 * created on 2024/2/20
 */
@Service
public class JobDataLogServiceImpl implements IJobDataLogService {

    @Resource
    private JobDataLogMapper jobDataLogMapper;

    @Override
    public void addLog(JobDataLog log) {
        if (StringUtils.isBlank(log.getMessage())) {
            throw new IllegalArgumentException("message cannot be blank");
        }
        if (StringUtils.isBlank(log.getStatus())) {
            throw new IllegalArgumentException("status cannot be blank");
        }
        if (log.getStatus().length() > 1) {
            throw new IllegalArgumentException("status length must be 1");
        }
        if (log.getLogId() == null) {
            log.setLogId(SnowFlakeIdGenerator.nextIdLong());
        }
//        if (log.getJobClass() == null) {
//            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//            StackTraceElement caller = stackTraceElements[2];
//            log.setJobClass(caller.getClassName());
//            log.setJobMethod(caller.getMethodName());
//        }
        jobDataLogMapper.insertSelective(log);
    }
}
