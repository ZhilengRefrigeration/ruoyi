package org.link.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "管理后台 - 测试类")
@RestController
@RequestMapping( "/test01")
public class Test01Controller {

}
