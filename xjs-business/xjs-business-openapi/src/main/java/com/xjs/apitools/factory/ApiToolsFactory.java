package com.xjs.apitools.factory;


import java.util.List;

/**
 * api工具工厂接口
 *
 * @author xiejs
 * @since 2022-01-17
 */
public interface ApiToolsFactory<T, R> {


    /**
     * 获取api数据工厂方法 (无参)
     *
     * @return T
     */
    default T apiData() {
        return null;
    }

    /**
     * 获取api数据工厂方法 (无参)
     * @return List<T>
     */
    default List<T> apiDataList() {
        return null;
    }

    /**
     * 获取api数据工厂方法 (有参)
     *
     * @param req 请求参数
     * @return T
     */
    default T apiData(R req) {
        return null;
    }

    /**
     * 获取api数据工厂方法 (有参)
     *
     * @param req 请求参数
     * @return List<T>
     */
    default List<T> apiDataList(R req) {
        return null;
    }



}
