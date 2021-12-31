package com.xjs.client;

import com.alibaba.fastjson.JSONObject;
import com.xjs.aop.ApiLog;
import com.xjs.client.factory.TianXingTranDictFeignFactory;
import com.xjs.copywriting.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.TIANXING_TRAN_DICT;
import static com.xjs.consts.ApiConst.TIANXING_TRAN_DICT_URL;

/**
 * @author xiejs
 * @desc  天行翻译字典接口api调用
 * @create 2021-12-30
 */
@FunctionalInterface
@FeignClient(name = "tianXingTranDict",url = TIANXING_TRAN_DICT_URL,fallbackFactory = TianXingTranDictFeignFactory.class)
public interface TianXingTranDictClient {

    @GetMapping
    @ApiLog(name = TIANXING_TRAN_DICT,
            url = TIANXING_TRAN_DICT_URL,
            method = "Get")
    JSONObject tranDictApi(@SpringQueryMap RequestBody requestBody);

}
