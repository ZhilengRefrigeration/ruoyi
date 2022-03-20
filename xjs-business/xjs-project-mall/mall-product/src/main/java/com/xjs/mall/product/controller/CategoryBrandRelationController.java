package com.xjs.mall.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.xjs.mall.product.entity.BrandEntity;
import com.xjs.mall.product.entity.CategoryBrandRelationEntity;
import com.xjs.mall.product.service.CategoryBrandRelationService;
import com.xjs.mall.product.vo.BrandVo;
import com.xjs.mall.other.R;
import com.xjs.validation.group.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 品牌分类关联
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 10:16:53
 */
@RestController
@RequestMapping("product/categorybrandrelation")
@Api(tags = "商城-商品-品牌分类关联")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     *根据分类id找到品牌
     * @param catId 分类
     * @return r
     */
    @GetMapping("/brands/list")
    @ApiOperation("分类关联品牌列表")
    public R catelogList(@RequestParam("brand") Long catId) {
        List<BrandEntity> brandEntities=categoryBrandRelationService.getBrandsByCatId(catId);

        List<BrandVo> collect = brandEntities.stream().map(item -> {
            BrandVo brandVo = new BrandVo();
            brandVo.setBrandId(item.getBrandId());
            brandVo.setBrandName(item.getName());
            return brandVo;
        }).collect(Collectors.toList());

        return R.ok().put("data", collect);
    }

    /**
     * 获取当前品牌关联的所有分类列表
     */
    @GetMapping("/catelog/list")
    @ApiOperation("品牌关联分类列表")
    public R list(@RequestParam Long brandId) {

        LambdaQueryWrapper<CategoryBrandRelationEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CategoryBrandRelationEntity::getBrandId, brandId);

        List<CategoryBrandRelationEntity> list = categoryBrandRelationService.list(wrapper);

        return R.ok().put("page", list);
    }


    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存")
    @Log(title = "品牌分类关联", businessType = BusinessType.INSERT)
    public R save(@Validated(AddGroup.class) @RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
        categoryBrandRelationService.saveDetail(categoryBrandRelation);

        return R.ok();
    }


    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除")
    @Log(title = "品牌分类关联", businessType = BusinessType.DELETE)
    public R delete(@RequestBody Long[] ids) {
        categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
