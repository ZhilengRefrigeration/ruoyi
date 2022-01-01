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
     * @param apiRecord apiRecord
     * @return apiRecord
     */
    Boolean saveApiRecord(ApiRecord apiRecord);


    /**
     * 修改 根据url名称修改api调用次数
     * @param apiRecord apiRecord
     * @return apiRecord
     */
    boolean updateApiRecord(ApiRecord apiRecord);

    /**
     * 根据 apiurl和name查询
     * @param apiRecord
     * @return
     */
    List<ApiRecord> selectApiRecordList(ApiRecord apiRecord);
}
