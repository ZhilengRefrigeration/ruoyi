package com.xjs.business.api.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.RemoteWeatherFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 内部调用天气服务降级
 * @author xiejs
 * @since 2022-01-16
 */
@Component
public class RemoteWeatherFactory implements FallbackFactory<RemoteWeatherFeign> {
    private static final Logger log = LoggerFactory.getLogger(RemoteWeatherFactory.class);

    @Override
    public RemoteWeatherFeign create(Throwable cause) {
        log.error("api模块天气服务调用失败:{}", cause.getMessage());
        return () -> R.fail("天气服务调用失败" + cause.getMessage());
    }
}
