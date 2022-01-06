package com.xjs.service;

import com.xjs.domain.ApiRecord;

import java.util.List;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-31
 */
public interface ApiWarningService {

    /**
     * 保存apirecord 当存在相同api时，不允许保存
     *
     * @param apiRecord apiRecord
     * @return apiRecord
     */
    Boolean saveApiRecord(ApiRecord apiRecord);


    /**
     * 修改 根据url名称修改api调用次数
     *
     * @param apiRecord apiRecord
     * @return apiRecord
     */
    boolean updateApiRecordByUrl(ApiRecord apiRecord);

    /**
     * 根据 apiurl和name查询
     *
     * @param apiRecord
     * @return
     */
    List<ApiRecord> selectApiRecordListByUrl(ApiRecord apiRecord);

    //---------------------代码生成---------------------------------

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
