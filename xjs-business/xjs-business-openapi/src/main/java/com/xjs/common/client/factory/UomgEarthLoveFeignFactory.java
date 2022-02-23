package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.uomg.UomgEarthLoveFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 弃用
 * @author xiejs
 * @since 2022-02-14
 */
@Component
@Log4j2
public class UomgEarthLoveFeignFactory implements FallbackFactory<UomgEarthLoveFeignClient> {
    @Override
    public UomgEarthLoveFeignClient create(Throwable cause) {
        log.error("api模块Uomg-土味情话服务调用失败:{},执行降级处理", cause.getMessage());
        return  (req) -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DEMOTE_ERROR, R.FAIL);
            return jsonObject;
        };
    }
}
