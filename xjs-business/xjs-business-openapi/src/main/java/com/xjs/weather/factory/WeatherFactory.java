package com.xjs.weather.factory;

/**
 * 天气api工厂接口
 * @author xiejs
 * @since 2022-01-16
 */
public interface WeatherFactory<T> {


    /**
     * 获取天气api工厂方法
     * @return T
     */
    T weatherApi();


}
