package com.xjs.mall;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.xjs.mall.to.SkuHasStockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 仓库服务feign
 * @author xiejs
 * @since 2022-04-06
 */
@FeignClient(contextId = "remoteWareFeign",
        value = ServiceNameConstants.MALL_WARE_SERVICE)
public interface RemoteWareFeign {

    @PostMapping("/ware/waresku/hasStock")
    List<SkuHasStockVo> getSkuHasStock(@RequestBody List<Long> skuIds);
}
