package com.xjs.copywriting;

import cn.hutool.core.util.RandomUtil;
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
    public AjaxResult translation(@Validated RequestBody requestBody) {
        requestBody = Optional.ofNullable(requestBody).orElseGet(RequestBody::new);
        ArrayList<CopyWritingFactory> factories = new ArrayList<>();
        //添加了新接口只需要在这add接口进去
        factories.add(tianXingcopyWritingFactory);
        //随机调用集合中的接口
        CopyWritingFactory copyWritingFactory = RandomUtil.randomEle(factories);
        CopyWriting copyWriting = copyWritingFactory.productCopyWriting(requestBody);
        return AjaxResult.success(copyWriting);
    }

}
