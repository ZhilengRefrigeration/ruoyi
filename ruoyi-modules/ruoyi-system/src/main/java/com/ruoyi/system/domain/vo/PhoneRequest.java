package com.ruoyi.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@ApiModel(value="用户登录获取手机号码请求信息")
@Setter
@Getter
public class PhoneRequest implements Serializable {
    @ApiModelProperty(value="包括敏感数据在内的完整用户信息的加密数据",required=false)
    private String encryptedData;
    @ApiModelProperty(value = "动态令牌。可通过动态令牌换取用户手机号",required=false)
    private String code;

    private String cloudID;
    @ApiModelProperty(value="加密算法的初始向量",required=false)
    private String iv;
    @ApiModelProperty(value="sessionKey",required=false)
    private String sessionKey;
    @ApiModelProperty(value="错误信息",required=false)
    private String errMsg;

    @ApiModelProperty(value="userid",required=false)
    private String userId;
}
