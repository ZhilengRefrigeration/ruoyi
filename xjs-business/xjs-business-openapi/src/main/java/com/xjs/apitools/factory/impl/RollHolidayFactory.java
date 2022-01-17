package com.xjs.apitools.factory.impl;

import com.xjs.apitools.domain.ApiHoliday;
import com.xjs.apitools.factory.ApiToolsFactory;
import com.xjs.config.RollProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * roll平台获取节假日api工厂实现
 * @author xiejs
 * @since 2022-01-17
 */
@Component
@Log4j2
public class RollHolidayFactory implements ApiToolsFactory<ApiHoliday,Object> {

    @Autowired
    private RollProperties rollProperties;




    @Override
    public ApiHoliday ApiData() {


        return null;
    }
}
