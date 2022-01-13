package com.xjs.business.log.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.factory.RemoteTranDictFactory;
import com.xjs.business.log.RemoteLogFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 日志远程调用rpc服务降级处理
 * @author xiejs
 * @since 2022-01-13
 */
@Component
public class RemoteLogFactory implements FallbackFactory<RemoteLogFeign> {
    private static final Logger log = LoggerFactory.getLogger(RemoteLogFactory.class);

    @Override
    public RemoteLogFeign create(Throwable cause) {
        log.error("日志模块服务添加调用失败:{}", cause.getMessage());
        return apiLog -> R.fail("日志模块服务添加调用失败" + cause.getMessage());
    }
}
