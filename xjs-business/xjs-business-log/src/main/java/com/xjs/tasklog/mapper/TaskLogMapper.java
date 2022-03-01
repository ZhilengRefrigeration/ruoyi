package com.xjs.tasklog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.tasklog.domain.TaskLog;

import java.util.List;

/**
 * 任务日志mapper
 * @author xiejs
 * @since 2022-03-01
 */
public interface TaskLogMapper extends BaseMapper<TaskLog> {

    //-------------------------代码生成--------------------------

    /**
     * 查询任务日志列表
     *
     * @param taskLog 任务日志
     * @return 任务日志集合
     */
    public List<TaskLog> selectTaskLogList(TaskLog taskLog);

    /**
     * 删除任务日志
     *
     * @param id 任务日志主键
     * @return 结果
     */
    public int deleteTaskLogById(Long id);

    /**
     * 批量删除任务日志
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskLogByIds(Long[] ids);
}
