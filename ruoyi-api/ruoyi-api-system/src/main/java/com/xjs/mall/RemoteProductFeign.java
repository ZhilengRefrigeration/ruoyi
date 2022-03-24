package com.xjs.mall;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.xjs.mall.other.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用商品服务feign
 *
 * @author xiejs
 * @since 2022-03-24
 */
@FeignClient(contextId = "remoteProductFeign",
        value = ServiceNameConstants.MALL_PRODUCT_SERVICE)
public interface RemoteProductFeign {

    @GetMapping("/product/skuinfo/getSkuNameByIdForRPC/{skuId}")
    R getSkuNameById(@PathVariable("skuId") Long skuId);
}
