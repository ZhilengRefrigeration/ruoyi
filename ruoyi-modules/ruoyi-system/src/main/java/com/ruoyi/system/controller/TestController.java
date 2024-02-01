package com.ruoyi.system.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alan Scipio
 * created on 2024/1/31
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @GetMapping("/showInfo")
    public AjaxResult showInfo() {
        return success("Hello World! This is system module.");
    }

}
