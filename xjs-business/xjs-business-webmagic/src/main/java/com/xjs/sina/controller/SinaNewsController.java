package com.xjs.sina.controller;

import com.ruoyi.common.core.domain.R;
import com.xjs.sina.task.SinaTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新浪爬虫数据controller
 * @author xiejs
 * @since 2022-02-15
 */
@RestController
@RequestMapping("sina")
@Api(tags = "爬虫模块-新浪新闻")
public class SinaNewsController {
    @Autowired
    private SinaTask sinaTask;











    //----------------------远程rpc调用---------------------------
    @GetMapping("taskForPRC")
    @ApiOperation("供定时任务服务RPC远程调用")
    public R sinaTaskForPRC() {
        sinaTask.reptileSinaNews();
        return R.ok();
    }

}
