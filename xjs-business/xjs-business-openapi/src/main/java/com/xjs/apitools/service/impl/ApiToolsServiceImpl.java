package com.xjs.apitools.service.impl;

import com.xjs.apitools.domain.ApiHoliday;
import com.xjs.apitools.factory.ApiToolsFactory;
import com.xjs.apitools.factory.impl.RollHolidayFactory;
import com.xjs.apitools.service.ApiToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * api工具服务实现
 *
 * @author xiejs
 * @since 2022-01-18
 */
@Service
public class ApiToolsServiceImpl implements ApiToolsService {

    private ApiToolsFactory<ApiHoliday, Object> holidayFactory;

    @Autowired
    public void setHolidayFactory(RollHolidayFactory rollHolidayFactory) {
        this.holidayFactory = rollHolidayFactory;
    }


    @Override
    public List<ApiHoliday> getApiHolidayList() {
        List<ApiHoliday> apiHolidayList = holidayFactory.apiDataList();
        List<ApiHoliday> collect = apiHolidayList.stream().map(holidayFactory -> {
            if (holidayFactory.getResidueDays() >= 0) {
                return holidayFactory;
            }else {
                return null;
            }
        }).collect(Collectors.toList());
        collect.removeIf(Objects::isNull);
        return collect;
    }
}
