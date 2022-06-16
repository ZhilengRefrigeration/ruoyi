package com.xjs.business.english;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.english.domain.EnglishWordDTO;
import com.xjs.business.english.factory.RemoteEnglishFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 调用openapi服务AreaController feign
 *
 * @author xiejs
 * @since 2022-03-23
 */
@FeignClient(contextId = "remoteEnglishFeign",
        value = ServiceNameConstants.BUSINESS_ENGLISH_SERVICE,
        fallbackFactory = RemoteEnglishFactory.class)
public interface RemoteEnglishFeign {

    @GetMapping("/word/getEnglishWordForRpc")
    R<List<EnglishWordDTO>> getEnglishWordByRandom();

}
