package com.xjs.tasklog.controller;

import com.ruoyi.common.core.domain.R;
import com.xjs.tasklog.domain.TaskLog;
import com.xjs.tasklog.service.TaskLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务日志controller
 * @author xiejs
 * @since 2022-03-01
 */
@RestController
@RequestMapping("taskLog")
@Api(tags = "业务模块-任务日志")
public class TaskLogController {

    @Autowired
    private TaskLogService taskLogService;


    //-----------------------内部调用rpc------------------------

    @PostMapping("saveForPRC")
    @ApiOperation("供AOP切面RPC远程调用")
    public R<Object> saveTaskLog(@RequestBody TaskLog taskLog) {
        boolean save = taskLogService.save(taskLog);
        return save ? R.ok() : R.fail();
    }
}
