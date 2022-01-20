package com.xjs.apitools.service;

import com.xjs.apitools.domain.*;

import java.util.List;

/**
 * api工具服务接口
 * @author xiejs
 * @since 2022-01-18
 */
public interface ApiToolsService {

    /**
     * 获取节假日信息(获取未来节假日，已过的节假日排除)
     * @return List<ApiHoliday>
     */
    List<ApiHoliday> getApiHolidayList();


    /**
     * 获取手机归属地信息
     * @param mobile 手机号
     * @return ApiMobileBelong
     */
    ApiMobileBelong getApiMobileBelong(String mobile);


    /**
     * 获取实时天气
     * @param city 目标城市
     * @return ApiWeather
     */
    ApiNowWeather getNowWeather(String city);

    /**
     * 获取预报天气
     * @param city 目标城市
     * @return ApiForecastWeather
     */
    ApiForecastWeather getForecastWeather(String city);


    /**
     * 获取垃圾分类
     * @param name 垃圾名称
     * @return ApiGarbageSorting
     */
    ApiGarbageSorting getGarbageSorting(String name);


    /**
     * 获取mm图片
     * @return ApiBeautyPicture
     */
    List<ApiBeautyPicture> getBeautyPictureList();


    /**
     * 获取历史上的今天数据
     * @return ApiHistoryToday
     */
    List<ApiHistoryToday> getHistoryTodayList();


    /**
     * 获取简繁转换数据
     * @param content 传入的简体
     * @return ApiSimpleComplex
     */
    ApiSimpleComplex getSimpleComplex(String content);


    /**
     * 获取汉语字典数据
     * @param content 被查询的汉字内容
     * @return ApiChineseDict
     */
    ApiChineseDict getChineseDict(String content);


    /**
     * 获取身份证查询数据
     * @param idcard 身份证号码
     * @return ApiIdcardQuery
     */
    ApiIdcardQuery getIdcardQuery(String idcard);



}
