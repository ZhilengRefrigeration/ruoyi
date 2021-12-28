package com.xjs.common.client.factory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xjs.common.client.TianXingFeignClient;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.copywriting.service.CopyWritingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @desc  天行数据平台接口降级处理
 * @create 2021-12-28
 */
@Log4j2
@Component
public class TianXingFeignFactory implements FallbackFactory<TianXingFeignClient> {
    @Autowired
    private CopyWritingService copyWritingService;

    @Override
    public TianXingFeignClient create(Throwable cause) {
        log.error("英语模块文案服务调用失败:{},执行降级处理", cause.getMessage());
        //没用拉姆达考虑后面该feign接口还会调用其他api接口
        return new TianXingFeignClient() {
            @Override
            public JSONObject copyWritingApi(RequestBody requestBody) {
                CopyWriting copyWriting = copyWritingService.getOneToRandom();
                String jsonString = JSON.toJSONString(copyWriting);
                return JSONObject.parseObject(jsonString);
            }
        };
    }










}
