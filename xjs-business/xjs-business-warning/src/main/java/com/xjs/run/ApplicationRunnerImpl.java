package com.xjs.run;

import com.xjs.domain.ApiRecord;
import com.xjs.handler.RecordDateHandler;
import com.xjs.service.ApiWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * springboot启动成功后执行
 * @author xiejs
 * @since 2022-01-19
 */
@Component
public class ApplicationRunnerImpl extends RecordDateHandler implements ApplicationRunner {

    @Autowired
    private ApiWarningService apiWarningService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("-----------------启动成功！！！---------------------");

        //启动后处理每日预警数量
        List<ApiRecord> apiRecordList = apiWarningService.selectApiRecordList(new ApiRecord());
        List<ApiRecord> handleDate = super.handleDate(apiRecordList);
        handleDate.forEach(data ->{
            apiWarningService.updateApiRecordByUrl(data);
        });
    }
}
