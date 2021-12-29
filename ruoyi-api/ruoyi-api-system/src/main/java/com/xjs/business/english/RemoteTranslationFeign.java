package com.xjs.business.english;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.english.domain.TranslationVo;
import com.xjs.business.english.factory.RemoteTranslationFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xiejs
 * @desc  rpc远程调用其他服务翻译接口
 * @create 2021-12-29
 */
@FeignClient(contextId = "remoteTranslationFeign",
        value = ServiceNameConstants.BUSINESS_OPENAPI_SERVICE,
        fallbackFactory = RemoteTranslationFactory.class)
public interface RemoteTranslationFeign {

    @GetMapping(value = "/translation/forRPC")
                                    //get请求传递单个参数需要此注解
    R<TranslationVo> translation(@RequestParam("content") String content);

}
