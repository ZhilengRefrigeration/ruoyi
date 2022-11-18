package com.ruoyi.system.service;

import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;

/**
 * @author 吴一博
 * @date 2022年11月17日 17:11
 * @Description
 */
public interface WxApplesCodeService {
    /**
     * @description 获取微信小程序AccessToken
     * @author 吴一博
     * @date 2022/11/17 17:13
     * @return java.lang.String
     */
    String getWxApplesAccessToken();

    WxAppletsCodeVo genWxApplesAqrCode(WxAppletsCodeVo wxAppletsCodeVo);
}
