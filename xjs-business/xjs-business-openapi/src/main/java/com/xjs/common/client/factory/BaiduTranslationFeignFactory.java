package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.baidu.BaiduTranslationFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 百度翻译平台服务降级处理类
 * @author xiejs
 * @since  2021-12-28
 */
@Log4j2
@Component
public class BaiduTranslationFeignFactory implements FallbackFactory<BaiduTranslationFeignClient> {
    @Override
    public BaiduTranslationFeignClient create(Throwable cause) {
        log.error("api模块百度翻译服务调用失败:{},执行降级处理", cause.getMessage());
        return  qo -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DEMOTE_ERROR, R.FAIL);
            return jsonObject;
        };
    }
}
