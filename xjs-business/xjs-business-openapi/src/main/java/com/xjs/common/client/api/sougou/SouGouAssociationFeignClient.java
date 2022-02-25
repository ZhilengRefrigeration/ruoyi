package com.xjs.common.client.api.sougou;

import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.SouGouAssociationFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.xjs.consts.ApiConst.SOUGOU_ASSOCIATION;
import static com.xjs.consts.ApiConst.SOUGOU__ASSOCIATION_URL;

/**
 * 搜狗语义联想api feign
 * @author xiejs
 * @since 2022-02-25
 */
@FeignClient(name = "souGouAssociation", url = SOUGOU__ASSOCIATION_URL, fallbackFactory = SouGouAssociationFeignFactory.class)
public interface SouGouAssociationFeignClient {

    @GetMapping
    @ApiLog(name = SOUGOU_ASSOCIATION,
            url = SOUGOU__ASSOCIATION_URL,
            method = "Get")
    String associationApi(@RequestParam("key") String key);
}
