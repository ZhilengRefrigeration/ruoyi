package com.xjs.word.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.SelectGroup;
import com.xjs.validation.group.UpdateGroup;
import com.xjs.web.MyBaseController;
import com.xjs.word.domain.EnglishWord;
import com.xjs.word.service.IEnglishWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 英语单词Controller
 *
 * @author xjs
 * @date 2021-12-29
 */
@RestController
@RequestMapping("/word")
@Api(tags = "业务模块-英语单词管理")
public class EnglishWordController extends MyBaseController<EnglishWord> {
    @Autowired
    private IEnglishWordService englishWordService;


    /**
     * 英语单词收藏夹
     *
     * @return TableDataInfo
     */
    @RequiresPermissions("english:word:collect")
    @GetMapping("collect")
    @ApiOperation("英语单词收藏夹")
    public AjaxResult collect() {
        IPage<EnglishWord> englishWordList = englishWordService.getEnglishWordByCollect(startPageMP());
        return AjaxResult.success(englishWordList);
    }


    /**
     * 获取英语单词详细信息RPC
     */
    @GetMapping(value = "/rpc/{id}")
    @ApiOperation("获取英语单词详细信息RPC")
    public AjaxResult getInfoRPC(@PathVariable("id") Long id) {
        return AjaxResult.success(englishWordService.selectEnglishWordToRPC(id));
    }

    /**
     * 获取英语单词详细信息
     */
    @RequiresPermissions("english:word:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取英语单词详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(englishWordService.selectById(id));
    }

    /**
     * 查询英语单词列表
     */
    @RequiresPermissions("english:word:list")
    @GetMapping("/list")
    @ApiOperation("查询英语单词列表")
    public AjaxResult list(@Validated({SelectGroup.class}) EnglishWord englishWord) {
        return AjaxResult.success(englishWordService.selectEnglishWordList(startPageMP(), englishWord));
    }


    //------------------------内部调用rpc-----------------------------------
    @GetMapping("getEnglishWordForRpc")
    @ApiOperation("随机获取5条英语单词数据")
    public R<List<EnglishWord>> getEnglishWordByRandom() {
        List<EnglishWord> englishWordList=englishWordService.getEnglishWordByRandom();
        return R.ok(englishWordList);
    }


    //------------------------代码自动生成-----------------------------------


    /**
     * 导出英语单词列表
     */
    @RequiresPermissions("english:word:export")
    @ApiOperation("导出英语单词列表")
    @Log(title = "英语单词", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnglishWord englishWord) {
        List<EnglishWord> list = englishWordService.selectEnglishWordList(englishWord);
        ExcelUtil<EnglishWord> util = new ExcelUtil<EnglishWord>(EnglishWord.class);
        util.exportExcel(response, list, "英语单词数据");
    }


    /**
     * 新增英语单词
     */
    @RequiresPermissions("english:word:add")
    @ApiOperation("新增英语单词")
    @Log(title = "英语单词", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated({AddGroup.class}) @RequestBody EnglishWord englishWord) {
        return toAjax(englishWordService.insertEnglishWord(englishWord));
    }

    /**
     * 修改英语单词
     */
    @RequiresPermissions("english:word:edit")
    @ApiOperation("修改英语单词")
    @Log(title = "英语单词", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated({UpdateGroup.class}) @RequestBody EnglishWord englishWord) {
        return toAjax(englishWordService.updateEnglishWord(englishWord));
    }

    /**
     * 删除英语单词
     */
    @RequiresPermissions("english:word:remove")
    @ApiOperation("删除英语单词")
    @Log(title = "英语单词", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(englishWordService.deleteEnglishWordByIds(ids));
    }
}
