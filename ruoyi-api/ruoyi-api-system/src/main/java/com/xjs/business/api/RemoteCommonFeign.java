package com.xjs.business.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.factory.RemoteCommonFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * API模块通用feign
 * @author xiejs
 * @since 2022-02-21
 */
@FeignClient(contextId = "remoteCommonFeign",
        value = ServiceNameConstants.BUSINESS_OPENAPI_SERVICE,
        fallbackFactory = RemoteCommonFactory.class)
public interface RemoteCommonFeign {


    @GetMapping("/common/checkApiStatus")
    R CheckApiStatusForRPC();

}
