package com.xjs.servicemonitor.service.impl;

import cn.hutool.system.JavaRuntimeInfo;
import cn.hutool.system.JvmInfo;
import cn.hutool.system.JvmSpecInfo;
import cn.hutool.system.SystemUtil;
import com.xjs.servicemonitor.service.JvmMonitorService;
import org.springframework.stereotype.Service;

/**
 * @author xiejs
 * @desc
 * @create 2022-01-02
 */
@Service
public class JvmMonitorServiceImpl implements JvmMonitorService {


    @Override
    public JvmSpecInfo getJvmSpecInfo() {
        return SystemUtil.getJvmSpecInfo();
    }

    @Override
    public JvmInfo getJvmInfo() {
        return SystemUtil.getJvmInfo();
    }

    @Override
    public JavaRuntimeInfo getJavaRuntimeInfo() {
        return SystemUtil.getJavaRuntimeInfo();
    }
}
