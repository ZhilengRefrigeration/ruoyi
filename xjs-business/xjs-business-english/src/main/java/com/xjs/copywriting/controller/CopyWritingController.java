package com.xjs.copywriting.controller;

import cn.hutool.core.util.RandomUtil;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-27
 */
@RestController
@RequestMapping("copyWriting")
@Api(tags = "业务模块-文案管理")
public class CopyWritingController {

    @Autowired
    private CopyWritingFactory tianXingPYQCopyWritingFactory;
    @Autowired
    private CopyWritingFactory tianXingWYYCopyWritingFactory;
    @Autowired
    private CopyWritingService copyWritingService;

    //todo 文案管理前端页面，
    // 天行数据整合一个菜单，
    // 实现其他天行数据接口，
    // 实现其他朋友圈文案api，

    @GetMapping
    @ApiOperation("文案接口")
    @Log(title = "获取文案")
    @RequiresLogin
    @RequiresPermissions("english:translation:api")
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
     * @param copyWritingFactory 工厂
     * @param requestBody 请求参数
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









}
