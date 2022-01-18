package com.xjs.servicemonitor.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.servicemonitor.service.PerformanceMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 性能监控controller
 *
 * @author xiejs
 * @since 2022-01-18
 */
@RestController
@RequestMapping("performancemonitor")
@Api(tags = "业务模块-性能监控")
public class PerformanceMonitorController {

    @Autowired
    private PerformanceMonitorService performanceMonitorService;


    @GetMapping
    @RequiresPermissions("monitor:performance:list")
    @ApiOperation("获取性能监控信息")
    public AjaxResult getPerformanceMonitor() {
        return AjaxResult.success(performanceMonitorService.getServers());
    }
}
