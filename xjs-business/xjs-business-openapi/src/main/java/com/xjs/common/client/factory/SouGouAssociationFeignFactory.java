package com.xjs.common.client.factory;

import com.xjs.common.client.api.sougou.SouGouAssociationFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 搜狗语义联想api 降级
 * @author xiejs
 * @since 2022-02-25
 */
@Component
@Log4j2
public class SouGouAssociationFeignFactory implements FallbackFactory<SouGouAssociationFeignClient> {

    @Override
    public SouGouAssociationFeignClient create(Throwable cause) {
        log.error("搜狗联想服务调用失败:{},执行降级处理", cause.getMessage());
        return (wd ->"");
    }

}
