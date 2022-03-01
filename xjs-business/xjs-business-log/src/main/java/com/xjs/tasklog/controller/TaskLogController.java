package com.xjs.tasklog.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.tasklog.domain.TaskLog;
import com.xjs.tasklog.service.TaskLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 任务日志controller
 *
 * @author xiejs
 * @since 2022-03-01
 */
@RestController
@RequestMapping("taskLog")
@Api(tags = "业务模块-任务日志")
public class TaskLogController extends BaseController {

    @Autowired
    private TaskLogService taskLogService;


    //-----------------------内部调用rpc------------------------

    @PostMapping("saveForPRC")
    @ApiOperation("供AOP切面RPC远程调用")
    public R<Object> saveTaskLog(@RequestBody TaskLog taskLog) {
        boolean save = taskLogService.save(taskLog);
        return save ? R.ok() : R.fail();
    }


    //--------------------------代码生成----------------------------

    /**
     * 查询任务日志列表
     */
    @RequiresPermissions("log:taskLog:list")
    @GetMapping("/list")
    public TableDataInfo list(TaskLog taskLog) {
        startPage();
        List<TaskLog> list = taskLogService.selectTaskLogList(taskLog);
        return getDataTable(list);
    }

    /**
     * 导出任务日志列表
     */
    @RequiresPermissions("log:taskLog:export")
    @Log(title = "任务日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskLog taskLog) {
        List<TaskLog> list = taskLogService.selectTaskLogList(taskLog);
        ExcelUtil<TaskLog> util = new ExcelUtil<>(TaskLog.class);
        util.exportExcel(response, list, "任务日志数据");
    }


    /**
     * 删除任务日志
     */
    @RequiresPermissions("log:taskLog:remove")
    @Log(title = "任务日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(taskLogService.deleteTaskLogByIds(ids));
    }
}
