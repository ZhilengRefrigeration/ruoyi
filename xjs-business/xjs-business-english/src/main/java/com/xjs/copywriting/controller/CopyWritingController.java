package com.xjs.copywriting.controller;

import cn.hutool.core.util.RandomUtil;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresLogin;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.copywriting.factory.CopyWritingFactory;
import com.xjs.copywriting.service.CopyWritingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-27
 */
@RestController
@RequestMapping("copyWriting")
@Api(tags = "业务模块-文案管理")
public class CopyWritingController extends BaseController {

    @Autowired
    private CopyWritingFactory tianXingPYQCopyWritingFactory;
    @Autowired
    private CopyWritingFactory tianXingWYYCopyWritingFactory;
    @Autowired
    private CopyWritingService copyWritingService;


    @GetMapping
    @ApiOperation("文案接口")
    @Log(title = "获取文案")
    @RequiresLogin
    public AjaxResult copyWriting(@Validated RequestBody requestBody) {
        requestBody = Optional.ofNullable(requestBody).orElseGet(RequestBody::new);
        CopyWritingFactory copyWritingFactory = this.randomApi();
        CopyWriting copyWriting = this.handlerException(copyWritingFactory, requestBody);
        return AjaxResult.success(copyWriting);
    }


    @GetMapping("forPRC")
    @ApiOperation("供定时任务服务RPC远程调用")
    public R<CopyWriting> copyWriting() {
        CopyWritingFactory copyWritingFactory = this.randomApi();
        CopyWriting copyWriting = this.handlerException(copyWritingFactory, new RequestBody());
        return R.ok(copyWriting);
    }

    /**
     * 封装随机调用api
     *
     * @return 文案工厂
     */
    private CopyWritingFactory randomApi() {
        ArrayList<CopyWritingFactory> factories = new ArrayList<>();
        //添加了新接口只需要在这add接口进去
        factories.add(tianXingPYQCopyWritingFactory);
        factories.add(tianXingWYYCopyWritingFactory);
        //--------add----------------------------;-
        //随机调用集合中的接口
        return RandomUtil.randomEle(factories);
    }


    /**
     * 捕获apiException异常，直接从数据库查询值然后返回
     *
     * @param copyWritingFactory 工厂
     * @param requestBody        请求参数
     * @return 返回对象
     */
    private CopyWriting handlerException(CopyWritingFactory copyWritingFactory, RequestBody requestBody) {
        CopyWriting copyWriting = null;
        try {
            copyWriting = copyWritingFactory.productCopyWriting(requestBody);
            return copyWriting;
        } catch (Exception e) {
            e.printStackTrace();
            copyWriting = copyWritingService.getOneToRandom();
            return copyWriting;
        }
    }


//------------------------代码自动生成-----------------------------------

    /**
     * 查询文案api，通过api获取文案信息列表
     */
    @ApiOperation("文案列表")
    @RequiresPermissions("system:copywriting:list")
    @GetMapping("/list")
    public TableDataInfo list(CopyWriting copyWriting) {
        startPage();
        List<CopyWriting> list = copyWritingService.selectCopyWritingList(copyWriting);
        return getDataTable(list);
    }

    /**
     * 导出文案api，通过api获取文案信息列表
     */
    @RequiresPermissions("system:copywriting:export")
    @Log(title = "文案管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出文案")
    public void export(HttpServletResponse response, CopyWriting copyWriting) {
        List<CopyWriting> list = copyWritingService.selectCopyWritingList(copyWriting);
        ExcelUtil<CopyWriting> util = new ExcelUtil<>(CopyWriting.class);
        util.exportExcel(response, list, "文案api，通过api获取文案信息数据");
    }

    /**
     * 获取文案api，通过api获取文案信息详细信息
     */
    @RequiresPermissions("system:copywriting:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取文案根据ID")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(copyWritingService.selectCopyWritingById(id));
    }

    /**
     * 删除文案api，通过api获取文案信息
     */
    @RequiresPermissions("system:copywriting:remove")
    @Log(title = "文案管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除文案")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(copyWritingService.deleteCopyWritingByIds(ids));
    }




}
