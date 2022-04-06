package com.xjs.business.log.factory;

import com.ruoyi.common.core.domain.R;
import com.xjs.business.log.RemoteLogFeign;
import com.xjs.business.log.domain.ApiLog;
import com.xjs.business.log.domain.TaskLog;
import com.xjs.business.log.domain.WebmagicLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 日志远程调用rpc服务降级处理
 * @author xiejs
 * @since 2022-01-13
 */
@Component
public class RemoteLogFactory implements FallbackFactory<RemoteLogFeign> {
    private static final Logger log = LoggerFactory.getLogger(RemoteLogFactory.class);

    @Override
    public RemoteLogFeign create(Throwable cause) {
        return new RemoteLogFeign() {
            @Override
            public R<Object> saveApiLog(ApiLog apiLog) {
                log.error("日志模块api日志服务添加调用失败");
                return R.fail("日志模块api日志服务添加调用失败" + cause.getMessage());
            }

            @Override
            public R<Map<String, List>> statisticsByDate(String startDate, String endDate) {
                log.error("日志模块api日志服务根据时间查询调用失败");
                return R.fail("日志模块api日志服务根据时间查询调用失败" + cause.getMessage());
            }

            @Override
            public R<Object> saveReptileLog(WebmagicLog webmagicLog) {
                log.error("日志模块爬虫日志服务添加调用失败");
                return R.fail("日志模块爬虫日志服务添加调用失败" + cause.getMessage());
            }

            @Override
            public R<Object> saveTaskLog(TaskLog taskLog) {
                log.error("日志模块任务日志服务添加调用失败");
                return R.fail("日志模块任务日志服务添加调用失败" + cause.getMessage());
            }
        };
    }
}
