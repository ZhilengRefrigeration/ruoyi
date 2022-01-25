package com.xjs.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.service.ApiStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * api记录统计控制器
 * @author xiejs
 * @since 2022-01-25
 */
@RestController
@RequestMapping("apistatistics")
@Api(tags = "业务模块-API记录统计")
public class ApiStatisticsController {

    @Autowired
    private ApiStatisticsService apiStatisticsService;

    @GetMapping("history")
    @ApiOperation("查询API历史记录统计")
    @RequiresPermissions("statistics:apistatistics:list")
    public R<Map<String, List>> statisticsHistoryApi() {
        Map<String, List> map = apiStatisticsService.statisticsHistoryApi();
        return R.ok(map);
    }

    @GetMapping("today")
    @ApiOperation("查询API当天记录统计")
    @RequiresPermissions("statistics:apistatistics:list")
    public R<Map<String, List>> statisticsTodayApi() {
        Map<String, List> map = apiStatisticsService.statisticsTodayApi();
        return R.ok(map);
    }

}
