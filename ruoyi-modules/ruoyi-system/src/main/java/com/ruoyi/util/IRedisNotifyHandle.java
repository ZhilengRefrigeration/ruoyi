package com.ruoyi.util;

import org.nutz.lang.util.NutMap;

/**
 * 接收到HttpServerRedis消息的处理类
 * @author 林桦
 *
 */
public interface IRedisNotifyHandle {
	/**
	 * 处理json格式的消息
	 * @param jsonMessage
	 */
	public void handle(String method,NutMap params);
	/**
	 * 获取注册的ID,不同的ID之间不能重复.
	 * @return
	 */
	public String getRegisterId();

}
