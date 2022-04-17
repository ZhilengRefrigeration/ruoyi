package com.xjs.activiti.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.xjs.activiti.service.IFormHistoryDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "工作流-历史表格数据")
@RequestMapping("/historyFromData")
public class HistoryFormDataCoroller {
    @Autowired
    private IFormHistoryDataService formHistoryDataService;

    @GetMapping(value = "/ByInstanceId/{instanceId}")
    @ApiOperation("历史表格数据")
    public AjaxResult historyFromData(@PathVariable("instanceId") String instanceId) {
        return AjaxResult.success(formHistoryDataService.historyDataShow(instanceId));

    }
}
