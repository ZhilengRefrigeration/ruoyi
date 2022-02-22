package com.xjs.business.webmagic.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.RemoteWebmagicWeiXinSouGouFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 *  内部 调用 微信搜狗 爬虫定时任务feign降级
 * @author xiejs
 * @since 2022-02-22
 */
@Component
@Log4j2
public class RemoteWebmagicWeiXinSouGouFactory implements FallbackFactory<RemoteWebmagicWeiXinSouGouFeign> {
    @Override
    public RemoteWebmagicWeiXinSouGouFeign create(Throwable cause) {
        return new RemoteWebmagicWeiXinSouGouFeign() {
            @Override
            public R WeiXinSouGouTaskForPRC() {
                log.error("微信搜狗 爬虫定时任务 降级------服务可能正在运行");
                return R.fail("降级处理------服务可能正在运行");
            }
        };
    }
}
