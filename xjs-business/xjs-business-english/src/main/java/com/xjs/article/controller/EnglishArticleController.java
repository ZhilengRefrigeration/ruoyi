package com.xjs.article.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.article.domain.EnglishArticle;
import com.xjs.article.service.impl.EnglishArticleServiceImpl;
import com.xjs.web.MyBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 英语文章Controller
 *
 * @author xiejs
 * @since 2022-03-03
 */
@RestController
@RequestMapping("/article")
@Api(tags = "业务模块-英语文章管理")
public class EnglishArticleController extends MyBaseController<EnglishArticle> {

    @Autowired
    private EnglishArticleServiceImpl englishArticleService;

    /**
     * 查询英语文章列表
     */
    @RequiresPermissions("english:article:list")
    @GetMapping("/list")
    @ApiOperation("查询英语文章列表")
    public AjaxResult list(EnglishArticle englishArticle) {
        IPage<EnglishArticle> page = englishArticleService.selectEnglishArticleList(startPageMP(), englishArticle);
        return AjaxResult.success(page);
    }

    @GetMapping("/test")
    public AjaxResult test(EnglishArticle englishArticle) {

        EnglishArticle translation = englishArticleService.translation(englishArticle);
        return AjaxResult.success(translation);

    }



    //--------------------------代码生成------------------------------------

    /**
     * 获取英语文章详细信息
     */
    @RequiresPermissions("english:article:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取英语文章详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(englishArticleService.selectEnglishArticleById(id));
    }

    /**
     * 新增英语文章
     */
    @RequiresPermissions("english:article:add")
    @Log(title = "英语文章", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增英语文章")
    public AjaxResult add(@RequestBody EnglishArticle englishArticle) {
        return toAjax(englishArticleService.insertEnglishArticle(englishArticle));
    }

    /**
     * 修改英语文章
     */
    @RequiresPermissions("english:article:edit")
    @Log(title = "英语文章", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改英语文章")
    public AjaxResult edit(@RequestBody EnglishArticle englishArticle) {
        return toAjax(englishArticleService.updateEnglishArticle(englishArticle));
    }

    /**
     * 删除英语文章
     */
    @RequiresPermissions("english:article:remove")
    @Log(title = "英语文章", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除英语文章")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(englishArticleService.deleteEnglishArticleByIds(ids));
    }

}
