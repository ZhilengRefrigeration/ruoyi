package com.xjs.apitools.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.apitools.domain.*;
import com.xjs.apitools.service.ApiToolsService;
import com.xjs.utils.ChineseUtils;
import com.xjs.weather.domain.IPInfoVo;
import com.xjs.weather.service.IPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Pattern;

import static com.xjs.consts.RegexConst.*;

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
    @Autowired
    private IPService ipService;


    @GetMapping("holiday")
    @ApiOperation("获取节假日信息")
    @Log(title = "获取节假日")
    @RequiresPermissions("open:apitools:holiday")
    public R<List<ApiHoliday>> getHolidayApiData() {
        return R.ok(apiToolsService.getApiHolidayList());
    }


    @GetMapping("mobilebelong/{mobile}")
    @ApiOperation("获取手机归属地信息")
    @Log(title = "获取手机归属地")
    @RequiresPermissions("open:apitools:mobilebelong")
    public R<ApiMobileBelong> getMobileBelongApiData(@PathVariable("mobile") String mobile) {
        boolean matches = Pattern.matches(MOBILE_REGEX, mobile);
        if (!matches) {
            return R.fail("请输入正确的手机号码！！！");
        }
        return R.ok(apiToolsService.getApiMobileBelong(mobile));
    }

    @GetMapping("nowweather/{city}")
    @ApiOperation("获取实时天气信息")
    @Log(title = "获取实时天气")
    @RequiresPermissions("open:apitools:nowweather")
    public R<ApiNowWeather> getNowWeatherApiData(@PathVariable("city") String city) {
        return R.ok(apiToolsService.getNowWeather(city));
    }

    @GetMapping("forecastweather/{city}")
    @ApiOperation("获取预报天气信息")
    @Log(title = "获取预报天气")
    @RequiresPermissions("open:apitools:forecastweather")
    public R<ApiForecastWeather> getForecastWeatherApiData(@PathVariable("city") String city) {
        return R.ok(apiToolsService.getForecastWeather(city));
    }


    @GetMapping("garbagesorting/{name}")
    @ApiOperation("获取垃圾分类信息")
    @Log(title = "获取垃圾分类")
    @RequiresPermissions("open:apitools:garbagesorting")
    public R<ApiGarbageSorting> getGarbageSortingApiData(@PathVariable("name") String name) {
        return R.ok(apiToolsService.getGarbageSorting(name));
    }

    @GetMapping("beautypicture")
    @ApiOperation("获取mm图片信息")
    @Log(title = "获取mm图片分类")
    @RequiresPermissions("open:apitools:beautypicture")
    public R<List<ApiBeautyPicture>> getBeautyPictureApiData() {
        return R.ok(apiToolsService.getBeautyPictureList());
    }

    @GetMapping("historytoday")
    @ApiOperation("获取历史今天信息")
    @Log(title = "获取历史今天")
    @RequiresPermissions("open:apitools:historytoday")
    public R<List<ApiHistoryToday>> getHistoryTodayApiData() {
        return R.ok(apiToolsService.getHistoryTodayList());
    }

    @GetMapping("simplecomplex/{content}")
    @ApiOperation("获取简繁转换信息")
    @Log(title = "获取简繁转换")
    @RequiresPermissions("open:apitools:simplecomplex")
    public R<ApiSimpleComplex> getSimpleComplexApiData(@PathVariable("content") String content) {
        boolean b = ChineseUtils.checkNameChese(content);
        if (b) {
            return R.ok(apiToolsService.getSimpleComplex(content));

        } else {
            return R.fail("请输入中文！！！");
        }
    }

    @GetMapping("chinesedict/{content}")
    @ApiOperation("获取汉语字典信息")
    @Log(title = "获取汉语字典")
    @RequiresPermissions("open:apitools:chinesedict")
    public R<ApiChineseDict> getChineseDictApiData(@PathVariable("content") String content) {
        boolean b1 = ChineseUtils.checkNameChese(content);
        if (b1 && content.length() == 1) {
            return R.ok(apiToolsService.getChineseDict(content));
        } else {
            return R.fail("请输入单个中文！！！");
        }
    }


    @GetMapping("idcardquery/{idcard}")
    @ApiOperation("获取身份证信息")
    @Log(title = "获取身份证信息")
    @RequiresPermissions("open:apitools:idcardquery")
    public R<ApiIdcardQuery> getIdcardQueryApiData(@PathVariable("idcard") String idcard) {
        boolean id15 = Pattern.matches(IDCARD_15_REGEX, idcard);
        boolean id18 = Pattern.matches(IDCARD_18_REGEX, idcard);
        if (id15 || id18) {
            return R.ok(apiToolsService.getIdcardQuery(idcard));
        }
        return R.fail("请输入正确的身份证号！！！");
    }

    @GetMapping("ipinfo/{ip}")
    @ApiOperation("获取IP信息")
    @Log(title = "获取IP")
    @RequiresPermissions("open:apitools:ipinfo")
    public R<IPInfoVo> getIPApiData(@PathVariable("ip")String ip) {
        return R.ok(ipService.getIPApiData());
    }

}
