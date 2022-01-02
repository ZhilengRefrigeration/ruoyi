package com.xjs.servicemonitor.service;

import cn.hutool.system.JavaRuntimeInfo;
import cn.hutool.system.JvmInfo;
import cn.hutool.system.JvmSpecInfo;

/**
 * @author xiejs
 * @desc  获取jvm信息
 * @create 2022-01-02
 */
public interface JvmMonitorService {

    /**
     * 获取jvm规格信息
     * @return JvmSpecInfo
     */
    JvmSpecInfo getJvmSpecInfo();

    /**
     * 获取jvm基本信息
     * @return JvmInfo
     */
    JvmInfo getJvmInfo();

    /**
     * 获取java运行时信息
     * @return JavaRuntimeInfo
     */
    JavaRuntimeInfo getJavaRuntimeInfo();


}
