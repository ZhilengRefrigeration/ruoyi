package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.RollTranslationFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * roll翻译降级
 * @author xiejs
 * @since 2022-01-07
 */
@Log4j2
@Component
public class RollTranslationFeignFactory implements FallbackFactory<RollTranslationFeignClient> {
    @Override
    public RollTranslationFeignClient create(Throwable cause) {
        log.error("api模块roll翻译服务调用失败:{},执行降级处理", cause.getMessage());
        return  qo -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", R.FAIL);
            return jsonObject;
        };
    }
}
