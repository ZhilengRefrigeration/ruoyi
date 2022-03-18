package com.xjs.weixin.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.weixin.service.WeiXinLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequiresPermissions("webmagic:weixinlink:list")
    @GetMapping("/getPicture")
    @ApiOperation("获取文章图片")
    public AjaxResult getPicture(@RequestParam("link") String link) {
        Boolean flag = weiXInLinkService.getPicture(link);
        return toAjax(flag);
    }

    @RequiresPermissions("webmagic:weixinlink:update")
    @PutMapping("/updateSettings")
    @ApiOperation("修改参数配置")
    public AjaxResult updateSettings(@RequestParam("path") String path) {
        boolean flag = weiXInLinkService.updateSettings(path);

        return toAjax(flag);
    }


    @RequiresPermissions("webmagic:weixinlink:list")
    @GetMapping("/getSettings")
    @ApiOperation("获取参数配置")
    public AjaxResult getSettings() {
        String settings = weiXInLinkService.getSettings();

        return AjaxResult.success("操作成功", settings);
    }

    @RequiresPermissions("webmagic:weixinlink:reset")
    @PutMapping("/resetSettings")
    @ApiOperation("重置参数配置")
    public AjaxResult resetSettings() {
        boolean flag = weiXInLinkService.restSettings();
        return toAjax(flag);
    }


}
