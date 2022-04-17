package com.xjs.workflow.leave.controller;


import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.xjs.workflow.leave.domain.WorkflowLeave;
import com.xjs.workflow.leave.service.IWorkflowLeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 请假Controller
 *
 * @author xiejs
 * @since 2022-04-17 17:54:05
 */
@RestController
@RequestMapping("/workflow/leave")
@Api(tags = "工作流-请假流程")
public class WorkflowLeaveController extends BaseController {
    @Autowired
    private IWorkflowLeaveService workflowLeaveService;

    /**
     * 查询请假列表
     */
    @GetMapping("/list")
    @RequiresPermissions("workflow:leave:list")
    @ApiOperation("查询请假列表")
    public TableDataInfo list(WorkflowLeave workflowLeave) {
        startPage();
        workflowLeave.setCreateBy(SecurityUtils.getUsername());
        List<WorkflowLeave> list = workflowLeaveService.selectWorkflowLeaveAndTaskNameList(workflowLeave);
        return getDataTable(list);
    }

    /**
     * 查询请假列表
     */
    @GetMapping("/listAll")
    @RequiresPermissions("workflow:leave:list")
    @ApiOperation("查询请假列表")
    public TableDataInfo listAll(WorkflowLeave workflowLeave) {
        startPage();
        List<WorkflowLeave> list = workflowLeaveService.selectWorkflowLeaveList(workflowLeave);
        return getDataTable(list);
    }

    /**
     * 导出请假列表
     */
    @Log(title = "请假", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @RequiresPermissions("workflow:leave:export")
    @ApiOperation("导出请假列表")
    public void export(HttpServletResponse response, WorkflowLeave workflowLeave) {
        List<WorkflowLeave> list = workflowLeaveService.selectWorkflowLeaveList(workflowLeave);
        ExcelUtil<WorkflowLeave> util = new ExcelUtil<>(WorkflowLeave.class);
        util.exportExcel(response, list, "leave");
    }

    /**
     * 获取请假详细信息
     */
    @GetMapping(value = "/{id}")
    @RequiresPermissions("workflow:leave:query")
    @ApiOperation("获取请假详细信息id")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(workflowLeaveService.selectWorkflowLeaveById(id));
    }

    /**
     * 获取请假详细信息
     */
    @GetMapping(value = "ByInstanceId/{instanceId}")
    @RequiresPermissions("workflow:leave:query")
    @ApiOperation("获取请假详细信息InstanceId")
    public AjaxResult getInfoByInstanceId(@PathVariable("instanceId") String instanceId) {
        return AjaxResult.success(workflowLeaveService.selectWorkflowLeaveByInstanceId(instanceId));
    }

    /**
     * 新增请假
     */
    @RequiresPermissions("workflow:leave:add")
    @Log(title = "请假", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增请假")
    public AjaxResult add(@RequestBody WorkflowLeave workflowLeave) {
        return toAjax(workflowLeaveService.insertWorkflowLeave(workflowLeave));
    }

    /**
     * 修改请假
     */
    @Log(title = "请假", businessType = BusinessType.UPDATE)
    @ApiOperation("修改请假")
    @PutMapping
    @RequiresPermissions("workflow:leave:edit")
    public AjaxResult edit(@RequestBody WorkflowLeave workflowLeave) {
        return toAjax(workflowLeaveService.insertWorkflowLeave(workflowLeave));
    }

    /**
     * 删除请假
     */
    @RequiresPermissions("workflow:leave:remove")
    @Log(title = "请假", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除请假")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workflowLeaveService.deleteWorkflowLeaveByIds(ids));
    }
}
