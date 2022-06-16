package com.xjs.business.webmagic.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagicSinaFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

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
                log.error("新浪 爬虫定时任务 降级------服务可能正在运行");
                return R.fail("降级处理------服务可能正在运行");
            }

            @Override
            public R<Map<Object, Object>> getNews() {
                log.error("新浪 获取最新新闻 降级------服务可能正在运行");
                return R.fail("降级处理------服务可能正在运行");
            }
        };
    }
}
