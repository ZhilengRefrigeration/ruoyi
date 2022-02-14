package com.xjs.common.client.api.lq;

import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.LqPoisonChickenFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.LQ_POISON_CHICKEN;
import static com.xjs.consts.ApiConst.LQ_POISON_CHICKEN_URL;

/**
 * 零七平台 毒鸡汤 feign
 * @author xiejs
 * @since 2022-02-14
 */
@FeignClient(name = "lqPoisonChicken", url = LQ_POISON_CHICKEN_URL, fallbackFactory = LqPoisonChickenFeignFactory.class)
public interface LqPoisonChickenFeignClient {

    @GetMapping()
    @ApiLog(name = LQ_POISON_CHICKEN,
            url = LQ_POISON_CHICKEN_URL,
            method = "Get")
    String poisonChickenApi();
}
