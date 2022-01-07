package com.xjs.business.warning;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.warning.domain.ApiRecord;
import com.xjs.business.warning.domain.ApiWarning;
import com.xjs.business.warning.factory.RemoteWarningCRUDFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author xiejs
 * @desc 远程rpc调用预警服务crud接口
 * @create 2021-12-31
 */
@FeignClient(contextId = "remoteWarningCRUDFeign",
        value = ServiceNameConstants.BUSINESS_WARNING_SERVICE,
        fallbackFactory = RemoteWarningCRUDFactory.class)
public interface RemoteWarningCRUDFeign {

    @PostMapping("apiwarning")
    public R<ApiRecord> saveApiRecordForRPC(@RequestBody ApiRecord apiRecord);


    @PutMapping("apiwarning")
    public R<ApiRecord> updateApiRecordForRPC(@RequestBody ApiRecord apiRecord);

    @GetMapping("apiwarning")
    R<List<ApiRecord>> selectApiRecordListForRPC(@SpringQueryMap ApiRecord apiRecord);

    @PostMapping("apiwarning/saveApiwarningForRPC")
    R<ApiWarning> saveApiWarningForRPC(@RequestBody ApiWarning apiWarning);

}
