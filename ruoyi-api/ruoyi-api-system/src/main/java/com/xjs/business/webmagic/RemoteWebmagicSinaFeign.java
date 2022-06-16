package com.xjs.business.webmagic;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.factory.RemoteWebmagicSinaFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * 内部 调用 新浪 爬虫定时任务
 * @author xiejs
 * @since 2022-02-15
 */
@FeignClient(contextId = "remoteWebmagicSinaFeign",
        value = ServiceNameConstants.BUSINESS_WEBMAGIC_SERVICE,
        fallbackFactory = RemoteWebmagicSinaFactory.class)
public interface RemoteWebmagicSinaFeign {

    @GetMapping("/sinaNews/taskForPRC")
    R sinaTaskForPRC();

    @GetMapping("/sinaNews/getNewsForRpc")
    R<Map<Object, Object>> getNews();
}
