package com.xjs.servicemonitor.service;

import cn.hutool.system.HostInfo;
import cn.hutool.system.OsInfo;
import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.UserInfo;

/**
 * @author xiejs
 * @desc 电脑系统信息
 * @create 2022-01-02
 */
public interface SystemOSService {

    /**
     * 获取os信息
     * @return OsInfo
     */
    OsInfo getOsInfo();

    /**
     * 获取系统的用户信息
     * @return UserInfo
     */
    UserInfo getUserInfo();

    /**
     * 获取网络地址信息
     * @return HostInfo
     */
    HostInfo getHostInfo();

    /**
     * 获取电脑运行时参数信息
     * @return RuntimeInfo
     */
    RuntimeInfo getRuntimeInfo();
}
