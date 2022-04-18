package com.xjs.zol.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.validation.group.SelectGroup;
import com.xjs.web.MyBaseController;
import com.xjs.zol.pojo.ZolPhone;
import com.xjs.zol.service.ZolPhoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 爬虫中关村手机controller
 * @author xiejs
 * @since 2022-04-18
 */
@RestController
@RequestMapping("zol-phone")
@Api(tags = "爬虫模块-中关村手机")
public class ZolPhoneController extends MyBaseController<ZolPhone> {

    @Autowired
    private ZolPhoneService zolPhoneService;


    @RequiresPermissions("webmagic:zol-phone:list")
    @GetMapping("/list")
    @ApiOperation("查询中关村手机列表")
    public AjaxResult list(@Validated({SelectGroup.class}) ZolPhone zolPhone) {
        IPage<ZolPhone> page=zolPhoneService.selectZolPhoneByPage(startPageMP(),zolPhone);
        return AjaxResult.success(page);
    }
}
