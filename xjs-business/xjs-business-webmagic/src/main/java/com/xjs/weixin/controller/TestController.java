package com.xjs.weixin.controller;

import com.xjs.weixin.task.OfficialAccountsTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiejs
 * @since 2022-06-13
 */
@RequestMapping("test")
@RestController
@Api(tags = "测试")
public class TestController {

    @Autowired
    private OfficialAccountsTask officialAccountsTask;


    @GetMapping
    @ApiOperation("微信公众号")
    public String test() {
        officialAccountsTask.execute();
        return "success";
    }
}
