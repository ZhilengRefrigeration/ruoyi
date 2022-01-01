package com.xjs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjs.domain.ApiRecord;
import com.xjs.exception.BusinessException;
import com.xjs.mapper.ApiRecordMapper;
import com.xjs.service.ApiWarningService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author xiejs
 * @desc api预警service实现类
 * @create 2021-12-31
 */
@Service
public class ApiWarningServiceImpl implements ApiWarningService {

    @Resource
    private ApiRecordMapper apiRecordMapper;

    @Override
    public Boolean saveApiRecord(ApiRecord apiRecord) {
        ApiRecord apiName = apiRecordMapper
                .selectOne(new QueryWrapper<ApiRecord>()
                        .eq("api_name", apiRecord.getApiName()));
        if (Objects.nonNull(apiName)) {
            throw new BusinessException("数据库存在相同url名称，保存失败！---" + apiRecord.getApiUrl());
        } else {
            apiRecordMapper.insert(apiRecord);
            return true;
        }
    }

    @Override
    public boolean updateApiRecord(ApiRecord apiRecord) {
        int name = apiRecordMapper.update(apiRecord, new QueryWrapper<ApiRecord>()
                .eq("api_name", apiRecord.getApiName()));
        return name == 1;
    }

    @Override
    public List<ApiRecord> selectApiRecordList(ApiRecord apiRecord) {
        return apiRecordMapper
                .selectList(new QueryWrapper<ApiRecord>().eq("api_name",apiRecord.getApiName())
                        .eq("api_name",apiRecord.getApiName()));
    }


}
