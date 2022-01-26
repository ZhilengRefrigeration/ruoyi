package com.xjs.business.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.factory.RemoteWeatherFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/weather/getHistoryWeatherForRPC")
    R<Map<String, List>> getHistoryWeatherForRPC(@RequestParam("startDate")String startDate,
                                                 @RequestParam("endDate")String endDate);

    @GetMapping("/weather/getFutureWeatherForRPC")
    R<Map<String, List<String>>> getFutureWeatherForRPC();
}
