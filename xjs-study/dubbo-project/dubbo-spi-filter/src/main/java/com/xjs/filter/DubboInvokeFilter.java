package com.xjs.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * dubbo过滤器
 *
 * @author xiejs
 * @since 2022-05-23
 */
@Activate(group = {CommonConstants.CONSUMER})
public class DubboInvokeFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long startTime = 0L;
        long endTime = 0L;
        try {
            startTime = System.currentTimeMillis();

            Result invoke = invoker.invoke(invocation);

            endTime = System.currentTimeMillis();

            return invoke;
        } finally {
            String serviceName = invocation.getServiceName();
            System.out.println("serviceName---invoke time : " + (endTime - startTime) + "ms");
        }
    }
}
