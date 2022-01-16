package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.tianxing.TianXingWXRSFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * @author xiejs
 * @since 2022-01-11
 */
@Log4j2
@Component
public class TianXingWXRSFeignFactory implements FallbackFactory<TianXingWXRSFeignClient> {
    @Override
    public TianXingWXRSFeignClient create(Throwable cause) {
        log.error("api模块天行微信热搜榜服务调用失败:{},执行降级处理", cause.getMessage());
        return key -> {
            JSONObject jsonObject = new JSONObject();
            //构建一个异常json给下层接口处理
            jsonObject.put(DEMOTE_ERROR, R.FAIL);
            return jsonObject;
        };
    }
}
