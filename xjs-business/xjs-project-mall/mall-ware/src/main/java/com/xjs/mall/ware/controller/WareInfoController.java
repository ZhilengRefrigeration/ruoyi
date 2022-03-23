package com.xjs.mall.ware.controller;

import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.xjs.business.api.domain.Area;
import com.xjs.mall.other.R;
import com.xjs.mall.ware.entity.WareInfoEntity;
import com.xjs.mall.ware.service.WareInfoService;
import com.xjs.utils.PageUtils;
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


/**
 * 仓库信息
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since 2022-03-15 09:56:19
 */
@RestController
@RequestMapping("ware/wareinfo")
@Api(tags = "商城-仓库-仓库信息")
public class WareInfoController extends MyBaseController<WareInfoEntity> {
    @Autowired
    private WareInfoService wareInfoService;

    @GetMapping("getProvinceArea")
    @ApiOperation("获取所有省级区域")
    public R getProvinceArea() {
        List<Area> areaList = wareInfoService.getProvinceArea();
        return R.ok().put("data", areaList);
    }

    @GetMapping("getAreaByParentId/{pid}")
    @ApiOperation("根据父ID获取区域")
    public R getAreaByParentId(@PathVariable Long pid) {
        List<Area> areaList = wareInfoService.getAreaByParentId(pid);
        return R.ok().put("data", areaList);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params) {
        super.checkParams(params);
        PageUtils page = wareInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("信息")
    public R info(@PathVariable("id") Long id) {
        WareInfoEntity wareInfo = wareInfoService.getById(id);

        return R.ok().put("wareInfo", wareInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存")
    @Log(title = "仓库信息", businessType = BusinessType.INSERT)
    public R save(@Validated(AddGroup.class) @RequestBody WareInfoEntity wareInfo) {
        wareInfoService.save(wareInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation("修改")
    @Log(title = "仓库信息", businessType = BusinessType.UPDATE)
    public R update(@Validated(UpdateGroup.class)@RequestBody WareInfoEntity wareInfo) {
        wareInfoService.updateById(wareInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除")
    @Log(title = "仓库信息", businessType = BusinessType.DELETE)
    public R delete(@RequestBody Long[] ids) {
        wareInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }



}
