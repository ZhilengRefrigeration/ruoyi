package com.ruoyi.system.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.nutz.lang.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ruoyi.cache.service.CacheCallBack;
import com.ruoyi.cache.service.IOrgCacheService;
@SpringBootTest
class CacheCallBackTest {

	@Autowired
	private IOrgCacheService orgCacheService;
	@Test
	void testInvoke() {
		CacheCallBackPojo pojo=new CacheCallBackPojo();
		pojo.setUserId(1L);
		Lang.each(Lang.list(pojo), new CacheCallBack<>());
		assertNotNull(pojo.getUserName());
		System.out.println(pojo.toString());
	}
	
	@Test
	void testInvoke2() {
		CacheCallBackPojo2 pojo=new CacheCallBackPojo2();
		pojo.setUserId(1L);
		pojo.setDeptId(103L);
		Lang.each(Lang.list(pojo), new CacheCallBack<>());
		assertNotNull(pojo.getUserName());
		System.out.println(pojo.toString());
	}

}
