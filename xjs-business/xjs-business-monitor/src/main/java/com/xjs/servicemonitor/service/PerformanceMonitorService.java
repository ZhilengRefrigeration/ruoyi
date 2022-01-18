package com.xjs.servicemonitor.service;

import java.util.Map;

/**
 * 性能将service接口
 * @author xiejs
 * @since 2022-01-18
 */
public interface PerformanceMonitorService {

    /**
     * 获取性能监控
     * @return map
     */
    Map<String,Object> getServers();
}
