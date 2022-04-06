package com.xjs.apilog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.apilog.domain.ApiLog;
import com.xjs.apilog.vo.ApiLogVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 日志Mapper接口
 *
 * @author xjs
 * @date 2021-12-26
 */
public interface ApiLogMapper extends BaseMapper<ApiLog> {

    /**
     *根据时间查询API记录统计
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return list
     */
    List<ApiLogVo> statisticsByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);


    //------------------------代码自动生成-----------------------------------

    /**
     * 查询日志
     *
     * @param id 日志主键
     * @return 日志
     */
    public ApiLog selectApiLogById(Long id);

    /**
     * 查询日志列表
     *
     * @param apiLog 日志
     * @return 日志集合
     */
    public List<ApiLog> selectApiLogList(ApiLog apiLog);

    /**
     * 删除日志
     *
     * @param id 日志主键
     * @return 结果
     */
    public int deleteApiLogById(Long id);

    /**
     * 批量删除日志
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteApiLogByIds(Long[] ids);


}
