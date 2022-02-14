package com.xjs.common.client.api.lq;

import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.LqDogDiaryFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.LQ_DOG_DIARY;
import static com.xjs.consts.ApiConst.LQ_DOG_DIARY_URL;

/**
 * 零七平台 dog日记 feign
 * @author xiejs
 * @since 2022-02-14
 */
@FeignClient(name = "lqDogDiary", url = LQ_DOG_DIARY_URL, fallbackFactory = LqDogDiaryFeignFactory.class)
public interface LqDogDiaryFeignClient {

    @GetMapping()
    @ApiLog(name = LQ_DOG_DIARY,
            url = LQ_DOG_DIARY_URL,
            method = "Get")
    String dogDiaryApi();
}
