package com.xjs.activiti.controller;


import com.github.pagehelper.Page;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.PageDomain;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.core.web.page.TableSupport;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.activiti.domain.dto.ActTaskDTO;
import com.xjs.activiti.domain.dto.ActWorkflowFormDataDTO;
import com.xjs.activiti.service.IActTaskService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/task")
@Api(tags = "工作流-任务流程")
public class TaskController extends BaseController {


    @Autowired
    private IActTaskService actTaskService;


    //获取我的代办任务
    @GetMapping(value = "/list")
    @RequiresPermissions("activiti:task:list")
    public TableDataInfo getTasks() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Page<ActTaskDTO> hashMaps = actTaskService.selectTaskList(pageDomain);
        return getDataTable(hashMaps);
    }


    //获取我的历史任务
    @GetMapping("historyList")
    @RequiresPermissions("activiti:task:historyList")
    public TableDataInfo getHistoryTasks() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Page<ActTaskDTO> hashMaps = actTaskService.selectHistoryTaskList(pageDomain);
        return getDataTable(hashMaps);
    }



    //渲染表单
    @GetMapping(value = "/formDataShow/{taskID}")
    @RequiresPermissions("activiti:task:query")
    public AjaxResult formDataShow(@PathVariable("taskID") String taskID) {

        return AjaxResult.success(actTaskService.formDataShow(taskID));
    }

    //保存表单
    @PostMapping(value = "/formDataSave/{taskID}")
    @RequiresPermissions("activiti:task:save")
    public AjaxResult formDataSave(@PathVariable("taskID") String taskID,
                                   @RequestBody List<ActWorkflowFormDataDTO> formData) throws ParseException {
        for (ActWorkflowFormDataDTO formDatum : formData) {
            Assert.notNull(formDatum.getControlValue(),"参数不能为空");

            if (formDatum.getControlValue().length() > 100) {
                throw new IllegalArgumentException("长度超出 100 限制");
            }
        }
        return toAjax(actTaskService.formDataSave(taskID, formData));

    }

}
