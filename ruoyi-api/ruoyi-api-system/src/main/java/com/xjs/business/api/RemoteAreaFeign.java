package com.xjs.business.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.domain.Area;
import com.xjs.business.api.factory.RemoteAreaFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 调用openapi服务AreaController feign
 *
 * @author xiejs
 * @since 2022-03-23
 */
@FeignClient(contextId = "remoteAreaFeign",
        value = ServiceNameConstants.BUSINESS_OPENAPI_SERVICE,
        fallbackFactory = RemoteAreaFactory.class)
public interface RemoteAreaFeign {

    @GetMapping("/area/getProvinceAreaForRPC")
    R<List<Area>> getProvinceAreaForRPC();


    @GetMapping("/area/getAreaByParentIdForRPC/{pid}")
    R<List<Area>> getAreaByParentIdForRPC(@PathVariable("pid") Long pid);

}
