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
        Page<ActTaskDTO> hashMaps = actTaskService.selectProcessDefinitionList(pageDomain);
        return getDataTable(hashMaps);


    }


    //渲染表单
    @GetMapping(value = "/formDataShow/{taskID}")
    public AjaxResult formDataShow(@PathVariable("taskID") String taskID) {

        return AjaxResult.success(actTaskService.formDataShow(taskID));
    }

    //保存表单
    @PostMapping(value = "/formDataSave/{taskID}")
    public AjaxResult formDataSave(@PathVariable("taskID") String taskID,
                                   @RequestBody List<ActWorkflowFormDataDTO> formData) throws ParseException {
        return toAjax(actTaskService.formDataSave(taskID, formData));

    }

}
