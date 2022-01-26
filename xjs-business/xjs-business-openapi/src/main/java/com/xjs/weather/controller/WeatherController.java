package com.xjs.weather.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.security.annotation.RequiresLogin;
import com.xjs.utils.WeekUtils;
import com.xjs.weather.domain.Casts;
import com.xjs.weather.domain.ForecastWeather;
import com.xjs.weather.domain.NowWeather;
import com.xjs.weather.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 天气控制器
 *
 * @author xiejs
 * @since 2022-01-16
 */
@RestController
@RequestMapping("weather")
@Api(tags = "业务模块-天气管理")
@Log4j2
public class WeatherController {

    @Autowired
    private WeatherService weatherService;


    @GetMapping("now")
    @ApiOperation("获取实时天气信息")
    @Log(title = "获取实时天气")
    @RequiresLogin
    public AjaxResult getNowWeatherApiData() {
        return AjaxResult.success(weatherService.saveNowWeather());
    }

    @GetMapping("forecast")
    @ApiOperation("获取预报天气信息")
    @Log(title = "获取预报天气")
    @RequiresLogin
    public AjaxResult getForecastWeatherApiData() {
        ForecastWeather forecastWeather = weatherService.cacheForecastWeather();
        this.weekConvert(forecastWeather);
        return AjaxResult.success(forecastWeather);
    }

    //----------------------内部远程调用rpc----------------------------

    @GetMapping("getWeatherForRPC")
    @ApiOperation("远程调用获取天气信息")
    public R getWeatherForRPC() {
        NowWeather nowWeather = weatherService.save();
        return Objects.nonNull(nowWeather.getCity()) ? R.ok() : R.fail();
    }

    @GetMapping("getHistoryWeatherForRPC")
    @ApiOperation("远程调用获取历史天气信息")
    public R<Map<String, List>> getHistoryWeatherForRPC(@RequestParam("startDate")String startDate,
                                     @RequestParam("endDate")String endDate) {
        Map<String, List> map =  weatherService.getHistoryWeather(startDate,endDate);
        return R.ok(map);
    }

    @GetMapping("getFutureWeatherForRPC")
    @ApiOperation("远程调用获取未来天气信息")
    R<Map<String, List<String>>> getFutureWeatherForRPC() {
        Map<String, List<String>> map =  weatherService.getFutureWeather();
        return R.ok(map);
    }




    /**
     * week类型转换
     */
    private void weekConvert(ForecastWeather forecastWeather) {
        List<Casts> casts = forecastWeather.getCasts();
        casts.forEach(cast -> {
            String week = WeekUtils.weekConvert(cast.getWeek());
            cast.setWeek(week);
        });
    }


}
