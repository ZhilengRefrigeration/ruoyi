package com.ruoyi.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
/**
 * spring启动完成，调用回调类
 * @author condy
 *
 */
public class SpringLoadComplete implements ApplicationListener<ContextRefreshedEvent>{

	private static List<ISpringLoadComplete>  callbackList=new ArrayList<>();
	private Logger log=LoggerFactory.getLogger(SpringLoadComplete.class);
	/**
	 * 将回调方法加入到回调队列
	 * @param callback
	 */
	public static void addCallBack(ISpringLoadComplete callback) {
		if(!callbackList.contains(callback))
			callbackList.add(callback);
	}
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			try {
				for (ISpringLoadComplete callback : callbackList) {
					log.info("Spring启动完成,调用回调处理类:{}",callback);
					callback.callback(event.getApplicationContext());
				}
			} catch(Exception e) {
				log.error("初始化回调方法错误",e);
			}
		}
		
	}

}
