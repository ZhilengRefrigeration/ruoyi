package com.xjs.mall.product.controller;

import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.xjs.mall.product.service.AttrService;
import com.xjs.mall.product.vo.AttrResponseVo;
import com.xjs.mall.product.vo.AttrVo;
import com.xjs.utils.PageUtils;
import com.xjs.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 商品属性
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 10:16:53
 */
@RestController
@RequestMapping("product/attr")
@Api(tags = "商城-商品-规格参数")
public class AttrController {
    @Autowired
    private AttrService attrService;


    @GetMapping("/base/list/{catelogId}")
    @ApiOperation("列表")
    public R baseAttrList(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId) {
        PageUtils page = attrService.queryBaseAttrPage(params, catelogId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{attrId}")
    @ApiOperation("信息")
    public R info(@PathVariable("attrId") Long attrId){
		AttrResponseVo attr = attrService.getAttrInfo(attrId);

        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存")
    @Log(title = "规格参数", businessType = BusinessType.INSERT)
    public R save(@RequestBody AttrVo attr){
		attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation("修改")
    @Log(title = "规格参数", businessType = BusinessType.UPDATE)
    public R update(@RequestBody AttrVo attr){
		attrService.updateAttr(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除")
    @Log(title = "规格参数", businessType = BusinessType.DELETE)
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
