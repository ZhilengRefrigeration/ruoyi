package com.xjs.weather.service;

import com.xjs.weather.domain.IPInfoVo;

/**
 * ip api服务接口
 * @author xiejs
 * @since 2022-01-15
 */
public interface IPService {

    /**
     * 获取IP的数据
     * @return IPInfoVo
     */
    IPInfoVo getIPApiData();

}
