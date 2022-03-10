package com.lagou.rpc.consumer.proxy;

import com.alibaba.fastjson.JSON;
import com.lagou.rpc.common.RpcRequest;
import com.lagou.rpc.common.RpcResponse;
import com.lagou.rpc.consumer.client.RpcClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * 客户端代理类-创建代理对象
 * <ol>
 *     <li>封装request请求对象</li>
 *     <li>创建RpcClient对象</li>
 *     <li>发送消息</li>
 *     <li>返回结果</li>
 * </ol>
 *
 * @author xiejs
 * @since 2022-03-10
 */
public class RpcClientProxy {

    public static Object createProxy(Class serviceClass) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{serviceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //1、封装request对象
                        RpcRequest rpcRequest = new RpcRequest();
                        rpcRequest.setRequestId(UUID.randomUUID().toString());
                        rpcRequest.setClassName(method.getDeclaringClass().getName());
                        rpcRequest.setParameterTypes(method.getParameterTypes());
                        rpcRequest.setMethodName(method.getName());
                        rpcRequest.setParameters(args);

                        //2、封装RpcClient对象
                        RpcClient rpcClient = new RpcClient("127.0.0.1", 8887);

                        try {
                            //3、发送消息
                            Object responseMsg = rpcClient.send(JSON.toJSONString(rpcRequest));

                            RpcResponse rpcResponse = JSON.parseObject(responseMsg.toString(), RpcResponse.class);
                            if (rpcResponse.getError() != null) {
                                throw new RuntimeException(rpcResponse.getError());
                            }

                            //4、返回结构
                            Object result = rpcResponse.getResult();

                            return JSON.parseObject(result.toString(), method.getReturnType());
                        } catch (Exception e) {
                            throw e;
                        } finally {
                            rpcClient.close();
                        }
                    }
                }
        );
    }


}
