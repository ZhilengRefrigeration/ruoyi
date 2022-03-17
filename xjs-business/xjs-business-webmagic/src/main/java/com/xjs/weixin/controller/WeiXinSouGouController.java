package com.xjs.weixin.controller;

import com.ejlchina.searcher.SearchResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.PageDomain;
import com.ruoyi.common.core.web.page.TableSupport;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.validation.group.SelectGroup;
import com.xjs.weixin.pojo.WeiXinSouGou;
import com.xjs.weixin.service.WeiXinSouGouService;
import com.xjs.weixin.task.WeiXinSouGouTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 微信搜狗controller
 *
 * @author xiejs
 * @since 2022-02-22
 */
@RestController
@RequestMapping("weixin_sougou")
@Api(tags = "爬虫模块-微信搜狗")
public class WeiXinSouGouController extends BaseController {

    @Autowired
    private WeiXinSouGouTask weiXinSouGouTask;
    @Autowired
    private WeiXinSouGouService weiXinSouGouService;


    /**
     * 查询爬虫微信搜狗搜索列表
     */
    @RequiresPermissions("webmagic:weixinsougou:list")
    @GetMapping("/list")
    @ApiOperation("查询爬虫微信搜狗搜索列表")
    public AjaxResult list(@Validated({SelectGroup.class}) WeiXinSouGou weiXinSouGou) {
        //startPage();
        PageDomain pageDomain = TableSupport.buildPageRequest();
        SearchResult<WeiXinSouGou> list = weiXinSouGouService.selectWeiXinSouGouList(weiXinSouGou, pageDomain);
        return AjaxResult.success(list);
    }



    //----------------------远程rpc调用---------------------------
    @GetMapping("taskForPRC")
    @ApiOperation("供定时任务服务RPC远程调用")
    public R WeiXinSouGouTaskForPRC() {
        Long count = weiXinSouGouTask.reptileWeiXinSouGou();
        return R.ok(count);
    }


    //-------------------------代码生成--------------------------------

    /**
     * 导出爬虫微信搜狗搜索列表
     */
    @RequiresPermissions("webmagic:weixinsougou:export")
    @Log(title = "微信搜狗", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出爬虫微信搜狗搜索列表")
    public void export(HttpServletResponse response, WeiXinSouGou weiXinSouGou) {
        List<WeiXinSouGou> list = weiXinSouGouService.selectWeiXinSouGouList(weiXinSouGou);
        ExcelUtil<WeiXinSouGou> util = new ExcelUtil<>(WeiXinSouGou.class);
        util.exportExcel(response, list, "爬虫微信搜狗搜索数据");
    }

    /**
     * 删除爬虫微信搜狗搜索
     */
    @RequiresPermissions("webmagic:weixinsougou:remove")
    @Log(title = "微信搜狗", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("爬虫微信搜狗搜索")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(weiXinSouGouService.deleteWeiXinSouGouByIds(ids));
    }

}
