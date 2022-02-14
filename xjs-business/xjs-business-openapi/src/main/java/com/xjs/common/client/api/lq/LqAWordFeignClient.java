package com.xjs.common.client.api.lq;

import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.LqAWordFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.LQ_A_WORD;
import static com.xjs.consts.ApiConst.LQ_A_WORD_URL;

/**
 * 零七平台 一言 feign
 * @author xiejs
 * @since 2022-02-14
 */
@FeignClient(name = "lqAWord", url = LQ_A_WORD_URL, fallbackFactory = LqAWordFeignFactory.class)
public interface LqAWordFeignClient {
    @GetMapping( headers ={ "Accept-Encoding=''"})//解决响应乱码
    @ApiLog(name = LQ_A_WORD,
            url = LQ_A_WORD_URL,
            method = "Get")
    String aWordApi();
}
