package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.baidu.BaiduFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @desc  百度翻译平台服务降级处理类
 * @create 2021-12-28
 */
@Log4j2
@Component
public class BaiduFeignFactory implements FallbackFactory<BaiduFeignClient> {
    @Override
    public BaiduFeignClient create(Throwable cause) {
        log.error("api模块百度翻译服务调用失败:{},执行降级处理", cause.getMessage());
        return  qo -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", R.FAIL);
            return jsonObject;
        };
    }
}
