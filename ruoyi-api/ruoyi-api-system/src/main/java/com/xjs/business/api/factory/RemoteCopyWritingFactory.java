package com.xjs.business.api.factory;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.factory.RemoteFileFallbackFactory;
import com.xjs.business.api.RemoteCopyWritingFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @desc  文案rpc降级服务处理
 * @create 2021-12-27
 */
@Component
public class RemoteCopyWritingFactory implements FallbackFactory<RemoteCopyWritingFeign> {

    private static final Logger log = LoggerFactory.getLogger(RemoteFileFallbackFactory.class);

    @Override
    public RemoteCopyWritingFeign create(Throwable cause) {
        log.error("英语模块文案服务调用失败:{}", cause.getMessage());
        return () -> R.fail("文案服务调用失败" + cause.getMessage());
    }
}
