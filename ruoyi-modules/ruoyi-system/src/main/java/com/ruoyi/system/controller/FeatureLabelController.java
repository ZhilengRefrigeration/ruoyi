package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ruoyi.system.domain.FeatureLabel;
import com.ruoyi.system.service.IFeatureLabelService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 球馆特征Controller
 * 
 * @author ruoyi
 * @date 2023-07-06
 */
@RestController
@RequestMapping("/featureLabel")
public class FeatureLabelController extends BaseController
{
    @Autowired
    private IFeatureLabelService featureLabelService;

    /**
     * 查询球馆特征列表
     */
    @RequiresPermissions("system:featureLabel:list")
    @GetMapping("/list")
    public TableDataInfo list(FeatureLabel featureLabel)
    {
        startPage();
        List<FeatureLabel> list = featureLabelService.selectFeatureLabelList(featureLabel);
        return getDataTable(list);
    }

    /**
     * 导出球馆特征列表
     */
    @RequiresPermissions("system:featureLabel:export")
    @Log(title = "球馆特征", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FeatureLabel featureLabel)
    {
        List<FeatureLabel> list = featureLabelService.selectFeatureLabelList(featureLabel);
        ExcelUtil<FeatureLabel> util = new ExcelUtil<FeatureLabel>(FeatureLabel.class);
        util.exportExcel(response, list, "球馆特征数据");
    }

    /**
     * 获取球馆特征详细信息
     */
    @RequiresPermissions("system:featureLabel:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(featureLabelService.selectFeatureLabelById(id));
    }

    /**
     * 新增球馆特征
     */
    @RequiresPermissions("system:featureLabel:add")
    @Log(title = "球馆特征", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FeatureLabel featureLabel)
    {
        return toAjax(featureLabelService.insertFeatureLabel(featureLabel));
    }

    /**
     * 修改球馆特征
     */
    @RequiresPermissions("system:featureLabel:edit")
    @Log(title = "球馆特征", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FeatureLabel featureLabel)
    {
        return toAjax(featureLabelService.updateFeatureLabel(featureLabel));
    }

    /**
     * 删除球馆特征
     */
    @RequiresPermissions("system:featureLabel:remove")
    @Log(title = "球馆特征", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(featureLabelService.deleteFeatureLabelByIds(ids));
    }
}
