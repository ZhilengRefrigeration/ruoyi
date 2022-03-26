package com.ruoyi.common.log.p6spy;

import com.ruoyi.common.log.aspect.LogAspect;
import com.ruoyi.common.log.utils.JansiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 输出 SQL 日志
 *
 * @author hubin
 * @since 2019-02-20
 */
public class StdoutLogger extends com.p6spy.engine.spy.appender.StdoutLogger {
    @Override
    public void logText(String text) {
        JansiUtils.info(text);
    }
}
