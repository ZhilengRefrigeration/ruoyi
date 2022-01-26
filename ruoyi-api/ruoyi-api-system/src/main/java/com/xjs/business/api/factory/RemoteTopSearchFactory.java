package com.xjs.business.api.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.RemoteTopSearchFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author xiejs
 * @since 2022-01-26
 */
@Component
@Log4j2
public class RemoteTopSearchFactory implements FallbackFactory<RemoteTopSearchFeign> {

    @Override
    public RemoteTopSearchFeign create(Throwable cause) {
        return new RemoteTopSearchFeign() {
            @Override
            public R<Map<String, List>> topSearchForRPC() {
                log.error("api模块获取热搜服务调用失败:{}", cause.getMessage());
                return R.fail("文案服务调用失败" + cause.getMessage());
            }
        };
    }
}
