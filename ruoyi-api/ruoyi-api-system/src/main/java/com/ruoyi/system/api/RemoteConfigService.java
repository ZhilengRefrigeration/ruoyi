package com.ruoyi.system.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.factory.RemoteConfigFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 系统配置服务
 * @author xiejs
 * @since 2022-02-20
 */
@FeignClient(contextId = "remoteConfigService",
        value = ServiceNameConstants.SYSTEM_SERVICE,
        fallbackFactory = RemoteConfigFallbackFactory.class)
public interface RemoteConfigService {

    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/config/configKeyForRPC/{configKey}")
    R<String> getConfigKeyForRPC(@PathVariable("configKey") String configKey);

}
