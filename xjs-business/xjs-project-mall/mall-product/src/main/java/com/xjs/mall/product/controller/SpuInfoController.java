package com.xjs.mall.product.controller;

import com.xjs.mall.other.R;
import com.xjs.mall.product.entity.SpuInfoEntity;
import com.xjs.mall.product.service.SpuInfoService;
import com.xjs.mall.product.vo.spu.SpuSaveVo;
import com.xjs.utils.PageUtils;
import com.xjs.validation.group.AddGroup;
import com.xjs.web.MyBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
public class SpuInfoController extends MyBaseController<SpuInfoEntity> {
    @Autowired
    private SpuInfoService spuInfoService;

    @ApiOperation("商品上架")
    @PostMapping("/{spuId}/up")
    public R spuUp(@PathVariable("spuId") Long spuId) {
        spuInfoService.up(spuId);
        return R.ok();
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params) {
        super.checkParams(params);
        PageUtils page = spuInfoService.queryPageByCondition(params);

        return R.ok().put("page", page);
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
}
