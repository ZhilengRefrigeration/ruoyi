package com.xjs.business.webmagic.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagicY2048communityFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 2048降级
 * @author xiejs
 * @since 2022-06-20
 */
@Component
@Log4j2
public class RemoteWebmagicY2048communityFactory implements FallbackFactory<RemoteWebmagicY2048communityFeign> {
    @Override
    public RemoteWebmagicY2048communityFeign create(Throwable cause) {
        return new RemoteWebmagicY2048communityFeign() {
            @Override
            public R y2048communityTaskForPRC() {
                log.error("2048 爬虫定时任务 降级------服务可能正在运行");
                return R.fail("降级处理------服务可能正在运行");
            }
        };
    }
}
