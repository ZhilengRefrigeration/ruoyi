package com.xjs.tasklog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.apilog.domain.ApiLog;
import com.xjs.other.LogNumberVo;
import com.xjs.tasklog.domain.TaskLog;
import com.xjs.tasklog.mapper.TaskLogMapper;
import com.xjs.tasklog.service.TaskLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.xjs.consts.CommonConst.TODAY_END;
import static com.xjs.consts.CommonConst.TODAY_START;

/**
 * 任务日志service实现
 *
 * @author xiejs
 * @since 2022-03-01
 */
@Service
public class TaskLogServiceImpl extends ServiceImpl<TaskLogMapper, TaskLog> implements TaskLogService {
    @Resource
    private TaskLogMapper taskLogMapper;

    @Override
    public LogNumberVo getCount() {
        LogNumberVo logNumberVo = new LogNumberVo();

        long total = super.count();
        logNumberVo.setTotal(total);

        LambdaQueryWrapper<TaskLog> wrapper = new LambdaQueryWrapper<>();
        String startDate = DateUtil.today() + " "+TODAY_START;
        String endDate = DateUtil.today() + " "+TODAY_END;
        wrapper.between(TaskLog::getCreateTime, startDate, endDate);
        long todayCount = super.count(wrapper);
        logNumberVo.setTodayNumber(todayCount);

        return logNumberVo;
    }


    //----------------------------------代码生成------------------------------------

    /**
     * 查询任务日志列表
     *
     * @param taskLog 任务日志
     * @return 任务日志
     */
    @Override
    public List<TaskLog> selectTaskLogList(TaskLog taskLog) {
        return taskLogMapper.selectTaskLogList(taskLog);
    }


    /**
     * 批量删除任务日志
     *
     * @param ids 需要删除的任务日志主键
     * @return 结果
     */
    @Override
    public int deleteTaskLogByIds(Long[] ids) {
        return taskLogMapper.deleteTaskLogByIds(ids);
    }

    /**
     * 删除任务日志信息
     *
     * @param id 任务日志主键
     * @return 结果
     */
    @Override
    public int deleteTaskLogById(Long id) {
        return taskLogMapper.deleteTaskLogById(id);
    }

}
