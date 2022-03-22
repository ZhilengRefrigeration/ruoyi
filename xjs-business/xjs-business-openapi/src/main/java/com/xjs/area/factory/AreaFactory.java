package com.xjs.area.factory;


/**
 * 区域编码工厂接口
 * @author xiejs
 * @since 2022-03-22
 */
public interface AreaFactory<R,T> {

    /**
     * 获取区域编码
     * @param t 入参
     * @return 出参
     */
    R getArea(T t);
}
