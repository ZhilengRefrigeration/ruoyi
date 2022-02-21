package com.xjs.job.task.openapi;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.RemoteWeatherFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 调用天气定时任务
 * @author xiejs
 * @since 2022-01-16
 */
@Component("WeatherTask")
public class WeatherTask {

    @Resource
    private RemoteWeatherFeign remoteWeatherFeign;

    private static final Logger log = LoggerFactory.getLogger(WeatherTask.class);

    /**
     * 任务执行
     */
    public void execute() {
        LocalDateTime localDateTime1 = DateUtil.date().toLocalDateTime();
        log.info("---------------天气定时任务Start-------------------");
        R r = remoteWeatherFeign.getWeatherForRPC();
        log.info("天气定时任务结果:code={},msg={},data={}",r.getCode(),r.getMsg(),r.getData());
        LocalDateTime localDateTime2 = DateUtil.date().toLocalDateTime();
        long between = ChronoUnit.MILLIS.between(localDateTime1, localDateTime2);
        log.info("Job耗费时间:{}ms", between);
        log.info("---------------天气定时任务end---------------------");
    }

}
