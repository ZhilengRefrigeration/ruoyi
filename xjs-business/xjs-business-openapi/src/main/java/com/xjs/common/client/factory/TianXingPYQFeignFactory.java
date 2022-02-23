package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.tianxing.TianXingPYQFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 天行数据平台朋友圈文案接口降级处理
 * @author xiejs
 * @since  2021-12-28
 */
@Log4j2
@Component
public class TianXingPYQFeignFactory implements FallbackFactory<TianXingPYQFeignClient> {
    @Override
    public TianXingPYQFeignClient create(Throwable cause) {
        log.error("api模块网易云热评服务调用失败:{},执行降级处理", cause.getMessage());
        return requestBody -> {
                    JSONObject jsonObject = new JSONObject();
                    //构建一个异常json给下层接口处理
                    jsonObject.put(DEMOTE_ERROR, R.FAIL);
                    return jsonObject;
                };
    }










}
