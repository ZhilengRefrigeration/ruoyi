package com.xjs.zol.controller;

import com.ruoyi.common.core.domain.R;
import com.xjs.zol.task.ZolTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 爬虫中关村controller
 *
 * @author xiejs
 * @since 2022-04-18
 */
@RestController
@RequestMapping("zol")
@Api(tags = "爬虫模块-中关村")
public class ZolController {

    @Autowired
    private ZolTask zolTask;


    //------------------------------内部调用rpc-------------------------------------
    @GetMapping("taskForPRC")
    @ApiOperation("供定时任务服务RPC远程调用")
    public R<Long> ZolPhoneTaskForRPC() {
        Long aLong = zolTask.reptileZol();
        return R.ok(aLong);
    }
}
