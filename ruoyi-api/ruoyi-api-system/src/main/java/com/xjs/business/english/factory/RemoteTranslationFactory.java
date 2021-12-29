package com.xjs.business.english.factory;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.factory.RemoteFileFallbackFactory;
import com.xjs.business.english.RemoteTranslationFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @desc  调用翻译接口降级处理
 * @create 2021-12-29
 */
@Component
public class RemoteTranslationFactory implements FallbackFactory<RemoteTranslationFeign> {

    private static final Logger log = LoggerFactory.getLogger(RemoteFileFallbackFactory.class);

    @Override
    public RemoteTranslationFeign create(Throwable cause) {
        log.error("api模块翻译服务调用失败:{}", cause.getMessage());
        return content -> R.fail("翻译服务调用失败" + cause.getMessage());
    }
}
