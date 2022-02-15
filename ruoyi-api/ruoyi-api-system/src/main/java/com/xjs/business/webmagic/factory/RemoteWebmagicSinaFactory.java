package com.xjs.business.webmagic.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagicSinaFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 内部 调用 新浪 爬虫定时任务 降级
 * @author xiejs
 * @since 2022-02-15
 */
@Component
@Log4j2
public class RemoteWebmagicSinaFactory implements FallbackFactory<RemoteWebmagicSinaFeign> {
    @Override
    public RemoteWebmagicSinaFeign create(Throwable cause) {

        return new RemoteWebmagicSinaFeign() {
            @Override
            public R sinaTaskForPRC() {
                log.error("新浪 爬虫定时任务 降级");
                return R.fail("降级处理");
            }
        };
    }
}
