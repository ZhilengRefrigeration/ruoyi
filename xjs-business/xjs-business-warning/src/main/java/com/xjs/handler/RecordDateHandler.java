package com.xjs.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.xjs.domain.ApiRecord;

import java.util.Date;
import java.util.List;

/**
 * 日期的处理
 * @author xiejs
 * @since 2022-01-21
 */
public class RecordDateHandler {

    protected List<ApiRecord> handleDate(List<ApiRecord> apiRecordList) {
        if (CollUtil.isNotEmpty(apiRecordList)) {
            apiRecordList.forEach(apiRecord -> {
                String dateTime = DateUtil.formatDateTime(apiRecord.getUpdateTime());
                Date date = DateUtil.parseDate(dateTime).toJdkDate();
                //当前时间和最后一次修改时间间隔天数（超过1 就清零）
                long compareTime = DateUtil.between(date, new Date(), DateUnit.DAY);
                if (compareTime > 0) {
                    apiRecord.setDayCount(0L);
                }
            });
        }
        return apiRecordList;
    }

}
