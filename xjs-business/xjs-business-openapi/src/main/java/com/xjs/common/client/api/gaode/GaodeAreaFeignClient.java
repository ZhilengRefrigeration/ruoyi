package com.xjs.common.client.api.gaode;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.GaodeAreaFeignFactory;
import com.xjs.weather.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.GAODE_AREA;
import static com.xjs.consts.ApiConst.GAODE_AREA_URL;

/**
 * 高德区域编码api feign
 *
 * @author xiejs
 * @since 2022-03-22
 */
@FeignClient(contextId = "gaodeAreaFeignClient", name = "gaodeArea", url = GAODE_AREA_URL, fallbackFactory = GaodeAreaFeignFactory.class)
public interface GaodeAreaFeignClient {

    @GetMapping()
    @ApiLog(name = GAODE_AREA,
            url = GAODE_AREA_URL,
            method = "Get")
    JSONObject AreaApi(@SpringQueryMap RequestBody requestBody);
}
