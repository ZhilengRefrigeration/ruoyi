package com.xjs.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.service.WeatherStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 天气统计controller
 * @author xiejs
 * @since 2022-01-25
 */
@RestController
@RequestMapping("weatherstatistics")
@Api(tags = "业务模块-天气记录统计")
public class WeatherStatisticsController {

    @Autowired
    private WeatherStatisticsService weatherStatisticsService;

    @GetMapping("history")
    @ApiOperation("统计历史天气")
    @RequiresPermissions("statistics:weatherstatistics:list")
    public AjaxResult historyWeather(@RequestParam(value = "startDate",required = false)String startDate,
                                     @RequestParam(value="endDate",required = false)String endDate) {
        Map<String, List> map = weatherStatisticsService.historyWeather(startDate, endDate);
        return AjaxResult.success(map);
    }

}
