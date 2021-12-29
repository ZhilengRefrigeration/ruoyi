package com.xjs.word.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.word.domain.EnglishWord;
import com.xjs.word.service.IEnglishWordService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EnglishWordController extends BaseController {
    @Autowired
    private IEnglishWordService englishWordService;


    //------------------------代码自动生成-----------------------------------

    /**
     * 查询英语单词列表
     */
    @RequiresPermissions("english:word:list")
    @GetMapping("/list")
    public TableDataInfo list(EnglishWord englishWord) {
        startPage();
        List<EnglishWord> list = englishWordService.selectEnglishWordList(englishWord);
        return getDataTable(list);
    }

    /**
     * 导出英语单词列表
     */
    @RequiresPermissions("english:word:export")
    @Log(title = "英语单词", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnglishWord englishWord) {
        List<EnglishWord> list = englishWordService.selectEnglishWordList(englishWord);
        ExcelUtil<EnglishWord> util = new ExcelUtil<EnglishWord>(EnglishWord.class);
        util.exportExcel(response, list, "英语单词数据");
    }

    /**
     * 获取英语单词详细信息
     */
    @RequiresPermissions("english:word:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(englishWordService.selectEnglishWordById(id));
    }

    /**
     * 新增英语单词
     */
    @RequiresPermissions("english:word:add")
    @Log(title = "英语单词", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnglishWord englishWord) {
        return toAjax(englishWordService.insertEnglishWord(englishWord));
    }

    /**
     * 修改英语单词
     */
    @RequiresPermissions("english:word:edit")
    @Log(title = "英语单词", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnglishWord englishWord) {
        return toAjax(englishWordService.updateEnglishWord(englishWord));
    }

    /**
     * 删除英语单词
     */
    @RequiresPermissions("english:word:remove")
    @Log(title = "英语单词", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(englishWordService.deleteEnglishWordByIds(ids));
    }
}
