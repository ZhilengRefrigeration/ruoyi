package com.ruoyi.system.api.domain;

import lombok.Getter;
import lombok.Setter;

/**
 *
 **/
@Getter
public enum LoginMethodEnum {
	/**
	 *
	 **/
	ACCOUNT("account", "账号密码登录"),
	/**
	 *
	 **/
	WX_SCAN("wxScan", "微信扫码登录");
	@Getter
	@Setter
	private String desc;
	@Getter
	@Setter
	private String code;

	LoginMethodEnum(String code,String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getName(String code) {
		for (LoginMethodEnum signEnum : LoginMethodEnum.values()) {
			if (signEnum.code.equals(code)) {
				return signEnum.desc;
			}
		}
		return null;
	}
}
