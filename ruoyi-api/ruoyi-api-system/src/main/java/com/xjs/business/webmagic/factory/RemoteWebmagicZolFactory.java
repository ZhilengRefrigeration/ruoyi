package com.xjs.business.webmagic.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagicZolFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 内部 调用 中关村数据 爬虫定时任务feign降级
 *
 * @author xiejs
 * @since 2022-04-18
 */
@Log4j2
@Component
public class RemoteWebmagicZolFactory implements FallbackFactory<RemoteWebmagicZolFeign> {
    @Override
    public RemoteWebmagicZolFeign create(Throwable cause) {
        return () -> {
            log.error("中关村数据爬虫定时任务降级");
            return R.fail("中关村数据爬虫定时任务降级");
        };
    }
}
