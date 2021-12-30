package com.xjs.translation.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.security.annotation.RequiresLogin;
import com.xjs.translation.domain.qo.translation.TranslationQo;
import com.xjs.translation.domain.vo.translation.TranslationVo;
import com.xjs.translation.factory.TranslationFactory;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiejs
 * @desc 翻译字典控制器
 * @create 2021-12-30
 */
@RestController
@RequestMapping("tranDict")
@Api(tags = "业务模块-翻译字典管理")
@Log4j2
public class TranDictController {

    @Autowired
    private TranslationFactory tianXingTranDictFactory;

    @GetMapping("tranDictForRPC")
    @RequiresLogin
    public R<TranslationVo> tranDict(String content) {
        TranslationQo translationQo = new TranslationQo();
        translationQo.setQ(content);
        TranslationVo translationVo;
        try {
            translationVo = tianXingTranDictFactory.translationApi(translationQo);
        } catch (Exception e) {
            translationVo = new TranslationVo();
            translationVo.setErrorCode((long) R.FAIL);
            log.error("翻译字典接口平台异常");
            e.printStackTrace();
        }
        return R.ok(translationVo);
    }
}
