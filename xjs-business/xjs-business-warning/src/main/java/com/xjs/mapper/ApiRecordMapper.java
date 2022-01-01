package com.xjs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.domain.ApiRecord;

import java.util.List;

/**
 * @author xiejs
 * @desc api预警mapper
 * @create 2021-12-31
 */
public interface ApiRecordMapper extends BaseMapper<ApiRecord> {

    //--------------------代码生成----------------------------

    /**
     * 查询API预警
     *
     * @param id API预警主键
     * @return API预警
     */
    public ApiRecord selectApiRecordById(Long id);

    /**
     * 查询API预警列表
     *
     * @param apiRecord API预警
     * @return API预警集合
     */
    public List<ApiRecord> selectApiRecordList(ApiRecord apiRecord);

    /**
     * 修改API预警
     *
     * @param apiRecord API预警
     * @return 结果
     */
    public int updateApiRecord(ApiRecord apiRecord);

}
