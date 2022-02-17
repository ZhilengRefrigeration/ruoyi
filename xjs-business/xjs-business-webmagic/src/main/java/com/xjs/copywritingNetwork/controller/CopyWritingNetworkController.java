package com.xjs.copywritingNetwork.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.copywritingNetwork.pojo.CopyWritingNetwork;
import com.xjs.copywritingNetwork.service.CopyWritingNetworkService;
import com.xjs.copywritingNetwork.task.CopyWritingNetworkTask;
import com.xjs.web.MyBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文案网controller
 *
 * @author xiejs
 * @since 2022-02-17
 */
@RestController
@RequestMapping("copyWritingNetwork")
@Api(tags = "爬虫模块-文案网")
public class CopyWritingNetworkController extends MyBaseController {

    @Autowired
    private CopyWritingNetworkService copyWritingNetworkService;
    @Autowired
    private CopyWritingNetworkTask copyWritingNetworkTask;


    @GetMapping("getType")
    @ApiOperation("获取标签")
    public AjaxResult getType() {
        List<Object> typeList=copyWritingNetworkService.getType();
        return AjaxResult.success(typeList);
    }



    //----------------------远程rpc调用---------------------------
    @GetMapping("taskForPRC")
    @ApiOperation("供定时任务服务RPC远程调用")
    public R copyWritingNetworkTaskForPRC() {
        Long count = copyWritingNetworkTask.reptileCopyWriting();
        return R.ok(count);
    }


    //----------------------------代码生成-----------------------------

    /**
     * 查询文案网列表
     */
    @RequiresPermissions("webmagic:copyWritingNetwork:list")
    @GetMapping("/list")
    public TableDataInfo list(CopyWritingNetwork copyWritingNetwork) {
        startPage();
        List<CopyWritingNetwork> list = copyWritingNetworkService.selectCopyWritingNetworkList(copyWritingNetwork);
        return getDataTable(list);
    }

    /**
     * 导出文案网列表
     */
    @RequiresPermissions("webmagic:copyWritingNetwork:export")
    @Log(title = "文案网", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CopyWritingNetwork copyWritingNetwork) {
        List<CopyWritingNetwork> list = copyWritingNetworkService.selectCopyWritingNetworkList(copyWritingNetwork);
        ExcelUtil<CopyWritingNetwork> util = new ExcelUtil<>(CopyWritingNetwork.class);
        util.exportExcel(response, list, "文案网数据");
    }

    /**
     * 删除文案网
     */
    @RequiresPermissions("webmagic:copyWritingNetwork:remove")
    @Log(title = "文案网", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(copyWritingNetworkService.deleteCopyWritingNetworkByIds(ids));
    }


}
