package com.xjs.business.english.factory;

import com.xjs.business.english.RemoteEnglishFeign;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 *英语服务降级处理
 * @author xiejs
 * @since 2022-06-15
 */
@Component
public class RemoteEnglishFactory implements FallbackFactory<RemoteEnglishFeign> {

    @Override
    public RemoteEnglishFeign create(Throwable cause) {
        return null;
    }
}
