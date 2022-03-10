package com.lagou.rpc.provider.handler;

import com.alibaba.fastjson.JSON;
import com.lagou.rpc.common.RpcRequest;
import com.lagou.rpc.common.RpcResponse;
import com.lagou.rpc.provider.annotation.RpcService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.BeansException;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 服务端业务处理类
 * </p>
 *
 * <ul>
 *     <li>将标有@RpcService注解的bean缓存</li>
 *     <li>接收客户端请求</li>
 *     <li>根据传递过来的beanName从缓存中查找到对应的bean</li>
 *     <li>解析请求中的方法名称，参数类型 参数信息</li>
 *     <li>反射调用bean的方法</li>
 *     <li>给客户端进行响应</li>
 * </ul>
 *
 * @author xiejs
 * @since 2022-03-09
 */
@Component
@ChannelHandler.Sharable
public class RpcServerHandler extends SimpleChannelInboundHandler<String> implements ApplicationContextAware {

    private static final Map SERVICE_INSTANCE_MAP = new ConcurrentHashMap();


    /**
     * 通道读取就绪事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //1、接收客户端请求--将msg转化RpcRequest对象
        RpcRequest request = JSON.parseObject(msg, RpcRequest.class);

        RpcResponse response = new RpcResponse();
        response.setRequestId(request.getRequestId());

        try {
            response.setResult(handler(request));
        } catch (Exception e) {
            response.setError(e.getMessage());
            e.printStackTrace();
        }

        //给客户端进行响应
        ctx.writeAndFlush(JSON.toJSONString(response));
    }


    /**
     * 业务处理逻辑
     *
     * @return obj
     */
    private Object handler(RpcRequest request) throws InvocationTargetException {
        //根据传递过来的beanName从缓存中查找到对应的bean
        Object serviceBean = SERVICE_INSTANCE_MAP.get(request.getClassName());

        if (serviceBean == null) {
            throw new RuntimeException("根据beanName找不到服务，beanName：" + request.getClassName());
        }

        //解析请求中的方法名称，参数类型 参数信息
        Class<?> serviceBeanClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        //反射调用bean的方法
        FastClass fastClass = FastClass.create(serviceBeanClass);
        FastMethod method = fastClass.getMethod(methodName, parameterTypes);

        return method.invoke(serviceBean, parameters);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        Map<String, Object> serviceMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (serviceMap != null && serviceMap.size() > 0) {
            Set<Map.Entry<String, Object>> entries = serviceMap.entrySet();
            for (Map.Entry<String, Object> item : entries) {
                Object serviceBean = item.getValue();

                if (serviceBean.getClass().getInterfaces().length == 0) {
                    throw new RuntimeException("服务必须实现接口");
                }

                //默认取第一个接口作为缓存bean的名称
                String name = serviceBean.getClass().getInterfaces()[0].getName();
                SERVICE_INSTANCE_MAP.put(name, serviceBean);
            }
        }


    }
}
