package com.xjs.business.webmagic.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagic36wallpaperFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 内部 调用 36壁纸网 爬虫定时任务降级
 * @author xiejs
 * @since 2022-02-20
 */
@Component
@Log4j2
public class RemoteWebmagic36wallpaperFactory implements FallbackFactory<RemoteWebmagic36wallpaperFeign> {
    @Override
    public RemoteWebmagic36wallpaperFeign create(Throwable cause) {
        return new RemoteWebmagic36wallpaperFeign() {
            @Override
            public R _36wallpaperTaskForPRC() {
                log.error("新浪 爬虫定时任务 降级------服务可能正在运行");
                return R.fail("降级处理------服务可能正在运行");
            }
        };
    }
}
