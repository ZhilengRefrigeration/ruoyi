package com.xjs.service.impl;

import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.log.RemoteLogFeign;
import com.xjs.business.warning.RemoteWarningCRUDFeign;
import com.xjs.business.warning.domain.ApiRecord;
import com.xjs.service.ApiStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * api统计服务接口实现
 *
 * @author xiejs
 * @since 2022-01-25
 */
@Service
@SuppressWarnings("all")
public class ApiStatisticsServiceImpl implements ApiStatisticsService {

    @Autowired
    private RemoteWarningCRUDFeign remoteWarningCRUDFeign;
    @Autowired
    private RemoteLogFeign remoteLogFeign;

    @Override
    public Map<String, List> statisticsHistoryApi() {
        List<ApiRecord> recordList = getData();
        Map<String, List> map = new HashMap<>();
        List<String> apiNames = new ArrayList<>();
        List<Long> count = new ArrayList<>();
        recordList.forEach(record -> {
            apiNames.add(record.getApiName());
            count.add(record.getTotalCount());
        });
        map.put("apiNames", apiNames);
        map.put("count", count);
        return map;
    }

    @Override
    public Map<String, List> statisticsTodayApi() {
        List<ApiRecord> recordList = getData();
        Map<String, List> map = new HashMap<>();
        List<String> apiNames = new ArrayList<>();
        List<Long> count = new ArrayList<>();
        recordList.forEach(record -> {
            apiNames.add(record.getApiName());
            count.add(record.getDayCount());
        });
        map.put("apiNames", apiNames);
        map.put("count", count);
        return map;
    }

    @Override
    public Map<String, List> statisticsByDate(String startDate, String endDate) {
        R<Map<String, List>> r = remoteLogFeign.statisticsByDate(startDate, endDate);
        if (r.getCode() == HttpStatus.SUCCESS) {
            return r.getData();
        }else {
            return new HashMap<>();
        }
    }


    /**
     * 获取R中的data
     *
     * @return List
     */
    private List<ApiRecord> getData() {
        R<List<ApiRecord>> listR = remoteWarningCRUDFeign.selectApiRecordListForRPC();
        if (listR.getCode() == HttpStatus.SUCCESS) {
            return listR.getData();
        } else {
            return new ArrayList<>();
        }
    }
}
