package com.ruoyi.system.service;

import com.ruoyi.cache.annotation.OrgCacheKey;
import com.ruoyi.cache.annotation.OrgCacheTypeNum;
import com.ruoyi.cache.annotation.OrgCacheValue;
import com.ruoyi.cache.domain.CacheSysUser;

public class CacheCallBackPojo {
	@OrgCacheKey
	private Long userId;
	@OrgCacheValue(value = CacheSysUser.ANNOTAION_DEPT_ID)
	private Long deptId;
	@OrgCacheValue(value = CacheSysUser.ANNOTAION_USER_NAME)
	private String userName;
	@OrgCacheValue(value = CacheSysUser.ANNOTAION_PHONENUMBER)
	private String phoneNumber;
	private String detpName;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDetpName() {
		return detpName;
	}
	public void setDetpName(String detpName) {
		this.detpName = detpName;
	}
	@Override
	public String toString() {
		return "CacheCallBackPojo [userId=" + userId + ", deptId=" + deptId + ", userName=" + userName
				+ ", phoneNumber=" + phoneNumber + ", detpName=" + detpName + "]";
	}
	
	

}
