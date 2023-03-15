package com.ruoyi.util;

import java.util.HashMap;
import java.util.Map;

import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import com.ruoyi.common.redis.service.RedisService;


/**
 * 应用服务器监听从redis上发过来的消息,例如:重读服务的参数,以及一些命令.
 * @author 林桦
 *
 */
@Component
public class RedisNotifyService implements  MessageListener,ISpringLoadComplete   {
	private static Map<String,IRedisNotifyHandle> registerMap=new HashMap<String,IRedisNotifyHandle>(); 
	private static Logger LOGGER=LoggerFactory.getLogger(RedisNotifyService.class);
	public static final String CHANNEL="ruoyiMessageNotify";
	
	private RedisService redisService;
	public RedisNotifyService(RedisService redisService) {
		this.redisService=redisService;
		SpringLoadComplete.addCallBack(this);
	}

	/**
	 * 注册具体方法
	 * @param redisHandle
	 * @return
	 */
	public static boolean register(IRedisNotifyHandle redisHandle){
		if(registerMap.containsKey(redisHandle.getRegisterId())){
			LOGGER.info("已经存在:"+redisHandle.getRegisterId()+",无法注册成功!");
			return false;
		}else{
			LOGGER.info("注册ID:"+redisHandle.getRegisterId()+",注册成功,处理类:"+redisHandle.getClass());
			registerMap.put(redisHandle.getRegisterId(), redisHandle);
			return true;
		}
		
	}
	/**
	 * 取消注册.
	 * @param redisHandle
	 * @return
	 */
	public static boolean unRegister(IRedisNotifyHandle redisHandle){
		return unRegister(redisHandle.getRegisterId());
	}
	/**
	 * 取消注册
	 * @param registerId
	 * @return
	 */
	public static boolean unRegister(String registerId){
		if(registerId==null)
			return false;
		if(registerMap.containsKey(registerId)){
			LOGGER.info("注册ID:"+registerId+",取消注册成功!");
			registerMap.remove(registerId);
			return true;
		} else{
			return false;
		}
	}
	/**
	 * 判断是否有注册
	 * @param registerId
	 * @return
	 */
	public static boolean containRedisHandle(String registerId){
		if(registerId==null)
			return false;
		return registerMap.containsKey(registerId);
	}
	/**
	 * 判断是否有注册.
	 * @param redisHandle
	 * @return
	 */
	public static boolean containRedisHandle(IRedisNotifyHandle redisHandle){
		return containRedisHandle(redisHandle.getRegisterId());
	}
	/**
	 * 发送JsonRpc
	 * @param method 方法名
	 * @param params params的参数
	 */
	public void publish(String method,NutMap params) {
		NutMap jsonRpc=NutMap.NEW().addv("method", method).addv("params", params);
		redisService.redisTemplate.convertAndSend(CHANNEL,Json.toJson(jsonRpc) );
	}
	

	@Override
	public void onMessage(Message message, byte[] pattern) {
		/**
		 * Message的消息类型:{ "method": "sayHello", "params": ["Hello JSON-RPC"], "id": 1} 
		 */
		try {
			NutMap jsonMessage=Json.fromJson(NutMap.class,new String(message.getBody(),"UTF-8"));
			String registerId=jsonMessage.getString("method",null);
			if(containRedisHandle(registerId)){
				IRedisNotifyHandle redisHandle=registerMap.get(registerId);
				if("casLogout".equals(registerId)) //签出的消息比多，更改为debug级别.
					LOGGER.debug("recive httpServerRedis message:"+message+",submit to handle class:"+redisHandle.getClass());
				else
					LOGGER.info("recive httpServerRedis message:"+message+",submit to handle class:"+redisHandle.getClass());
				NutMap params=Json.fromJson(NutMap.class,jsonMessage.getString("params","{}"));
				redisHandle.handle(registerId,params);
			} else{
				if("casLogout".equals(registerId))
					LOGGER.debug("recive httpServerRedis message:"+message+",but not find any handle class");
				else
					LOGGER.info("recive httpServerRedis message:"+message+",but not find any handle class");
			}
		} catch(Exception e) {
			LOGGER.error("recive httpServerRedis handle error:"+message,e);
		}
		
	}

	@Override
	public void callback(ApplicationContext applicationContext) {
		RedisMessageListenerContainer redisMessageListenerContainer=applicationContext.getBean(RedisMessageListenerContainer.class);
		redisMessageListenerContainer.addMessageListener(this, new PatternTopic(CHANNEL));
		LOGGER.info("启动监听redis，channel:"+CHANNEL);
		
	}

}
