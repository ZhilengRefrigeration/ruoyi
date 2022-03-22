package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.gaode.GaodeAreaFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 高德区域编码api feign 降级处理
 * @author xiejs
 * @since 2022-03-22
 */
@Component
@Log4j2
public class GaodeAreaFeignFactory implements FallbackFactory<GaodeAreaFeignClient> {

    @Override
    public GaodeAreaFeignClient create(Throwable cause) {
        log.error("api模块高德区域编码服务调用失败:{},执行降级处理", cause.getMessage());
        return res ->{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DEMOTE_ERROR, R.FAIL);
            return jsonObject;
        };
    }
}
