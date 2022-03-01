package com.xjs.tasklog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.tasklog.domain.TaskLog;

import java.util.List;

/**
 * 任务日志service
 * @author xiejs
 * @since 2022-03-01
 */
public interface TaskLogService extends IService<TaskLog> {

    //-----------------------代码生成------------------------------

    /**
     * 查询任务日志列表
     *
     * @param taskLog 任务日志
     * @return 任务日志集合
     */
    public List<TaskLog> selectTaskLogList(TaskLog taskLog);

    /**
     * 批量删除任务日志
     *
     * @param ids 需要删除的任务日志主键集合
     * @return 结果
     */
    public int deleteTaskLogByIds(Long[] ids);

    /**
     * 删除任务日志信息
     *
     * @param id 任务日志主键
     * @return 结果
     */
    public int deleteTaskLogById(Long id);


}
