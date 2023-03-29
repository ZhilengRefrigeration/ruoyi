package com.ruoyi.system.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.system.domain.BulletinInfo;
import com.ruoyi.system.service.IBulletinInfoService;
@SpringBootTest
class BulletinInfoServiceImplTest {

	@Autowired
	private IBulletinInfoService bulletinInfoService;
	@BeforeEach
	void setUser() {
		SecurityContextHolder.set(SecurityConstants.DETAILS_USER_ID, 1);
		
	}
	@Test
	void testSelectBulletinInfoList() {
		BulletinInfo bulletinInfo=new BulletinInfo();
		bulletinInfo.setSts("C");
		List<BulletinInfo> list=bulletinInfoService.selectBulletinInfoList(bulletinInfo);
		assertTrue(list.size()>0);
		System.out.println("aaaaaaaaaaaaaaaa:"+list.get(0).getCreateUserId()+"="+list.get(0).getCreateBy());
	}

}
