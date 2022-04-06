package com.xjs.business.log;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.log.domain.ApiLog;
import com.xjs.business.log.domain.TaskLog;
import com.xjs.business.log.domain.WebmagicLog;
import com.xjs.business.log.factory.RemoteLogFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * rpc远程调用日志服务接口
 * @author xiejs
 * @since 2022-01-13
 */
@FeignClient(contextId = "remoteLogFeign",
        value = ServiceNameConstants.BUSINESS_LOG_SERVICE,
        fallbackFactory = RemoteLogFactory.class)
public interface RemoteLogFeign {

    @PostMapping("/apilog/forPRC")
    R<Object> saveApiLog(@RequestBody ApiLog apiLog);

    @GetMapping("/apilog/byDateForRPC")
    R<Map<String, List>> statisticsByDate(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate);


    @PostMapping("reptileLog/saveForPRC")
    R<Object> saveReptileLog(@RequestBody WebmagicLog webmagicLog);

    @PostMapping("taskLog/saveForPRC")
    R<Object> saveTaskLog(@RequestBody TaskLog taskLog);
}
