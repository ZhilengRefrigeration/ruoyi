package com.ruoyi.system.domain.enums;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**微信小程序发送模版消息返回errcode
 * @author :wyb
 * @date : 2019/11/30
 */
public enum WxAppletsSendErrCodesEnum {
    ERR_CODES_40003("40003", "touser字段openid为空或者不正确"),
    ERR_CODES_40037("40037", "订阅模板id为空不正确"),
    ERR_CODES_43101 ("43101", "用户拒绝接受消息，如果用户之前曾经订阅过，则表示用户取消了订阅关系"),
    ERR_CODES_47003("47003", "模板参数不准确，可能为空或者不满足规则，errmsg会提示具体是哪个字段出错"),
    ERR_CODES_41030 ("41030", "page路径不正确，需要保证在现网版本小程序中存在，与app.json保持一致");

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String desc;

    WxAppletsSendErrCodesEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static String getDescByCode(String code){
        if(StringUtils.isEmpty(code)){
            return null;
        }
        for(WxAppletsSendErrCodesEnum codesEnum:WxAppletsSendErrCodesEnum.values()){
            if(code.equals(codesEnum.code)){
                return codesEnum.desc;
            }
        }
        return null;
    }

}