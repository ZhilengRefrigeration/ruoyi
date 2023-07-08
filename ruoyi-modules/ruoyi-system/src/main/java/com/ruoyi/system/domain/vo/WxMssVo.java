package com.ruoyi.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/*
 * 小程序推送所需数据
 * */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ApiModel(value="微信小程序发送模版消息专用vo")
public class WxMssVo implements Serializable {
    @ApiModelProperty(value="用户openid",required=true)
    private String touser;
    @ApiModelProperty(value="消息模版id",required=true)
    private String template_id;
    @ApiModelProperty(value="默认跳到小程序首页地址路径",required=false)
    private String page = "pages/my/my";
    @ApiModelProperty(value="跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版",required=false)
    private String miniprogram_state="formal";
    @ApiModelProperty(value="进入小程序查看”的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN",required=false)
    private String lang="zh_CN";
    @ApiModelProperty(value="模板内容，格式形如 { \"key1\": { \"value\": any }, \"key2\": { \"value\": any } }",required=true)
    private Map<String, TemplateDataVo> data;
    @ApiModelProperty(value="小程序全局唯一后台接口调用凭据",required=false)
    private String accessToken;
}