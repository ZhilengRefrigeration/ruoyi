package com.ruoyi.common.log.p6spy;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

/**
 * P6spy SQL 打印策略
 *
 * @author hubin
 * @since 2019-02-20
 */
public class P6SpyLogger implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category,
                                String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql)
                ?
                    "\n Consume Time："
                            + elapsed +
                    " ms "
                            + now +
                    "\n Execute SQL："
                            +"\n\t\t"+ sql.replaceAll("[\\s]+", " ")
                    + "\n Execution Result ↓↓↓"
                :
                    "Not Sql Info \n";
    }
}
