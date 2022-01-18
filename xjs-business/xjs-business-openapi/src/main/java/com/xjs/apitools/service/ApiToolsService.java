package com.xjs.apitools.service;

import com.xjs.apitools.domain.ApiHoliday;
import com.xjs.apitools.domain.ApiMobileBelong;

import java.util.List;

/**
 * api工具服务接口
 * @author xiejs
 * @since 2022-01-18
 */
public interface ApiToolsService {

    /**
     * 获取节假日信息(获取未来节假日，已过的节假日排除)
     * @return List<ApiHoliday>
     */
    List<ApiHoliday> getApiHolidayList();


    /**
     * 获取手机归属地信息
     * @param mobile 手机号
     * @return ApiMobileBelong
     */
    ApiMobileBelong getApiMobileBelong(String mobile);

}
