package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.tianxing.TianXingTranDictClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 天行api翻译字典降级处理
 * @author xiejs
 * @since  2021-12-30
 */
@Component
@Log4j2
public class TianXingTranDictFeignFactory implements FallbackFactory<TianXingTranDictClient> {
    @Override
    public TianXingTranDictClient create(Throwable cause) {
        log.error("api模块翻译字典服务调用失败:{},执行降级处理", cause.getMessage());
        return requestBody -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DEMOTE_ERROR, R.FAIL);
            return jsonObject;
        };
    }
}
