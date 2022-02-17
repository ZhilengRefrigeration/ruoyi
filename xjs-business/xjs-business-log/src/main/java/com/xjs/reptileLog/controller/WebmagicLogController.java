package com.xjs.reptileLog.controller;

import com.ruoyi.common.core.domain.R;
import com.xjs.reptileLog.domain.WebmagicLog;
import com.xjs.reptileLog.service.WebmagicLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 爬虫日志控制器
 * @author xiejs
 * @since 2022-02-17
 */
@RestController
@RequestMapping("reptileLog")
@Api(tags = "业务模块-爬虫日志")
public class WebmagicLogController {

    @Autowired
    private WebmagicLogService webmagicLogService;



    //-----------------------内部调用rpc------------------------

    @PostMapping("saveForPRC")
    @ApiOperation("供AOP切面RPC远程调用")
    public R<Object> saveReptileLog(@RequestBody WebmagicLog webmagicLog) {
        boolean save = webmagicLogService.save(webmagicLog);
        return save?R.ok():R.fail();
    }

}
