package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.alapi.AlapiJokeAllFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * alapi平台笑话大全降级处理
 * @author xiejs
 * @since 2022-02-15
 */
@Component
@Log4j2
public class AlapiJokeAllFeignFactory implements FallbackFactory<AlapiJokeAllFeignClient> {
    @Override
    public AlapiJokeAllFeignClient create(Throwable cause) {
        log.error("api模块alapi平台笑话大全服务调用失败:{},执行降级处理", cause.getMessage());
        return (token -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DEMOTE_ERROR, R.FAIL);
            return jsonObject;
        });
    }
}
