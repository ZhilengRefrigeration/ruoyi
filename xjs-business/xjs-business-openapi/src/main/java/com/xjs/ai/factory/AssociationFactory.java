package com.xjs.ai.factory;

/**
 * 联想 工厂
 * @author xiejs
 * @since 2022-02-24
 */
public interface AssociationFactory<T> {

    /**
     * 获取数据
     * @param content 输入内容
     * @return T
     */
    T getData(String content);

}
