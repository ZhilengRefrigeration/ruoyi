package com.ruoyi.common.datascope.mybatis;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.common.core.web.domain.ExtBaseEntity;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Date;

/**
 * 共通字段自动填充插件
 * <p>
 * 注：该插件仅针对自己写的mapper.xml里的insert和update语句生效，对Dynamic SQL无效
 * </p>
 *
 * @author Alan Scipio
 * created on 2024/2/6
 */
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}
        )
})
public class AutoFillPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object parameter = invocation.getArgs()[1];
        if (parameter instanceof BaseEntity entity) {
            MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
            String commandType = statement.getSqlCommandType().name();

            Date now = DateUtils.getNowDate();
            String userId = SecurityUtils.getUserIdStr();

            if ("INSERT".equals(commandType)) {
                // insert时的自动填充
                entity.setCommonForInsert(userId, now);
            } else if ("UPDATE".equals(commandType)) {
                // update时的自动填充
                entity.setCommonForUpdate(userId, now);
            }

            if (entity instanceof ExtBaseEntity extEntity) {
                // ExtBaseEntity的自动填充
                if (extEntity.getUpdateCount() == null) {
                    extEntity.setUpdateCount(0);
                } else {
                    extEntity.setUpdateCount(extEntity.getUpdateCount() + 1);
                }
            }
        }

        return invocation.proceed();
    }

}
