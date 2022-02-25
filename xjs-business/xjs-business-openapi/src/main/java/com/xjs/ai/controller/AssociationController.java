package com.xjs.ai.controller;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresLogin;
import com.xjs.ai.factory.AssociationFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 联想controller
 *
 * @author xiejs
 * @since 2022-02-24
 */
@RestController
@RequestMapping("association")
@Api(tags = "业务模块-联想管理")
@Log4j2
public class AssociationController {

    @Autowired
    private AssociationFactory<List<String>> baiduAssociationFactory;


    @GetMapping("getAssociation")
    @ApiOperation("获取联想词汇")
    @RequiresLogin
    public AjaxResult getAssociation(@RequestParam("content") String content) {
        if (StringUtils.isBlank(content)) {
            return AjaxResult.success("参数为空");
        }
        List<String> data = baiduAssociationFactory.getData(content);
        return AjaxResult.success(data);
    }
}
