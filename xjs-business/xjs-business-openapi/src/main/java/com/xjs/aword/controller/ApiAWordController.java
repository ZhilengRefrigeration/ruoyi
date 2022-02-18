package com.xjs.aword.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresLogin;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.aword.domain.ApiAWord;
import com.xjs.aword.domain.RequestBody;
import com.xjs.aword.factory.AWordFactory;
import com.xjs.aword.service.ApiAWordService;
import com.xjs.validation.group.SelectGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * api每日一句控制器
 *
 * @author xiejs
 * @since 2022-01-08
 */
@RequestMapping("aword")
@RestController
@Api(tags = "业务模块-每日一句")
public class ApiAWordController extends BaseController {
    @Autowired
    private AWordFactory tianXingAWordFactory;
    @Autowired
    private ApiAWordService apiAWordService;

    @GetMapping
    @ApiOperation("每日一句接口")
    @Log(title = "获取每日一句")
    @RequiresLogin
    public AjaxResult getApiAWord(@Validated RequestBody requestBody) {
        requestBody = Optional.ofNullable(requestBody).orElseGet(RequestBody::new);
        ApiAWord apiAWord = tianXingAWordFactory.productApiAWord(requestBody);
        return AjaxResult.success(apiAWord);
    }


    //--------------------------代码生成---------------------------------

    /**
     * 查询每日一句列表
     */
    @ApiOperation("查询每日一句列表")
    @RequiresPermissions("openapi:aword:list")
    @GetMapping("/list")
    public TableDataInfo list(@Validated({SelectGroup.class}) ApiAWord apiAWord) {
        startPage();
        List<ApiAWord> list = apiAWordService.selectApiAWordList(apiAWord);
        return getDataTable(list);
    }

    /**
     * 导出每日一句列表
     */
    @RequiresPermissions("openapi:aword:export")
    @Log(title = "每日一句", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出每日一句列表")
    public void export(HttpServletResponse response, ApiAWord apiAWord) {
        List<ApiAWord> list = apiAWordService.selectApiAWordList(apiAWord);
        ExcelUtil<ApiAWord> util = new ExcelUtil<ApiAWord>(ApiAWord.class);
        util.exportExcel(response, list, "每日一句数据");
    }

    /**
     * 获取每日一句详细信息
     */
    @RequiresPermissions("openapi:aword:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取每日一句详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(apiAWordService.selectApiAWordById(id));
    }

    /**
     * 删除每日一句
     */
    @RequiresPermissions("openapi:aword:remove")
    @Log(title = "每日一句", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除每日一句")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(apiAWordService.deleteApiAWordByIds(ids));
    }
}
