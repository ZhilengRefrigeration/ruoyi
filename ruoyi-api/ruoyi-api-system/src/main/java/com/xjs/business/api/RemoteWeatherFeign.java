package com.xjs.business.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.factory.RemoteWeatherFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * rpc远程调用其他服务天气接口
 * @author xiejs
 * @since 2022-01-16
 */
@FeignClient(contextId = "remoteTWeatherFeign",
        value = ServiceNameConstants.BUSINESS_OPENAPI_SERVICE,
        fallbackFactory = RemoteWeatherFactory.class)
public interface RemoteWeatherFeign {

    @GetMapping("/weather/getWeatherForRPC")
    R getWeatherForRPC() ;
}
