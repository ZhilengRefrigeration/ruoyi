package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.tianxing.TianXingQWRSFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @since 2022-01-10
 */
@Component
@Log4j2
public class TianXingQWRSFeignFactory implements FallbackFactory<TianXingQWRSFeignClient> {

    @Override
    public TianXingQWRSFeignClient create(Throwable cause) {
        log.error("api模块天行全网热搜榜服务调用失败:{},执行降级处理", cause.getMessage());
        return requestBody -> {
            JSONObject jsonObject = new JSONObject();
            //构建一个异常json给下层接口处理
            jsonObject.put("error", R.FAIL);
            return jsonObject;
        };
    }
}
