package com.xjs.servicemonitor.service.impl;

import cn.hutool.system.*;
import com.xjs.servicemonitor.service.SystemOSService;
import org.springframework.stereotype.Service;

/**
 * @author xiejs
 * @desc
 * @create 2022-01-02
 */
@Service
public class SystemOSServiceImpl implements SystemOSService {
    @Override
    public OsInfo getOsInfo() {
        return SystemUtil.getOsInfo();
    }

    @Override
    public UserInfo getUserInfo() {
        return SystemUtil.getUserInfo();
    }

    @Override
    public HostInfo getHostInfo() {
        return SystemUtil.getHostInfo();
    }

    @Override
    public RuntimeInfo getRuntimeInfo() {
        return SystemUtil.getRuntimeInfo();
    }
}
