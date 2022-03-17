package com.xjs.mall.product.controller;

import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.xjs.mall.product.entity.AttrGroupEntity;
import com.xjs.mall.product.service.AttrGroupService;
import com.xjs.mall.product.service.CategoryService;
import com.xjs.utils.PageUtils;
import com.xjs.utils.R;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 属性分组
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since 2022-03-15 10:16:53
 */
@RestController
@RequestMapping("product/attrgroup")
@Api(tags = "商城-商品-属性分组")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @GetMapping("/list/{catelogId}")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params, Long catelogId) {

        PageUtils page = attrGroupService.queryPage(params, catelogId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{attrGroupId}")
    @ApiOperation("信息")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        Long[] path = categoryService.finCatelogPath(attrGroup.getCatelogId());
        List<String> collect = Arrays.stream(path).map(String::valueOf).collect(Collectors.toList());

        attrGroup.setCatelogPath(collect);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存")
    @Log(title = "属性分组", businessType = BusinessType.INSERT)
    public R save(@Validated(AddGroup.class) @RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation("修改")
    @Log(title = "属性分组", businessType = BusinessType.UPDATE)
    public R update(@Validated(UpdateGroup.class) @RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除")
    @Log(title = "属性分组", businessType = BusinessType.DELETE)
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
