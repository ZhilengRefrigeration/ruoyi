package com.ruoyi.wms.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alan Scipio
 * created on 2024/2/1
 */
@RequestMapping("/test")
@RestController
public class TestWmsController extends BaseController {

    @GetMapping("/showInfo")
    public AjaxResult showInfo() {
        return success("Hello World! This is wms module.");
    }

}
