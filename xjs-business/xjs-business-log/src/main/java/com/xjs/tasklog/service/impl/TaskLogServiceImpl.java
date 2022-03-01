package com.xjs.tasklog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.tasklog.domain.TaskLog;
import com.xjs.tasklog.mapper.TaskLogMapper;
import com.xjs.tasklog.service.TaskLogService;
import org.springframework.stereotype.Service;

/**
 * 任务日志service实现
 * @author xiejs
 * @since 2022-03-01
 */
@Service
public class TaskLogServiceImpl extends ServiceImpl<TaskLogMapper, TaskLog> implements TaskLogService {
}
