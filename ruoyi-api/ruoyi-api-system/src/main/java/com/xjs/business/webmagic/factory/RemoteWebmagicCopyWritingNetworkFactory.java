package com.xjs.business.webmagic.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagicCopyWritingNetworkFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 内部 调用 文案网 爬虫定时任务 降级
 * @author xiejs
 * @since 2022-02-17
 */
@Component
@Log4j2
public class RemoteWebmagicCopyWritingNetworkFactory implements FallbackFactory<RemoteWebmagicCopyWritingNetworkFeign> {
    @Override
    public RemoteWebmagicCopyWritingNetworkFeign create(Throwable cause) {
        return new RemoteWebmagicCopyWritingNetworkFeign() {
            @Override
            public R copyWritingNetworkTaskForPRC() {
                log.error("文案网 爬虫定时任务 降级------服务可能正在运行");
                return R.fail("降级处理------服务可能正在运行");
            }
        };
    }
}
