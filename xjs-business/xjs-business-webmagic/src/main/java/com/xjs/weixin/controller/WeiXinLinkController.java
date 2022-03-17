package com.xjs.weixin.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.weixin.service.WeiXinLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信文章链接控制器
 *
 * @author xiejs
 * @since 2022-03-17
 */
@RestController
@RequestMapping("weixin_link")
@Api(tags = "爬虫模块-微信链接")
public class WeiXinLinkController extends BaseController {

    @Autowired
    private WeiXinLinkService weiXInLinkService;

    @RequiresPermissions("webmagic:weixinlink:get")
    @GetMapping("/getPicture")
    @ApiOperation("获取文章图片")
    public AjaxResult getPicture(@RequestParam("link") String link) {
        Boolean flag = weiXInLinkService.getPicture(link);
        return toAjax(flag);
    }

}
