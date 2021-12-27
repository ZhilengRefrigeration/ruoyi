package com.xjs.copywriting;

import cn.hutool.core.util.RandomUtil;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.security.annotation.RequiresLogin;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.domain.RequestBody;
import com.xjs.copywriting.factory.CopyWritingFactory;
import com.xjs.translation.domain.qo.translation.TranslationQo;
import com.xjs.translation.domain.vo.translation.TranslationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
public class CopyWritingController {

    @Autowired
    private CopyWritingFactory tianXingcopyWritingFactory;

    @GetMapping
    @ApiOperation("文案接口")
    @Log(title = "文案管理")
    @RequiresLogin
    @RequiresPermissions("english:translation:api")
    public AjaxResult copyWriting(@Validated RequestBody requestBody) {
        requestBody = Optional.ofNullable(requestBody).orElseGet(RequestBody::new);
        CopyWritingFactory copyWritingFactory = this.randomApi();
        CopyWriting copyWriting = copyWritingFactory.productCopyWriting(requestBody);
        return AjaxResult.success(copyWriting);
    }


    @GetMapping("forPRC")
    @ApiOperation("供定时任务服务RPC远程调用")
    public R<CopyWriting> copyWriting() {
        CopyWritingFactory copyWritingFactory = this.randomApi();
        return R.ok(copyWritingFactory.productCopyWriting(new RequestBody()));
    }


    /**
     * 封装随机调用api
     * @return 文案工厂
     */
    private CopyWritingFactory randomApi() {
        ArrayList<CopyWritingFactory> factories = new ArrayList<>();
        //添加了新接口只需要在这add接口进去
        factories.add(tianXingcopyWritingFactory);
        //--------add----------------------------;-
        //随机调用集合中的接口
        return RandomUtil.randomEle(factories);
    }








}
