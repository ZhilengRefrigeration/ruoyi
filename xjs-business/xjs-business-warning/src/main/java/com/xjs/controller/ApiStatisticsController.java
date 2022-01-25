package com.xjs.controller;

import com.ruoyi.common.core.domain.R;
import com.xjs.domain.ApiRecord;
import com.xjs.service.ApiWarningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private ApiWarningService apiWarningService;

    @GetMapping
    @ApiOperation("远程查询API记录统计")
    public R<List<ApiRecord>> selectApiRecordListForRPC() {
        List<ApiRecord> apiRecords = apiWarningService.selectApiRecordList(new ApiRecord());
        return R.ok(apiRecords);
    }

}
