package com.xjs.mall;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.xjs.mall.other.R;
import com.xjs.mall.to.SkuReductionTo;
import com.xjs.mall.to.SpuBoundTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 远程调用优惠服务接口feign
 * @author xiejs
 * @since 2022-03-20
 */
@FeignClient(contextId = "remoteCouponFeign",
        value = ServiceNameConstants.MALL_COUPON_SERVICE)
public interface RemoteCouponFeign {


    /**
     * 保存spu优惠信息接口
     */
    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    /**
     * 保存满减、会员价信息接口
     * @param skuReductionTo 满减信息
     * @return R
     */
    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
