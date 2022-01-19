package com.xjs.apitools.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.apitools.domain.*;
import com.xjs.apitools.service.ApiToolsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

import static com.xjs.consts.RegexConst.MOBILE_REGEX;

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


    @GetMapping("holiday")
    @ApiOperation("获取节假日信息")
    @Log(title = "获取节假日")
    @RequiresPermissions("open:apitools:holiday")
    public R<List<ApiHoliday>> getHolidayApiData() {
        return R.ok(apiToolsService.getApiHolidayList());
    }


    @GetMapping("mobilebelong")
    @ApiOperation("获取手机归属地信息")
    @Log(title = "获取手机归属地")
    @RequiresPermissions("open:apitools:mobilebelong")
    public R<ApiMobileBelong> getMobileBelongApiData(@RequestParam(name = "mobile") String mobile) {
        boolean matches = Pattern.matches(MOBILE_REGEX, mobile);
        if (!matches) {
            return R.fail("请输入正确的手机号码！！！");
        }
        return R.ok(apiToolsService.getApiMobileBelong(mobile));
    }

    @GetMapping("nowweather/{city}")
    @ApiOperation("获取实时天气信息")
    @Log(title = "获取实时天气")
    public R<ApiNowWeather> getNowWeatherApiData(@PathVariable("city") String city) {
        return R.ok(apiToolsService.getNowWeather(city));
    }

    @GetMapping("forecastweather/{city}")
    @ApiOperation("获取预报天气信息")
    @Log(title = "获取预报天气")
    public R<ApiForecastWeather> getForecastWeatherApiData(@PathVariable("city") String city) {
        return R.ok(apiToolsService.getForecastWeather(city));
    }


    @GetMapping("garbagesorting/{name}")
    @ApiOperation("获取垃圾分类信息")
    @Log(title = "获取垃圾分类")
    public R<ApiGarbageSorting> getGarbageSortingApiData(@PathVariable("name") String name) {
        return R.ok(apiToolsService.getGarbageSorting(name));
    }




}
