package com.xjs.apitools.controller;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.apitools.domain.ApiHoliday;
import com.xjs.apitools.service.ApiToolsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * api小工具控制器
 *
 * @author xiejs
 * @since 2022-01-17
 */
@RestController
@RequestMapping("apitools")
@Api(tags = "业务模块-API小工具")
@Log4j2
public class ApiToolsController {


    @Autowired
    private ApiToolsService apiToolsService;


    @GetMapping
    @ApiOperation("获取节假日信息")
    @Log(title = "获取节假日")
    @RequiresPermissions("open:apitools:holiday")
    public R<List<ApiHoliday>> getHolidayApiData() {
        List<ApiHoliday> apiHolidayList = apiToolsService.getApiHolidayList();
        return CollUtil.isNotEmpty(apiHolidayList) ? R.ok(apiHolidayList) : R.fail();
    }


}
