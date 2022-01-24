package com.xjs.weather.factory;

/**
 * 获取ip的API接口工厂
 * @author xiejs
 * @since 2022-01-15
 */
public interface IPFactory<T> {

    /**
     * 获取ip工厂方法
     * @return T
     */
    T ipApi();

    /**
     * 获取ip工厂方法
     * @param ip ip
     * @return T
     */
    T ipApi(String ip);

}
