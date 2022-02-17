package com.xjs.business.log;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.log.domain.ApiLog;
import com.xjs.business.log.domain.WebmagicLog;
import com.xjs.business.log.factory.RemoteLogFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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


    @PostMapping("reptileLog/saveForPRC")
    public R<Object> saveReptileLog(@RequestBody WebmagicLog webmagicLog);
}
