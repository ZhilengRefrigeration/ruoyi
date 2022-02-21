package com.xjs.common.controller;

import com.ruoyi.common.core.domain.R;
import com.xjs.common.task.CheckApiStatusTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API模块通用controller
 * @author xiejs
 * @since 2022-02-21
 */
@RestController
@RequestMapping("common")
@Api(tags = "业务模块-通用")
@Log4j2
public class ApiCommonController {

    @Autowired
    private CheckApiStatusTask checkApiStatusTask;

    @GetMapping("checkApiStatus")
    @ApiOperation("检查api状态")
    public R CheckApiStatusForRPC() {
        checkApiStatusTask.checkApiStatus();
        return R.ok();
    }

}
