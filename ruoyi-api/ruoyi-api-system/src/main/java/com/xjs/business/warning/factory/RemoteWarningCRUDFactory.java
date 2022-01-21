package com.xjs.business.warning.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.warning.RemoteWarningCRUDFeign;
import com.xjs.business.warning.domain.ApiRecord;
import com.xjs.business.warning.domain.ApiWarning;
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
            public R<ApiRecord> saveApiRecordForRPC(ApiRecord apiRecord) {
                log.error("调用预警服务添加接口失败，执行降级处理----"+apiRecord.getApiName());
                return R.fail();
            }

            @Override
            public R<ApiRecord> updateApiRecordForRPC(ApiRecord apiRecord) {
                log.error("调用预警服务修改接口失败，执行降级处理----"+apiRecord.getApiName());
                return R.fail();
            }

            @Override
            public R<List<ApiRecord>> selectApiRecordListForRPC(ApiRecord apiRecord) {
                log.error("调用预警服务查询接口失败，执行降级处理----"+apiRecord.getApiName());
                return R.fail();
            }

            @Override
            public R<ApiWarning> saveApiWarningForRPC(ApiWarning apiWarning) {
                log.error("调用预警服务api预警查询接口失败，执行降级处理----"+apiWarning.getApiName());
                return R.fail();
            }

            @Override
            public R<List<String>> getApiName() {
                log.error("调用预警服务api预警查询所有api名称接口失败，执行降级处理----");
                return R.fail();
            }
        };
    }
}
