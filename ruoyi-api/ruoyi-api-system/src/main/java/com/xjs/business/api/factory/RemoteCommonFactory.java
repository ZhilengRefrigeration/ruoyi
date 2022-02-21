package com.xjs.business.api.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.RemoteCommonFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * API模块通用 降级
 * @author xiejs
 * @since 2022-02-21
 */
@Component
@Log4j2
public class RemoteCommonFactory implements FallbackFactory<RemoteCommonFeign> {

    @Override
    public RemoteCommonFeign create(Throwable cause) {
        return new RemoteCommonFeign() {
            @Override
            public R CheckApiStatusForRPC() {
                log.error("API模块通用 降级"+cause.getMessage());
                return R.fail("API模块通用 降级");
            }
        };
    }
}
