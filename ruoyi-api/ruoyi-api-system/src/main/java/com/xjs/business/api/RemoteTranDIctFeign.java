package com.xjs.business.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.domain.TranslationVo;
import com.xjs.business.api.factory.RemoteTranDictFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xiejs
 * @desc  rpc调用翻译字典接口
 * @create 2021-12-30
 */
@FeignClient(contextId = "remoteTranDictFeign",
        value = ServiceNameConstants.BUSINESS_OPENAPI_SERVICE,
        fallbackFactory = RemoteTranDictFactory.class)
public interface RemoteTranDIctFeign {

    @GetMapping(value = "/tranDict/tranDictForRPC")
    R<TranslationVo> tranDict(@RequestParam("content") String content);

}
