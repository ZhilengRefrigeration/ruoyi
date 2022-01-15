package com.xjs.servicemonitor.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.servicemonitor.domain.ServiceMonitorInfo;
import com.xjs.servicemonitor.service.JvmMonitorService;
import com.xjs.servicemonitor.service.SystemOSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiejs
 * @desc  业务监控控制器
 * @create 2022-01-02
 */
@RestController
@RequestMapping("servicemonitor")
@Api(tags = "业务模块-服务监控")
public class ServiceMonitorController {
    @Autowired
    private JvmMonitorService jvmMonitorService;

    @Autowired
    private SystemOSService systemOSService;

    @GetMapping
    @RequiresPermissions("monitor:service:list")
    @ApiOperation("获取服务监控信息")
    public AjaxResult getServiceMonitor(HttpServletRequest request) {
        ServiceMonitorInfo serviceMonitorInfo = new ServiceMonitorInfo();
        //获取客户端信息
        String characterEncoding = request.getCharacterEncoding();
        int serverPort = request.getServerPort();
        String userAgent = request.getHeader("user-agent");
        serviceMonitorInfo.setCharacterEncoding(characterEncoding);
        serviceMonitorInfo.setServerPort(serverPort);
        serviceMonitorInfo.setUserAgent(userAgent);
        //获取服务器信息
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
