package com.xjs.mall.product.controller;

import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.xjs.mall.product.entity.AttrEntity;
import com.xjs.mall.product.entity.AttrGroupEntity;
import com.xjs.mall.product.service.AttrAttrgroupRelationService;
import com.xjs.mall.product.service.AttrGroupService;
import com.xjs.mall.product.service.AttrService;
import com.xjs.mall.product.service.CategoryService;
import com.xjs.mall.product.vo.AttrGroupRelationVo;
import com.xjs.mall.product.vo.AttrGroupWithAttrsVo;
import com.xjs.utils.PageUtils;
import com.xjs.mall.other.R;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import com.xjs.web.MyBaseController;
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
public class AttrGroupController extends MyBaseController<AttrGroupEntity> {
    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @GetMapping("/{catelogId}/withattr")
    @ApiOperation("根据分类id获取规格参数")
    public R getAttrGroupWithAttrs(@PathVariable("catelogId")Long catelogId){
        //1、查出当前分类下的所有属性分组，
        //2、查出每个属性分组的所有属性
        List<AttrGroupWithAttrsVo> vos =  attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data",vos);
    }

    /**
     * 获取关联信息
     *
     * @param attrgroupId 属性分组id
     * @return r
     */
    @GetMapping("/{attrgroupId}/attr/relation")
    @ApiOperation("获取关联信息")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId) {
        List<AttrEntity> attrList = attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data", attrList);
    }

    /**
     * 获取未被关联的信息
     * @param attrgroupId 属性分组id
     * @param params 条件
     * @return R
     */
    @GetMapping("/{attrgroupId}/noattr/relation")
    @ApiOperation("获取未被关联的信息")
    public R attrNoRelation(@PathVariable("attrgroupId") Long attrgroupId, @RequestParam Map<String, Object> params) {
        PageUtils page=attrService.getAttrNoRelation(params, attrgroupId);
        return R.ok().put("page", page);
    }

    @PostMapping("/attr/relation")
    @ApiOperation("添加属性分组与规格关联")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> vos) {

        attrAttrgroupRelationService.saveBatch(vos);
        return R.ok();
    }

    /**
     * 列表
     */
    @GetMapping("/list/{catelogId}")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params, Long catelogId) {
        super.checkParams(params);
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
        attrGroupService.removeAttrGroup(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
