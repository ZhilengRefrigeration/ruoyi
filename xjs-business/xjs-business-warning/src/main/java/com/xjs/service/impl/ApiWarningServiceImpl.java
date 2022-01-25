package com.xjs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.domain.ApiRecord;
import com.xjs.domain.ApiWarning;
import com.xjs.exception.BusinessException;
import com.xjs.mapper.ApiRecordMapper;
import com.xjs.mapper.ApiWarningMapper;
import com.xjs.service.ApiWarningService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xiejs
 * @desc api预警service实现类
 * @create 2021-12-31
 */
@Service
public class ApiWarningServiceImpl extends ServiceImpl<ApiWarningMapper, ApiWarning> implements ApiWarningService {

    @Resource
    private ApiRecordMapper apiRecordMapper;
    @Resource
    private ApiWarningMapper apiWarningMapper;

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
    public boolean updateApiRecordByUrl(ApiRecord apiRecord) {
        int name = apiRecordMapper.update(apiRecord, new QueryWrapper<ApiRecord>()
                .eq("api_name", apiRecord.getApiName()));
        return name == 1;
    }

    @Override
    public List<ApiRecord> selectApiRecordListByUrl(ApiRecord apiRecord) {
        return apiRecordMapper
                .selectList(new QueryWrapper<ApiRecord>()
                        .eq(StringUtils.isNotEmpty(apiRecord.getApiName()),"api_name", apiRecord.getApiName())
                        .eq(StringUtils.isNotEmpty(apiRecord.getApiUrl()),"api_url", apiRecord.getApiUrl()));
    }

    @Override
    public Integer clearAll() {
        return apiWarningMapper.clearAll();
    }

    @Override
    public Integer AllHaveRead() {
        return apiWarningMapper.AllHaveRead();
    }


    //------------------------代码生成-------------------------------

    /**
     * 查询API预警
     *
     * @param id API预警主键
     * @return API预警
     */
    @Override
    public ApiRecord selectApiRecordById(Long id) {
        return apiRecordMapper.selectApiRecordById(id);
    }

    /**
     * 查询API预警列表
     *
     * @param apiRecord API预警
     * @return API预警
     */
    @Override
    public List<ApiRecord> selectApiRecordList(ApiRecord apiRecord) {
        return apiRecordMapper.selectApiRecordList(apiRecord);
    }

    /**
     * 修改API预警
     *
     * @param apiRecord API预警
     * @return 结果
     */
    @Override
    public int updateApiRecord(ApiRecord apiRecord) {
        return apiRecordMapper.updateApiRecord(apiRecord);
    }

    @Override
    public List<String> getApiName() {
        List<ApiRecord> recordList = apiRecordMapper.selectList(new QueryWrapper<ApiRecord>().orderByDesc("create_time"));
        return recordList.stream()
                .map(ApiRecord::getApiName)
                .collect(Collectors.toList());
    }


}
