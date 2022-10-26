package com.server.xm.controller;

import com.server.xm.entity.XmNode;
import com.server.xm.service.XmNodeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Xmind列表(XmNode)表控制层
 *
 * @author makejava
 * @since 2022-10-24 22:16:42
 */
@RestController
@RequestMapping("xmNode")
public class XmNodeController {
    /**
     * 服务对象
     */
    @Resource
    private XmNodeService xmNodeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public XmNode selectOne(String id) {
        return this.xmNodeService.queryById(id);
    }

}
