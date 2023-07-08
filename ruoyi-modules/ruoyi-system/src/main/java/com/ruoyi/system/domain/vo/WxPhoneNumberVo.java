package com.ruoyi.system.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 吴一博
 * @date 2022年11月01日 17:17
 * @Description 微信小程序获取手机号码返回对象
 */
@Data
public class WxPhoneNumberVo implements Serializable {

    /**
     * errcode : 0
     * errmsg : ok
     * phone_info : {"phoneNumber":"xxxxxx","purePhoneNumber":"xxxxxx","countryCode":86,"watermark":{"timestamp":1637744274,"appid":"xxxx"}}
     */

    private int errcode;
    private String errmsg;
    private PhoneInfoBean phone_info;

    @Data
    public static class PhoneInfoBean implements Serializable {
        /**
         * phoneNumber : xxxxxx
         * purePhoneNumber : xxxxxx
         * countryCode : 86
         * watermark : {"timestamp":1637744274,"appid":"xxxx"}
         */

        private String phoneNumber;
        private String purePhoneNumber;
        private int countryCode;
        private WatermarkBean watermark;

        @Data
        public static class WatermarkBean implements Serializable {
            /**
             * timestamp : 1637744274
             * appid : xxxx
             */

            private int timestamp;
            private String appid;
        }
    }
}
