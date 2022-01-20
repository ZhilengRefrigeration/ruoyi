package com.xjs.common.client.api.roll;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.common.client.factory.RollChineseDictFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.ROLL_CHINESE_DICT;
import static com.xjs.consts.ApiConst.ROLL_CHINESE_DICT_URL;

/**
 * roll 汉语字典api接口feign远程调用
 * @author xiejs
 * @since 2022-01-20
 */
@FeignClient(name = "rollChineseDict", url = ROLL_CHINESE_DICT_URL, fallbackFactory = RollChineseDictFeignFactory.class)
public interface RollChineseDictFeignClient {

    @GetMapping()
    @ApiLog(name = ROLL_CHINESE_DICT,
            url = ROLL_CHINESE_DICT_URL,
            method = "Get")
    JSONObject chineseDictApi(@SpringQueryMap RequestBody requestBody);
}
