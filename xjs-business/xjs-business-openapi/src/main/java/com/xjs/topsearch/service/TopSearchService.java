package com.xjs.topsearch.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 热搜榜服务接口
 * @author xiejs
 * @since 2022-01-22
 */
public interface TopSearchService {

    /**
     * 获取所有热搜(内含插入)
     * @return
     */
    Map<String, List> getAllTopSearch() throws ExecutionException, InterruptedException;


    /**
     * 删除重复数据
     * @return
     */
    Integer deleteRepeatData();


    /**
     * 根据日期获取历史热搜数据
     * @param date 日期
     * @return 热搜数据
     */
    Map<String, List> getHistoryTopSearchByDate(String date);


}
