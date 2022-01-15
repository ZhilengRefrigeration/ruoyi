package com.xjs.translation.controller;

import cn.hutool.core.util.RandomUtil;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.translation.domain.qo.translation.TranslationQo;
import com.xjs.translation.domain.vo.translation.TranslationVo;
import com.xjs.translation.factory.TranslationFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.xjs.consts.TranslationTypeConst.*;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-25
 */
@RestController
@RequestMapping("translation")
@Api(tags = "业务模块-翻译管理")
@Log4j2
public class TranslationController {

    @Autowired
    private TranslationFactory youDaoTranslationFactory;
    @Autowired
    private TranslationFactory baiDuTranslationFactory;
    @Autowired
    private TranslationFactory rollTranslationFactory;

    @PostMapping
    @ApiOperation("翻译接口")
    @Log(title = "获取翻译")
    @RequiresPermissions("openapi:translation:api")
    public AjaxResult translation(@Validated @RequestBody TranslationQo translationQo) {
        TranslationVo translationVo = new TranslationVo();
        if (BAIDU.equals(translationQo.getTranslationType())) {
            translationVo = baiDuTranslationFactory.translationApi(translationQo);
        }
        if (YOUDAO.equals(translationQo.getTranslationType())) {
            translationVo = youDaoTranslationFactory.translationApi(translationQo);
        }
        if (ROLL.equals(translationQo.getTranslationType())) {
            translationVo = rollTranslationFactory.translationApi(translationQo);
        }
        return AjaxResult.success(translationVo);
    }


    @GetMapping("forRPC")
    @ApiOperation("翻译接口远程PRC调用")
    public R<TranslationVo> translation(String content) {
        TranslationFactory translationFactory = this.randomApi();
        TranslationQo translationQo = new TranslationQo();
        translationQo.setQ(content);
        TranslationVo translationVo;
        try {
            translationVo = translationFactory.translationApi(translationQo);
        } catch (Exception e) {
            translationVo = new TranslationVo();
            translationVo.setErrorCode((long) R.FAIL);
            log.error("翻译接口平台异常");
            e.printStackTrace();
        }
        return R.ok(translationVo);
    }

    /**
     * 封装随机调用api
     *
     * @return 翻译工厂
     */
    private TranslationFactory randomApi() {
        ArrayList<TranslationFactory> factories = new ArrayList<>();
        //添加了新接口只需要在这add接口进去
        factories.add(youDaoTranslationFactory);
        factories.add(baiDuTranslationFactory);
        factories.add(rollTranslationFactory);
        //--------add----------------------------;-
        //随机调用集合中的接口
        return RandomUtil.randomEle(factories);
    }

}
