package com.xjs.business.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.domain.CopyWriting;
import com.xjs.business.api.factory.RemoteCopyWritingFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * RPC远程调用文案接口服务
 * @author xiejs
 * @since  2021-12-27
 */
@FeignClient(contextId = "remoteCopyWritingFeign",
        value = ServiceNameConstants.BUSINESS_OPENAPI_SERVICE,
        fallbackFactory = RemoteCopyWritingFactory.class)
@FunctionalInterface
public interface RemoteCopyWritingFeign {
    /**
     * 获取文案
     *
     * @return 结果
     */
    @GetMapping(value = "/copyWriting/forPRC")
    R<CopyWriting> copyWriting();

}
