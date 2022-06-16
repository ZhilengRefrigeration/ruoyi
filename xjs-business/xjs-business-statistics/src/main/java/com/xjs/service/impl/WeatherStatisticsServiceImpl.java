package com.xjs.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xjs.business.api.RemoteWeatherFeign;
import com.xjs.service.WeatherStatisticsService;
import com.xjs.utils.WeekUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.xjs.consts.CommonConst.TODAY_END;
import static com.xjs.consts.CommonConst.TODAY_START;


/**
 * 天气统计service接口实现
 * @author xiejs
 * @since 2022-01-26
 */
@Service
public class WeatherStatisticsServiceImpl implements WeatherStatisticsService {

    @Autowired
    private RemoteWeatherFeign remoteWeatherFeign;


    @Override
    public Map<String, List> historyWeather(String startDate, String endDate) {
        if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
            startDate = DateUtil.today() + " "+TODAY_START;
            endDate = DateUtil.today() + " "+TODAY_END;
        }
        return remoteWeatherFeign.getHistoryWeatherForRPC(startDate, endDate).getData();
    }

    @Override
    public Map<String, List<String>> futureWeather() {
        Map<String, List<String>> map = remoteWeatherFeign.getFutureWeatherForRPC().getData();
        List<String> weekList = map.get("week");
        List<String> collect = weekList.stream().map(WeekUtils::weekConvert).collect(Collectors.toList());

        //合并时间和星期
        ArrayList<String> dateWeek = new ArrayList<>();
        List<String> date = map.get("date");
        for (int i = 0; i < collect.size(); i++) {
            for (int j = 0; j < date.size(); j++) {
                String dates = DateUtil.parse(date.get(i)).toString("MM-dd");
                if (i == j) {
                    String value = dates+"("+collect.get(j)+")";
                    dateWeek.add(value);
                }
            }
        }
        map.put("dateWeek", dateWeek);

        //移除不需要的属性
        map.remove("date");
        map.remove("week");

        return map;
    }
}
