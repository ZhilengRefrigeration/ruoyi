package com.xjs.business.api.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.RemoteTranDIctFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @desc  翻译字段服务降级处理
 * @create 2021-12-30
 */
@Component
public class RemoteTranDictFactory implements FallbackFactory<RemoteTranDIctFeign> {
    private static final Logger log = LoggerFactory.getLogger(RemoteTranDictFactory.class);

    @Override
    public RemoteTranDIctFeign create(Throwable cause) {
        log.error("api模块文案服务调用失败:{}", cause.getMessage());
        return content -> R.fail("文案服务调用失败" + cause.getMessage());
    }
}
