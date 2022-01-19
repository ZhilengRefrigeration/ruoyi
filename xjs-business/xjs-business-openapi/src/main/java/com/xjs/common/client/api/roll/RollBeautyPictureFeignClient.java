package com.xjs.common.client.api.roll;

import com.alibaba.fastjson.JSONObject;
import com.xjs.annotation.ApiLog;
import com.xjs.apitools.domain.RequestBody;
import com.xjs.common.client.factory.RollBeautyPictureFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.xjs.consts.ApiConst.ROLL_BEAUTY_PICTURE;
import static com.xjs.consts.ApiConst.ROLL_BEAUTY_PICTURE_URL;

/**
 * roll mm图片api接口feign远程调用
 * @author xiejs
 * @since 2022-01-19
 */
@FeignClient(name = "rollBeautyPicture", url = ROLL_BEAUTY_PICTURE_URL, fallbackFactory = RollBeautyPictureFeignFactory.class)
public interface RollBeautyPictureFeignClient {

    @GetMapping()
    @ApiLog(name = ROLL_BEAUTY_PICTURE,
            url = ROLL_BEAUTY_PICTURE_URL,
            method = "Get")
    JSONObject beautyPictureApi(@SpringQueryMap RequestBody requestBody);

}
