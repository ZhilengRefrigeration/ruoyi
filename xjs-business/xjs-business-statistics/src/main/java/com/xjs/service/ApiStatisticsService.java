package com.xjs.service;

import java.util.List;
import java.util.Map;

/**
 * api统计服务接口
 * @author xiejs
 * @since 2022-01-25
 */
public interface ApiStatisticsService {


    /**
     * 统计历史api次数
     * @return map
     */
    Map<String, List> statisticsHistoryApi();


    /**
     * 统计当天api次数
     * @return map
     */
    Map<String, List> statisticsTodayApi();

    /**
     * 根据时间查询API记录统计
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return map
     */
    Map<String, List> statisticsByDate(String startDate, String endDate);
}
