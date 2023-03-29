package com.ruoyi.cache.domain;

import com.ruoyi.system.api.domain.SysUser;
/**
 * 存储在redis上的用户缓存
 * @author condy
 *
 */
public class CacheSysUser extends BaseCache {
	/**
	 * @OrgCacheValue的 value类型
	 */
	public final static String ANNOTAION_USER_ID="userId";
	public final static String ANNOTAION_USER_NAME="userName";
	public final static String ANNOTAION_DEPT_ID="deptId";
	public final static String ANNOTAION_EMAIL="email";
	public final static String ANNOTAION_PHONENUMBER="phonenumber";

	
	
	private Long userId;
	private String userName;
	private Long deptId;
	private String nickName;
	private String email;
	private String phonenumber;
	
	public CacheSysUser(SysUser user) {
		this.userId=user.getUserId();
		this.userName=user.getUserName();
		this.deptId=user.getDeptId();
		this.nickName=user.getNickName();
		this.email=user.getEmail();
		this.phonenumber=user.getEmail();
	}
	public CacheSysUser()
	{
		
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
}
