package com.xjs.mall;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.xjs.mall.other.R;
import com.xjs.mall.to.es.SkuEsModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 检索服务feign
 * @author xiejs
 * @since 2022-04-06
 */
@FeignClient(contextId = "remoteSearchFeign",
        value = ServiceNameConstants.MALL_SEARCH_SERVICE)
public interface RemoteSearchFeign {

    @PostMapping("/search/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModelList);

}
