package com.xjs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiejs
 * @since 2022-05-25
 */
@RestController
@RequestMapping("time")
public class TimeController {

    @GetMapping
    public String getTime() {
        return "2022-05-25 09:41:34";
    }

}
