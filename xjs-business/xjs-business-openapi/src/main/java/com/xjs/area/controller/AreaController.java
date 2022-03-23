package com.xjs.area.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.area.domain.Area;
import com.xjs.area.service.AreaService;
import com.xjs.web.MyBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 区域编码controller
 *
 * @author xiejs
 * @since 2022-03-22
 */
@RestController
@RequestMapping("area")
@Api(tags = "业务模块-区域编码")
@Log4j2
public class AreaController extends MyBaseController<Area> {

    @Autowired
    private AreaService areaService;

    @GetMapping("rest")
    @ApiOperation("更新获取区域编码信息")
    @RequiresPermissions("openapi:area:rest")
    public AjaxResult restArea() {
        areaService.truncateArea();
        areaService.saveArea();

        return AjaxResult.success();
    }

    @GetMapping("getProvinceArea")
    @ApiOperation("获取所有省级区域")
    @RequiresPermissions("openapi:area:list")
    public AjaxResult getProvinceArea() {
        List<Area> areaList = areaService.getProvinceArea();
        return AjaxResult.success(areaList);
    }

    @GetMapping("getAreaByParentId/{pid}")
    @ApiOperation("根据父ID获取区域")
    @RequiresPermissions("openapi:area:list")
    public AjaxResult getAreaByParentId(@PathVariable Long pid) {
        List<Area> areaList = areaService.getAreaByParentId(pid);
        return AjaxResult.success(areaList);
    }


    //--------------------------------------远程调用------------------------------------------
    @GetMapping("getProvinceAreaForRPC")
    @ApiOperation("获取所有省级区域ForRPC")
    public R<List<Area>> getProvinceAreaForRPC() {
        List<Area> areaList = areaService.getProvinceArea();
        return R.ok(areaList);
    }

    @GetMapping("getAreaByParentIdForRPC/{pid}")
    @ApiOperation("根据父ID获取区域ForRPC")
    public R<List<Area>> getAreaByParentIdForRPC(@PathVariable Long pid) {
        List<Area> areaList = areaService.getAreaByParentId(pid);
        return R.ok(areaList);
    }


}
