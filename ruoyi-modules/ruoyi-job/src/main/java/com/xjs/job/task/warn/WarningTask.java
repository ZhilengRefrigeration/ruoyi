package com.xjs.job.task.warn;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.warning.RemoteWarningCRUDFeign;
import com.xjs.business.warning.domain.ApiRecord;
import com.xjs.job.aop.TaskLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 预警相关定时任务
 *
 * @author xiejs
 * @since 2022-01-25
 */
@Component("WarningTask")
@Log4j2
public class WarningTask {
    @Resource
    private RemoteWarningCRUDFeign remoteWarningCRUDFeign;


    /**
     * 处理预警api信息的每天调用次数
     */
    @TaskLog(name = "预警任务")
    public void handleRecordDate() {
        log.info("---------------预警次数初始化定时任务Start-------------------");
        R<JSONArray> listR = remoteWarningCRUDFeign.findRecordListForRPC();
        log.info("预警次数初始化定时任务结果:code={},msg={},data={}",listR.getCode(),listR.getMsg(),listR.getData());
        JSONArray jsonArray = listR.getData();
        List<ApiRecord> recordList = jsonArray.toJavaList(ApiRecord.class);
        List<ApiRecord> handleDate = this.handleDate(recordList);
        handleDate.forEach(data -> {
            remoteWarningCRUDFeign.updateApiRecordForRPC(data);
        });
        log.info("---------------预警次数初始化定时任务end---------------------");
    }


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
