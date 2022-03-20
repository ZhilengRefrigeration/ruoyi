package com.xjs.mall.product.controller;

import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.xjs.mall.product.entity.CategoryEntity;
import com.xjs.mall.product.service.CategoryService;
import com.xjs.mall.other.R;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 商品三级分类
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since 2022-03-15 10:16:53
 */
@RestController
@RequestMapping("product/category")
@Api(tags = "商城-商品-分类")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表--树形结构
     */
    @GetMapping("/list/tree")
    @ApiOperation("树形结构")
    public R list() {

        List<CategoryEntity> list = categoryService.listWithTree();

        return R.ok().put("page", list);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{catId}")
    @ApiOperation("信息")
    public R info(@PathVariable("catId") Long catId) {
        CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("data", category);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    public R save(@Validated(AddGroup.class) @RequestBody CategoryEntity category) {
        categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation("修改")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    public R update(@Validated(UpdateGroup.class) @RequestBody CategoryEntity category) {
        categoryService.updateCascade(category);

        return R.ok();
    }

    @PutMapping("/update/sort")
    @ApiOperation("修改商品分类排序")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    public R updateSort(@RequestBody CategoryEntity[] categoryEntities) {
        categoryService.updateBatchById(Arrays.asList(categoryEntities));
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    public R delete(@RequestBody Long[] catIds) {
        if (catIds == null || catIds.length == 0) {
            return R.error("请选择删除的分类");
        }
        categoryService.removeMenuByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
