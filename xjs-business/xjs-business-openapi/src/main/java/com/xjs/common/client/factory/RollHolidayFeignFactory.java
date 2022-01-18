package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.common.client.api.roll.RollHolidayFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * roll节假日api feign 降级
 * @author xiejs
 * @since 2022-01-18
 */
@Component
@Log4j2
public class RollHolidayFeignFactory implements FallbackFactory<RollHolidayFeignClient> {
    @Override
    public RollHolidayFeignClient create(Throwable cause) {
        log.error("api模块roll 节假日服务调用失败:{},执行降级处理", cause.getMessage());
        return requestBody -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DEMOTE_ERROR, R.FAIL);
            return jsonObject;
        };
    }
}
