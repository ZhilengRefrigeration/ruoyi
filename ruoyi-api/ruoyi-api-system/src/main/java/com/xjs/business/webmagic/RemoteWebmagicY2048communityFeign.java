package com.xjs.business.webmagic;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.webmagic.factory.RemoteWebmagicWeiXinSouGouFactory;
import com.xjs.business.webmagic.factory.RemoteWebmagicY2048communityFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 2048远程调用
 *
 * @author xiejs
 * @since 2022-06-20
 */
@FeignClient(contextId = "remoteWebmagicY2048communityFeign",
        value = ServiceNameConstants.BUSINESS_WEBMAGIC_SERVICE,
        fallbackFactory = RemoteWebmagicY2048communityFactory.class)
public interface RemoteWebmagicY2048communityFeign {

    @GetMapping("/y2048community/taskForPRC")
    R y2048communityTaskForPRC();
}

