package com.xjs.business.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.factory.RemoteTopSearchFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * 获取热搜榜内部远程调用feign
 *
 * @author xiejs
 * @since 2022-01-26
 */
@FeignClient(contextId = "remoteTopSearchFeign",
        value = ServiceNameConstants.BUSINESS_OPENAPI_SERVICE,
        fallbackFactory = RemoteTopSearchFactory.class)
public interface RemoteTopSearchFeign {


   @GetMapping("/topsearch/getTopsearchForRPC")
    R<Map<String, List>> topSearchForRPC();
}
