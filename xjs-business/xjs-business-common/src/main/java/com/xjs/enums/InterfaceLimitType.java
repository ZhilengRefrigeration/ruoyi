package com.xjs.enums;

/**
 * 接口限流类别枚举类
 * @author xiejs
 * @since 2022-05-16
 */
public enum InterfaceLimitType {

    /**
     * 默认策略全局限流
     */
    DEFAULT,
    /**
     * 根据请求者IP进行限流
     */
    IP
}
