package com.xjs.weixin.controller;

import com.ruoyi.common.core.domain.R;
import com.xjs.weixin.task.OfficialAccountsTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信公众号controller
 * @author xiejs
 * @since 2022-06-13
 */
@RestController
@RequestMapping("weixin_official_accounts")
@Api(tags = "爬虫模块-微信公众号")
public class OfficialAccountsController {

    @Autowired
    private OfficialAccountsTask officialAccountsTask;


    //----------------------远程rpc调用---------------------------
    @GetMapping("taskForPRC")
    @ApiOperation("供定时任务服务RPC远程调用")
    public R WeiXinOfficialAccountsTaskForPRC() {
        officialAccountsTask.execute();
        return R.ok();
    }
}
