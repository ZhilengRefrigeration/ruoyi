package com.xjs.business.warning.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.warning.RemoteWarningCRUDFeign;
import com.xjs.business.warning.domain.ApiRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xiejs
 * @desc  调用预警服务降级处理
 * @create 2021-12-31
 */
@Component
public class RemoteWarningCRUDFactory implements FallbackFactory<RemoteWarningCRUDFeign> {

    private static final Logger log = LoggerFactory.getLogger(RemoteWarningCRUDFactory.class);

    @Override
    public RemoteWarningCRUDFeign create(Throwable cause) {
        return new RemoteWarningCRUDFeign() {
            @Override
            public R<ApiRecord> saveApiRecord(ApiRecord apiRecord) {
                log.error("调用预警服务添加接口失败，执行降级处理----"+apiRecord.getApiName());
                return R.fail();
            }

            @Override
            public R<ApiRecord> updateApiRecord(ApiRecord apiRecord) {
                log.error("调用预警服务修改接口失败，执行降级处理----"+apiRecord.getApiName());
                return R.fail();
            }

            @Override
            public R<List<ApiRecord>> selectApiRecordList(ApiRecord apiRecord) {
                log.error("调用预警服务查询接口失败，执行降级处理----"+apiRecord.getApiName());
                return R.fail();
            }
        };
    }
}
