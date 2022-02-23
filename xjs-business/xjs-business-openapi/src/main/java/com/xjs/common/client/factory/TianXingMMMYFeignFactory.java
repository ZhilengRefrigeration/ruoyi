package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.tianxing.TianXingMMMYFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 天行 名人名言 降级
 * @author xiejs
 * @since  2021-12-29
 */
@Component
@Log4j2
public class TianXingMMMYFeignFactory implements FallbackFactory<TianXingMMMYFeignClient> {

    @Override
    public TianXingMMMYFeignClient create(Throwable cause) {
        log.error("api模块名人名言服务调用失败:{},执行降级处理", cause.getMessage());
        return requestBody -> {
            JSONObject jsonObject = new JSONObject();
            //构建一个异常json给下层接口处理
            jsonObject.put(DEMOTE_ERROR, R.FAIL);
            return jsonObject;
        };
    }
}
