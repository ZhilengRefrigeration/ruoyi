package com.ruoyi.system.controller;

import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.DataDictionary;
import com.ruoyi.system.service.IDataDictionaryService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@RestController
@RequestMapping("/dictionary")
public class DataDictionaryController extends BaseController
{
    @Autowired
    private IDataDictionaryService dataDictionaryService;
    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:dictionary:list")
    @GetMapping("/list")
    public TableDataInfo list(DataDictionary dataDictionary)
    {
        startPage();
        List<DataDictionary> list = dataDictionaryService.selectDataDictionaryList(dataDictionary);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:dictionary:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DataDictionary dataDictionary)
    {
        List<DataDictionary> list = dataDictionaryService.selectDataDictionaryList(dataDictionary);
        ExcelUtil<DataDictionary> util = new ExcelUtil<DataDictionary>(DataDictionary.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("system:dictionary:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(dataDictionaryService.selectDataDictionaryById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("system:dictionary:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DataDictionary dataDictionary)
    {
        return toAjax(dataDictionaryService.insertDataDictionary(dataDictionary));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("system:dictionary:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DataDictionary dataDictionary)
    {
        return toAjax(dataDictionaryService.updateDataDictionary(dataDictionary));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:dictionary:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dataDictionaryService.deleteDataDictionaryByIds(ids));
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"通过parentKey获取数据字典")
    @GetMapping(value = "/getChildByParentKey/{parentKey}")
    AjaxResult getChildByParentKey(@PathVariable("parentKey") String parentKey){
        if(StringUtils.isBlank(parentKey)){
            throw new IllegalArgumentException("参数["+parentKey+"]不能为空");
        }
        return AjaxResult.success(dataDictionaryService.getChildByParentKey(parentKey));
    }
}
