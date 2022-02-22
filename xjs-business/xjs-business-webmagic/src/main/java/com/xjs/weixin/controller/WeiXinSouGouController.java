package com.xjs.weixin.controller;

import com.ruoyi.common.core.domain.R;
import com.xjs.weixin.task.WeiXinSouGouTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信搜狗controller
 * @author xiejs
 * @since 2022-02-22
 */
@RestController
@RequestMapping("weixin_sougou")
@Api(tags = "爬虫模块-微信搜狗")
public class WeiXinSouGouController {

    @Autowired
    private WeiXinSouGouTask weiXinSouGouTask;



    //----------------------远程rpc调用---------------------------
    @GetMapping("taskForPRC")
    @ApiOperation("供定时任务服务RPC远程调用")
    public R WeiXinSouGouTaskForPRC() {
        Long count = weiXinSouGouTask.reptileWeiXinSouGou();
        return R.ok(count);
    }

}
