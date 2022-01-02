package com.xjs.servicemonitor.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.xjs.servicemonitor.domain.ServiceMonitorInfo;
import com.xjs.servicemonitor.service.JvmMonitorService;
import com.xjs.servicemonitor.service.SystemOSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiejs
 * @desc  业务监控控制器
 * @create 2022-01-02
 */
@RestController
@RequestMapping("servicemonitor")
public class ServiceMonitorController {
    @Autowired
    private JvmMonitorService jvmMonitorService;

    @Autowired
    private SystemOSService systemOSService;

    @GetMapping
    public AjaxResult getServiceMonitor() {
        ServiceMonitorInfo serviceMonitorInfo = new ServiceMonitorInfo();
        serviceMonitorInfo.setFreeMemory(systemOSService.getRuntimeInfo().getFreeMemory());
        serviceMonitorInfo.setCurrentDir(systemOSService.getUserInfo().getCurrentDir());
        serviceMonitorInfo.setHomeDir(systemOSService.getUserInfo().getHomeDir());
        serviceMonitorInfo.setHostAddress(systemOSService.getHostInfo().getAddress());
        serviceMonitorInfo.setHostName(systemOSService.getHostInfo().getName());
        serviceMonitorInfo.setJreName(jvmMonitorService.getJavaRuntimeInfo().getName());
        serviceMonitorInfo.setJreHoneDir(jvmMonitorService.getJavaRuntimeInfo().getHomeDir());
        serviceMonitorInfo.setMaxMemory(systemOSService.getRuntimeInfo().getMaxMemory());
        serviceMonitorInfo.setJreVersion(jvmMonitorService.getJavaRuntimeInfo().getVersion());
        serviceMonitorInfo.setJvmVersion(jvmMonitorService.getJvmInfo().getVersion());
        serviceMonitorInfo.setUserName(systemOSService.getUserInfo().getName());
        serviceMonitorInfo.setOsArch(systemOSService.getOsInfo().getArch());
        serviceMonitorInfo.setOsVersion(systemOSService.getOsInfo().getVersion());
        serviceMonitorInfo.setTotalMemory(systemOSService.getRuntimeInfo().getTotalMemory());
        serviceMonitorInfo.setUsableMemory(systemOSService.getRuntimeInfo().getUsableMemory());
        serviceMonitorInfo.setUserLanguage(systemOSService.getUserInfo().getLanguage());
        serviceMonitorInfo.setUserCountry(systemOSService.getUserInfo().getCountry());
        serviceMonitorInfo.setOsName(systemOSService.getOsInfo().getName());
        return AjaxResult.success(serviceMonitorInfo);
    }


}
