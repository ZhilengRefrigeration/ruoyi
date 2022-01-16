package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.tianxing.TianXingWBRSFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 天行微博热搜feign降级
 * @author xiejs
 * @since 2022-01-12
 */
@Log4j2
@Component
public class TianXingWBRSFeignFactory implements FallbackFactory<TianXingWBRSFeignClient> {
    @Override
    public TianXingWBRSFeignClient create(Throwable cause) {
        log.error("api模块天行微博热搜榜服务调用失败:{},执行降级处理", cause.getMessage());
        return key -> {
            JSONObject jsonObject = new JSONObject();
            //构建一个异常json给下层接口处理
            jsonObject.put(DEMOTE_ERROR, R.FAIL);
            return jsonObject;
        };
    }
}
