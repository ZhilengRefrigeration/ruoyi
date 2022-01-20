package com.xjs.common.client.api.roll;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.common.client.factory.RollIdcardQueryFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.ROLL_IDCARD_QUERY;
import static com.xjs.consts.ApiConst.ROLL_IDCARD_QUERY_URL;

/**
 * roll身份证查询api接口feign远程调用
 * @author xiejs
 * @since 2022-01-20
 */
@FeignClient(name = "rollIdcardQuery", url = ROLL_IDCARD_QUERY_URL, fallbackFactory = RollIdcardQueryFeignFactory.class)
public interface RollIdcardQueryFeignClient {

    @GetMapping()
    @ApiLog(name = ROLL_IDCARD_QUERY,
            url = ROLL_IDCARD_QUERY_URL,
            method = "Get")
    JSONObject idcardQueryApi(@SpringQueryMap RequestBody requestBody);
}
