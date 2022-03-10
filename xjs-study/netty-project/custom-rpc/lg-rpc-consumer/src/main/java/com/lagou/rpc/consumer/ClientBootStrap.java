package com.lagou.rpc.consumer;

import com.lagou.rpc.api.IUserService;
import com.lagou.rpc.consumer.proxy.RpcClientProxy;
import com.lagou.rpc.pojo.User;

/**
 * 测试类
 * @author xiejs
 * @since 2022-03-10
 */
public class ClientBootStrap {

    public static void main(String[] args) {
        IUserService userService = (IUserService) RpcClientProxy.createProxy(IUserService.class);

        User user = userService.getById(1);

        System.out.println(user);

    }

}
