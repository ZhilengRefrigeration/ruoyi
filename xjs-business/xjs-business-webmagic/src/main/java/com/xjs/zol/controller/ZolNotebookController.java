package com.xjs.zol.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.validation.group.SelectGroup;
import com.xjs.web.MyBaseController;
import com.xjs.zol.pojo.ZolNotebook;
import com.xjs.zol.service.ZolNotebookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 爬虫中关村笔记本controller
 * @author xiejs
 * @since 2022-04-18
 */
@RestController
@RequestMapping("zol-notebook")
@Api(tags = "爬虫模块-中关村笔记本")
public class ZolNotebookController extends MyBaseController<ZolNotebook> {

    @Autowired
    private ZolNotebookService zolNotebookService;

    @RequiresPermissions("webmagic:zol-notebook:list")
    @GetMapping("/list")
    @ApiOperation("查询中关村笔记本列表")
    public AjaxResult list(@Validated({SelectGroup.class}) ZolNotebook zolNotebook) {
        IPage<ZolNotebook> page=zolNotebookService.selectZolPhoneByPage(startPageMP(),zolNotebook);
        return AjaxResult.success(page);
    }

}
