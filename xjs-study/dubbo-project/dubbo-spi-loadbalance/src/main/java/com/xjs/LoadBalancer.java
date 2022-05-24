package com.xjs;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.List;

/**
 * dubbo自定义负载均衡器
 * @author xiejs
 * @since 2022-05-24
 */

public class LoadBalancer implements LoadBalance {
    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {

        //按照 IP + 端口排序

        Invoker<T> tInvoker = invokers.stream().sorted((i1, i2) -> {
            final int ipCompare = i1.getUrl().getIp().compareTo(i2.getUrl().getIp());
            if (ipCompare == 0) {
                return Integer.compare(i1.getUrl().getPort(), i2.getUrl().getPort());
            }
            return ipCompare;
        }).findFirst().get();

        return tInvoker;
    }
}
