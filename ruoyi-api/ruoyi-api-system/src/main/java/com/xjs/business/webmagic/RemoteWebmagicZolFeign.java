package com.xjs.business.webmagic;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.factory.RemoteWebmagicZolFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 内部 调用 中关村数据 爬虫定时任务feign
 * @author xiejs
 * @since 2022-04-18
 */
@FeignClient(contextId = "remoteWebmagicZolFeign",
        value = ServiceNameConstants.BUSINESS_WEBMAGIC_SERVICE,
        fallbackFactory =  RemoteWebmagicZolFactory.class)
public interface RemoteWebmagicZolFeign {

    @GetMapping("/zol/taskForPRC")
    R<Long> ZolPhoneTaskForRPC();

}
