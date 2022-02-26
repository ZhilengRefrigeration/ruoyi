package com.xjs.time.factory.impl;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.api.time.TimeFeignClient;
import com.xjs.exception.ApiException;
import com.xjs.time.factory.TimeFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 获取时间工厂实现
 *
 * @author xiejs
 * @since 2022-02-26
 */
@Component
@Log4j2
public class TimeFactoryImpl implements TimeFactory {

    @Autowired
    private TimeFeignClient timeFeignClient;

    @Override
    public String getTime() {
        String ttd_pid = "pubmatic";
        String fmt = "json";
        JSONObject jsonObject = timeFeignClient.timeApi(ttd_pid, fmt);
        if (jsonObject.containsKey(DEMOTE_ERROR)) {
            throw new ApiException("时间接口调用异常");
        }

        Date time = jsonObject.getDate("TDID_CREATED_AT");
        SimpleDateFormat bjSdf = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        TimeZone tz = TimeZone.getTimeZone("GMT+16");
        TimeZone.setDefault(tz);
        bjSdf.setTimeZone(tz);
        return bjSdf.format(time);
    }
}
