package com.xjs.y2048community.controller;

import com.ruoyi.common.core.domain.R;
import com.xjs.y2048community.task.Y2048communityTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2048社区控制器
 * @author xiejs
 * @since 2022-06-17
 */
@RestController
@RequestMapping("y2048community")
@Api(tags = "爬虫模块-2048社区")
public class Y2048communityController {

    @Autowired
    private Y2048communityTask y2048communityTask;



    //------------------------------内部rpc调用---------------------------------------
    @GetMapping("taskForPRC")
    @ApiOperation("供定时任务服务RPC远程调用")
    public R y2048communityTaskForPRC() {
        Long reptile = y2048communityTask.reptile();
        return R.ok();
    }
}
