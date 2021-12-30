package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.TianXingJDTCFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @desc  天行数据经典台词降级处理
 * @create 2021-12-29
 */
@Component
@Log4j2
public class TianXingJDTCFeignFactory implements FallbackFactory<TianXingJDTCFeignClient> {

    @Override
    public TianXingJDTCFeignClient create(Throwable cause) {
        log.error("api模块经典台词服务调用失败:{},执行降级处理", cause.getMessage());
        return requestBody -> {
            JSONObject jsonObject = new JSONObject();
            //构建一个异常json给下层接口处理
            jsonObject.put("error", 500);
            return jsonObject;
        };
    }
}