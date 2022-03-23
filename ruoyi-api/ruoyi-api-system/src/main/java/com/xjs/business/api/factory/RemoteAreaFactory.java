package com.xjs.business.api.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.RemoteAreaFeign;
import com.xjs.business.api.domain.Area;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * RemoteAreaFeign降级
 *
 * @author xiejs
 * @since 2022-03-23
 */
@Component
@Log4j2
public class RemoteAreaFactory implements FallbackFactory<RemoteAreaFeign> {
    @Override
    public RemoteAreaFeign create(Throwable cause) {
        return new RemoteAreaFeign() {
            @Override
            public R<List<Area>> getProvinceAreaForRPC() {
                log.error("API模块获取区域信息降级" + cause.getMessage());
                return R.fail("API模块获取区域信息降级");
            }

            @Override
            public R<List<Area>> getAreaByParentIdForRPC(Long pid) {
                log.error("API模块根据id获取区域信息降级" + cause.getMessage());
                return R.fail("API模块根据id获取区域信息降级");
            }
        };
    }
}
