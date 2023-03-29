package com.ruoyi.util;

import org.springframework.context.ApplicationContext;

public interface ISpringLoadComplete {
	/**
	 * 回调实现类
	 * @param applicationContext
	 */
	void callback(ApplicationContext applicationContext);

}