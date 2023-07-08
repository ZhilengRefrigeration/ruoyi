package com.ruoyi.system.service;

import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;
import com.ruoyi.system.domain.vo.PhoneRequest;
import com.ruoyi.system.domain.vo.WxApplesRes;
import com.ruoyi.system.domain.vo.WxMssVo;

/**
 * @author 吴一博
 * @date 2023年07月07日 16:18
 * @Description
 */
public interface WxAppletsService {
    String getAccessToken();

    WxApplesRes pushOneUser(WxMssVo wxMssVo);

    String getPhoneNumber(PhoneRequest request);

    String updatePhoneNumber(PhoneRequest request);

    WxAppletsCodeVo getWxacodeunlimit(WxAppletsCodeVo wxAppletsCodeVo, String accessToken);
}
