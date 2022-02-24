package com.xjs.common.client.factory;

import com.xjs.common.client.api.baidu.BaiduAssociationFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @since 2022-02-24
 */
@Component
@Log4j2
public class BaiduAssociationFeignFactory implements FallbackFactory<BaiduAssociationFeignClient> {

    @Override
    public BaiduAssociationFeignClient create(Throwable cause) {
        log.error("百度联想服务调用失败:{},执行降级处理", cause.getMessage());
        return (wd ->"");
    }
}
