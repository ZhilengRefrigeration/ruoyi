package com.xjs.common.client.api.uomg;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.UomgEarthLoveFeignFactory;
import com.xjs.copywriting.domain.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

import static com.xjs.consts.ApiConst.UOMG_EARTH_LOVE;
import static com.xjs.consts.ApiConst.UOMG_EARTH_LOVE_URL;

/**
 * Uomg平台 土味情话 feign
 *
 * @author xiejs
 * @since 2022-02-14
 */
@FeignClient(name = "uomgEarthLove", url = UOMG_EARTH_LOVE_URL, fallbackFactory = UomgEarthLoveFeignFactory.class)
@Deprecated
public interface UomgEarthLoveFeignClient {
    @PostMapping(headers = {"Content-Type=text/html;charset=UTF-8"})
    @ApiLog(name = UOMG_EARTH_LOVE,
            url = UOMG_EARTH_LOVE_URL,
            method = "Get")
    @Deprecated
    JSONObject earthLoveApi(@SpringQueryMap RequestBody requestBody);
}
