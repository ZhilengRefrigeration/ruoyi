package com.ruoyi.system.api.factory;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.RemoteConfigService;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @since 2022-02-20
 */
@Component
@Log4j2
public class RemoteConfigFallbackFactory implements FallbackFactory<RemoteConfigService> {
    @Override
    public RemoteConfigService create(Throwable cause) {
        log.error("系统配置服务调用失败:{}", cause.getMessage());
        return new RemoteConfigService() {
            @Override
            public R<String> getConfigKeyForRPC(String configKey) {
                return R.fail("系统配置服务查询调用失败");
            }

            @Override
            public R editForRPC(String key, String value) {
                return R.fail("系统配置服务修改调用失败");
            }
        };
    }
}
