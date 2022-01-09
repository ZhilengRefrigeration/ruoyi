package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.roll.RollMMYJFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * roll每日一句降级
 * @author xiejs
 * @since 2022-01-08
 */
@Component
@Log4j2
public class RollMMYJFeignFactory implements FallbackFactory<RollMMYJFeignClient> {
    @Override
    public RollMMYJFeignClient create(Throwable cause) {
        log.error("api模块roll每日一句服务调用失败:{},执行降级处理", cause.getMessage());
        return requestBody -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", R.FAIL);
            return jsonObject;
        };
    }
}
