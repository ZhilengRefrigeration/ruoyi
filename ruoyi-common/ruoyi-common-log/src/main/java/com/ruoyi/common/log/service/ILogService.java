package com.ruoyi.common.log.service;

import com.ruoyi.system.api.domain.SysOperLog;

/**
 * 调用日志服务接口
 *
 * @author ruoyi
 */
public interface ILogService {

    /**
     * 保存系统日志记录
     */
    public void saveSysLog(SysOperLog sysOperLog);
}
