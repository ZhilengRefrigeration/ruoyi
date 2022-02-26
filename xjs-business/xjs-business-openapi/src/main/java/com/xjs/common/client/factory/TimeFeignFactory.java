package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.time.TimeFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 网络时间feign降级
 * @author xiejs
 * @since 2022-02-26
 */
@Component
@Log4j2
public class TimeFeignFactory implements FallbackFactory<TimeFeignClient> {

    @Override
    public TimeFeignClient create(Throwable cause) {
        log.error("时间api接口服务调用失败:{},执行降级处理", cause.getMessage());
        return ((a,b) -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DEMOTE_ERROR, R.FAIL);
            return jsonObject;
        });
    }
}
