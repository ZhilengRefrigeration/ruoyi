package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.tianxing.TianXingDYRSFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @since 2022-01-12
 */
@Component
@Log4j2
public class TianXingDYRSFeignFactory implements FallbackFactory<TianXingDYRSFeignClient> {
    @Override
    public TianXingDYRSFeignClient create(Throwable cause) {
        log.error("api模块天行抖音热搜榜服务调用失败:{},执行降级处理", cause.getMessage());
        return key -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", R.FAIL);
            return jsonObject;
        };
    }
}
