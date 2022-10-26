package com.server.xm.controller;

import com.server.xm.entity.XmNodeStyle;
import com.server.xm.service.XmNodeStyleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 样式表(XmNodeStyle)表控制层
 *
 * @author makejava
 * @since 2022-10-24 22:33:28
 */
@RestController
@RequestMapping("xmNodeStyle")
public class XmNodeStyleController {
    /**
     * 服务对象
     */
    @Resource
    private XmNodeStyleService xmNodeStyleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public XmNodeStyle selectOne(String id) {
        return this.xmNodeStyleService.queryById(id);
    }

}
