package com.xjs.sina.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.sina.pojo.SinaNews;
import com.xjs.sina.service.SinaNewsService;
import com.xjs.sina.task.SinaNewsTask;
import com.xjs.web.MyBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 新浪爬虫数据controller
 *
 * @author xiejs
 * @since 2022-02-15
 */
@RestController
@RequestMapping("sinaNews")
@Api(tags = "爬虫模块-新浪新闻")
public class SinaNewsController extends MyBaseController {
    @Autowired
    private SinaNewsTask sinaNewsTask;
    @Autowired
    private SinaNewsService sinaNewsService;


    @GetMapping("getType")
    @ApiOperation("获取标签")
    public AjaxResult getType() {
        List<Object> typeList=sinaNewsService.getType();
        return AjaxResult.success(typeList);
    }


    //----------------------远程rpc调用---------------------------
    @GetMapping("taskForPRC")
    @ApiOperation("供定时任务服务RPC远程调用")
    public R sinaTaskForPRC() {
        Long count = sinaNewsTask.reptileSinaNews();
        return R.ok(count);
    }


    //----------------------代码生成----------------------------

    /**
     * 查询新浪新闻列表
     */
    @RequiresPermissions("webmagic:sinaNews:list")
    @GetMapping("/list")
    @ApiOperation("查询新浪新闻列表")
    public TableDataInfo list(SinaNews sinaNews) {
        startPage();
        List<SinaNews> list = sinaNewsService.selectSinaNewsList(sinaNews);
        return getDataTable(list);
    }

    /**
     * 删除新浪新闻
     */
    @RequiresPermissions("webmagic:sinaNews:remove")
    @Log(title = "新浪新闻", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除新浪新闻")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sinaNewsService.deleteSinaNewsByIds(ids));
    }


}
