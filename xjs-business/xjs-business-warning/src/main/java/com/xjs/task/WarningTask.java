package com.xjs.task;

import com.xjs.domain.ApiRecord;
import com.xjs.handler.RecordDateHandler;
import com.xjs.service.ApiWarningService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 预警相关定时任务
 * @author xiejs
 * @since 2022-01-21
 */
@Component
@Log4j2
public class WarningTask extends RecordDateHandler {

    @Autowired
    private ApiWarningService apiWarningService;

    /**
     * 处理预警api信息的每天调用次数<br>
     * 最近10次运行:<br>
     * 2022-01-22 00:00:10<br>
     * 2022-01-23 00:00:10<br>
     * 2022-01-24 00:00:10<br>
     * 2022-01-25 00:00:10<br>
     * 2022-01-26 00:00:10<br>
     * 2022-01-27 00:00:10<br>
     * 2022-01-28 00:00:10<br>
     * 2022-01-29 00:00:10<br>
     * 2022-01-30 00:00:10<br>
     * 2022-01-31 00:00:10<br>
     */
    @Scheduled(cron = "10 0 0 * * ? ")
    public void handleRecordDate() {
        List<ApiRecord> apiRecordList = apiWarningService.selectApiRecordList(new ApiRecord());
        List<ApiRecord> handleDate = super.handleDate(apiRecordList);
        handleDate.forEach(data ->{
            apiWarningService.updateApiRecordByUrl(data);
        });
        log.info("定时任务处理预警api信息的每天调用次数完毕");
    }


}
