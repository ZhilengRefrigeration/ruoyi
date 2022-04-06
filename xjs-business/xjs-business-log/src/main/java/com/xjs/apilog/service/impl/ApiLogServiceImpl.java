package com.xjs.apilog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.domain.R;
import com.xjs.apilog.domain.ApiLog;
import com.xjs.apilog.mapper.ApiLogMapper;
import com.xjs.apilog.service.IApiLogService;
import com.xjs.apilog.vo.ApiLogVo;
import com.xjs.business.warning.RemoteWarningCRUDFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志Service业务层处理
 *
 * @author xjs
 * @date 2021-12-26
 */
@Service
public class ApiLogServiceImpl extends ServiceImpl<ApiLogMapper,ApiLog> implements IApiLogService {
    @Resource
    private ApiLogMapper apiLogMapper;
    @Autowired
    private RemoteWarningCRUDFeign remoteWarningCRUDFeign;

    //------------------------代码自动生成-----------------------------------

    /**
     * 查询日志
     *
     * @param id 日志主键
     * @return 日志
     */
    @Override
    public ApiLog selectApiLogById(Long id) {
        return apiLogMapper.selectApiLogById(id);
    }

    /**
     * 查询日志列表
     *
     * @param apiLog 日志
     * @return 日志
     */
    @Override
    public List<ApiLog> selectApiLogList(ApiLog apiLog) {
        return apiLogMapper.selectApiLogList(apiLog);
    }

    /**
     * 批量删除日志
     *
     * @param ids 需要删除的日志主键
     * @return 结果
     */
    @Override
    public int deleteApiLogByIds(Long[] ids) {
        return apiLogMapper.deleteApiLogByIds(ids);
    }

    /**
     * 删除日志信息
     *
     * @param id 日志主键
     * @return 结果
     */
    @Override
    public int deleteApiLogById(Long id) {
        return apiLogMapper.deleteApiLogById(id);
    }

    @Override
    public List<String> getApiName() {
        R<List<String>> apiName = remoteWarningCRUDFeign.getApiNameForRPC();
        if (apiName.getCode() == HttpStatus.SUCCESS) {
            return apiName.getData();
        }
        return new ArrayList<String>();
    }

    @Override
    public Map<String, List> statisticsByDate(String startDate, String endDate) {
        List<ApiLogVo> recordList =apiLogMapper.statisticsByDate(startDate, endDate);
        Map<String, List> map = new HashMap<>();
        List<String> apiNames = new ArrayList<>();
        List<Long> count = new ArrayList<>();
        recordList.forEach(record ->{
            apiNames.add(record.getApiName());
            count.add(record.getCount());
        });
        map.put("apiNames", apiNames);
        map.put("count", count);
        return map;
    }
}
