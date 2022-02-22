package com.xjs.business.webmagic;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.factory.RemoteWebmagicWeiXinSouGouFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 内部 调用 微信搜狗 爬虫定时任务feign
 * @author xiejs
 * @since 2022-02-22
 */
@FeignClient(contextId = "remoteWebmagicWeiXinSouGouFeign",
        value = ServiceNameConstants.BUSINESS_WEBMAGIC_SERVICE,
        fallbackFactory = RemoteWebmagicWeiXinSouGouFactory.class)
public interface RemoteWebmagicWeiXinSouGouFeign {

    @GetMapping("/weixin_sougou/taskForPRC")
    R WeiXinSouGouTaskForPRC() ;
}
