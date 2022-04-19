package com.xjs.common.run;

import com.xjs.weather.service.WeatherService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 服务启动时执行类
 * @author xiejs
 * @since 2022-04-19
 */
@Component
@Log4j2
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Autowired
    private WeatherService weatherService;

    @Override
    public void run(ApplicationArguments args)  {
        log.info("获取天气数据中...");
        weatherService.saveNowWeather();
        weatherService.cacheForecastWeather();
        log.info("获取天气数据完成...");
    }
}
