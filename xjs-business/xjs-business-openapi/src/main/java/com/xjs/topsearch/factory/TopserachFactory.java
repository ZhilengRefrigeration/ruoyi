package com.xjs.topsearch.factory;

import java.util.List;

/**
 * 热搜api工厂 默认使用tianxing接口
 * @author xiejs
 * @since 2022-01-10
 */
public interface TopserachFactory<T> {

    /**
     * 热搜api工厂方法
     * @return Object
     */
    List<T> topSearchApi();


}
