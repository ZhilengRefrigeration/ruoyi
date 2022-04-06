package com.xjs.apilog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.apilog.domain.ApiLog;

import java.util.List;
import java.util.Map;

/**
 * 日志Service接口
 *
 * @author xjs
 * @date 2021-12-26
 */
public interface IApiLogService extends IService<ApiLog> {

    //------------------------代码自动生成-----------------------------------

    /**
     * 查询日志
     *
     * @param id 日志主键
     * @return 日志
     */
    ApiLog selectApiLogById(Long id);

    /**
     * 查询日志列表
     *
     * @param apiLog 日志
     * @return 日志集合
     */
    List<ApiLog> selectApiLogList(ApiLog apiLog);

    /**
     * 批量删除日志
     *
     * @param ids 需要删除的日志主键集合
     * @return 结果
     */
    int deleteApiLogByIds(Long[] ids);

    /**
     * 删除日志信息
     *
     * @param id 日志主键
     * @return 结果
     */
    int deleteApiLogById(Long id);

    /**
     * 获取所有api名称
     * @return
     */
    List<String> getApiName();

    /**
     * 根据时间查询API记录统计
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return map
     */
    Map<String, List> statisticsByDate(String startDate, String endDate);
}
