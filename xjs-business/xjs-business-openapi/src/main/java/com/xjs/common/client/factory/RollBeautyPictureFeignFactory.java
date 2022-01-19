package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.roll.RollBeautyPictureFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * roll mm图片api接口feign远程调用 降级
 * @author xiejs
 * @since 2022-01-19
 */
@Component
@Log4j2
public class RollBeautyPictureFeignFactory implements FallbackFactory<RollBeautyPictureFeignClient> {
    @Override
    public RollBeautyPictureFeignClient create(Throwable cause) {
        log.error("api模块roll mm图片服务调用失败:{},执行降级处理", cause.getMessage());
        return requestBody -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DEMOTE_ERROR, R.FAIL);
            return jsonObject;
        };
    }
}
