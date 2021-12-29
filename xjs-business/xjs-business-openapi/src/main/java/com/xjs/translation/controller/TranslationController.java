package com.xjs.translation.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.security.annotation.RequiresLogin;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.translation.domain.qo.translation.TranslationQo;
import com.xjs.translation.domain.vo.translation.TranslationVo;
import com.xjs.translation.factory.TranslationFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xjs.common.consts.TranslationTypeConst.BAIDU;
import static com.xjs.common.consts.TranslationTypeConst.YOUDAO;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-25
 */
@RestController
@RequestMapping("translation")
@Api(tags = "业务模块-翻译管理")
public class TranslationController {

    @Autowired
    private TranslationFactory youDaoTranslationFactory;
    @Autowired
    private TranslationFactory baiDuTranslationFactory;

    @PostMapping
    @ApiOperation("翻译接口")
    @Log(title = "获取翻译")
    @RequiresLogin
    @RequiresPermissions("openapi:translation:api")
    public AjaxResult translation(@Validated @RequestBody TranslationQo translationQo) {
        TranslationVo translationVo=new TranslationVo();
        if (BAIDU.equals(translationQo.getTranslationType())) {
            translationVo = baiDuTranslationFactory.translationApi(translationQo);
        }
        if (YOUDAO.equals(translationQo.getTranslationType())) {
            translationVo = youDaoTranslationFactory.translationApi(translationQo);
        }
        return AjaxResult.success(translationVo);
    }
}
