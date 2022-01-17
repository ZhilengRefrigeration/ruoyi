package com.xjs.apitools.factory;


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
    default T ApiData() {
        return null;
    }

    /**
     * 获取api数据工厂方法 (有参)
     *
     * @param req 请求参数
     * @return T
     */
    default T ApiData(R req) {
        return null;
    }


}
