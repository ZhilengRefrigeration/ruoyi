package com.xjs.apitools.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.apitools.domain.*;
import com.xjs.apitools.factory.ApiToolsFactory;
import com.xjs.apitools.factory.impl.*;
import com.xjs.apitools.service.ApiToolsService;
import com.xjs.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * api工具服务实现
 *
 * @author xiejs
 * @since 2022-01-18
 */
@Service
public class ApiToolsServiceImpl implements ApiToolsService {

    /**
     * 文件单位
     */
    public static final String KB= "KB";

    private ApiToolsFactory<ApiHoliday, Object> holidayFactory;
    private ApiToolsFactory<ApiMobileBelong, RequestBody> mobileBelongFactory;
    private ApiToolsFactory<ApiNowWeather, RequestBody> nowWeatherFactory;
    private ApiToolsFactory<ApiForecastWeather, RequestBody> forecastWeatherFactory;
    private ApiToolsFactory<ApiGarbageSorting, RequestBody> garbageSortingFactory;
    private ApiToolsFactory<ApiBeautyPicture, Object> beautyPictureFactory;
    private ApiToolsFactory<ApiHistoryToday,Object> historyTodayFactory;

    @Autowired
    @Qualifier("rollSimpleComplexFactory")
    private ApiToolsFactory<ApiSimpleComplex,RequestBody> simpleComplexFactory;

    @Autowired
    @Qualifier("rollChineseDictFactory")
    private ApiToolsFactory<ApiChineseDict,RequestBody> chineseDictFactory;

    @Autowired
    @Qualifier("rollIdcardQueryFactory")
    private ApiToolsFactory<ApiIdcardQuery,RequestBody> idcardQueryFactory;

    @Autowired
    public void setHolidayFactory(RollHolidayFactory rollHolidayFactory) {
        this.holidayFactory = rollHolidayFactory;
    }

    @Autowired
    public void setMobileBelongFactory(RollMobileBelongFactory rollMobileBelongFactory) {
        this.mobileBelongFactory = rollMobileBelongFactory;
    }

    @Autowired
    public void setNowWeatherFactory(RollNowWeatherFactory rollNowWeatherFactory) {
        this.nowWeatherFactory = rollNowWeatherFactory;
    }

    @Autowired
    public void setForecastWeatherFactory(RollForecastWeatherFactory rollForecastWeatherFactory) {
        this.forecastWeatherFactory = rollForecastWeatherFactory;
    }

    @Autowired
    public void setGarbageSortingFactory(RollGarbageSortingFactory rollGarbageSortingFactory) {
        this.garbageSortingFactory = rollGarbageSortingFactory;
    }

    @Autowired
    public void setBeautyPictureFactory(RollBeautyPictureFactory rollBeautyPictureFactory) {
        this.beautyPictureFactory = rollBeautyPictureFactory;
    }

    @Autowired
    private void setHistoryTodayFactory(RollHistoryTodayFactory rollHistoryTodayFactory) {
        this.historyTodayFactory = rollHistoryTodayFactory;
    }


    @Override
    public List<ApiHoliday> getApiHolidayList() {
        List<ApiHoliday> apiHolidayList = holidayFactory.apiDataList();
        Optional.ofNullable(apiHolidayList).orElseThrow(ApiException::new);
        List<ApiHoliday> collect = apiHolidayList.stream().map(holidayFactory -> {
            if (holidayFactory.getResidueDays() >= 0) {
                if (holidayFactory.getLunarHoliday()) {
                    //是农历
                    DateTime lunarDate = DateUtil.parseDate(holidayFactory.getLunarDate());
                    ChineseDate chineseDate = new ChineseDate(lunarDate.toJdkDate());
                    holidayFactory.setReturnDate(chineseDate.toString());
                }else {
                    holidayFactory.setReturnDate(holidayFactory.getDate());
                }
                return holidayFactory;
            } else {
                return null;
            }
        }).collect(Collectors.toList());
        collect.removeIf(Objects::isNull);
        return collect;
    }

    @Override
    public ApiMobileBelong getApiMobileBelong(String mobile) {
        RequestBody requestBody = new RequestBody();
        requestBody.setMobile(mobile);
        return Optional.ofNullable(mobileBelongFactory.apiData(requestBody))
                .orElseThrow(ApiException::new);
    }

    @Override
    public ApiNowWeather getNowWeather(String city) {
        RequestBody requestBody = new RequestBody();
        requestBody.setCity(city);
        return Optional.ofNullable(nowWeatherFactory.apiData(requestBody))
                .orElseThrow(ApiException::new);
    }

    @Override
    public ApiForecastWeather getForecastWeather(String city) {
        RequestBody requestBody = new RequestBody();
        requestBody.setCity(city);
        return Optional.ofNullable(forecastWeatherFactory.apiData(requestBody))
                .orElseThrow(ApiException::new);
    }

    @Override
    public ApiGarbageSorting getGarbageSorting(String name) {
        RequestBody requestBody = new RequestBody();
        requestBody.setName(name);
        return Optional.ofNullable(garbageSortingFactory.apiData(requestBody))
                .orElseThrow(ApiException::new);
    }

    @Override
    public List<ApiBeautyPicture> getBeautyPictureList() {
        List<ApiBeautyPicture> beautyPictureList = Optional.ofNullable(beautyPictureFactory.apiDataList())
                .orElseThrow(ApiException::new);
        beautyPictureList.forEach(bp ->{
            String imageFileLength = bp.getImageFileLength();
            if (StringUtils.isNotEmpty(imageFileLength)) {
                BigDecimal decimal = new BigDecimal(imageFileLength);
                BigDecimal divide = decimal.divide(new BigDecimal(1024), 0, RoundingMode.HALF_UP);
                bp.setImageFileLength(divide.toPlainString()+KB);
            }
        });
        return beautyPictureList;
    }

    @Override
    public List<ApiHistoryToday> getHistoryTodayList() {
        List<ApiHistoryToday> historyTodayList = historyTodayFactory.apiDataList();
        if (CollUtil.isNotEmpty(historyTodayList)) {
            return historyTodayList.stream().limit(7).collect(Collectors.toList());
        }else {
            throw new ApiException("获取历史上的今天api调用异常！！！");
        }
    }

    @Override
    public ApiSimpleComplex getSimpleComplex(String content) {
        RequestBody requestBody = new RequestBody();
        requestBody.setContent(content);
        return simpleComplexFactory.apiData(requestBody);
    }

    @Override
    public ApiChineseDict getChineseDict(String content) {
        RequestBody requestBody = new RequestBody();
        requestBody.setContent(content);
        return chineseDictFactory.apiData(requestBody);
    }

    @Override
    public ApiIdcardQuery getIdcardQuery(String idcard) {
        RequestBody requestBody = new RequestBody();
        requestBody.setIdcard(idcard);
        return idcardQueryFactory.apiData(requestBody);
    }



}
