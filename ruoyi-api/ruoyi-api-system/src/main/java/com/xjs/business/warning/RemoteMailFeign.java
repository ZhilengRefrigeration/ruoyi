package com.xjs.business.warning;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.xjs.business.warning.factory.RemoteMailFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 远程调用预警服务邮件feign
 * @author xiejs
 * @since 2022-04-13
 */
@FeignClient(contextId = "remoteMailFeign",
        value = ServiceNameConstants.BUSINESS_WARNING_SERVICE,
        fallbackFactory = RemoteMailFactory.class)
public interface RemoteMailFeign {

    @GetMapping("mail/send-weather-mail")
    void sendWeatherMailForRPC();
}
