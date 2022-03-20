package com.xjs.mall.product.controller;

import com.xjs.mall.other.R;
import com.xjs.mall.product.entity.SpuInfoEntity;
import com.xjs.mall.product.service.SpuInfoService;
import com.xjs.mall.product.vo.spu.SpuSaveVo;
import com.xjs.utils.PageUtils;
import com.xjs.validation.group.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * spu信息
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since 2022-03-15 10:16:53
 */
@RestController
@RequestMapping("product/spuinfo")
@Api(tags = "商城-商品-SPU信息")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = spuInfoService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        SpuInfoEntity spuInfo = spuInfoService.getById(id);

        return R.ok().put("spuInfo", spuInfo);
    }

    /**
     * 保存(保存spu关联的所有信息)
     */
    @PostMapping("/save")
    @ApiOperation("保存spu关联的所有信息")
    public R saveSpuInfo(@Validated(AddGroup.class) @RequestBody SpuSaveVo spuInfo) {
        spuInfoService.saveSpuInfo(spuInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SpuInfoEntity spuInfo) {
        spuInfoService.updateById(spuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        spuInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
