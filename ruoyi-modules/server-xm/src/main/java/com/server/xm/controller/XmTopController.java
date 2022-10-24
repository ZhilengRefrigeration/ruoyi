package com.server.xm.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.server.xm.entity.XmTop;
import com.server.xm.entity.vo.XmTopVo;
import com.server.xm.service.XmTopService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Xmind列表(XmTop)表控制层
 *
 * @author makejava
 * @since 2022-10-23 12:50:31
 */
@RestController
@RequestMapping("/xmTop")
public class XmTopController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private XmTopService xmTopService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/findOne/{id}")
    public XmTopVo selectOne(@PathVariable("id") String id) {
        return this.xmTopService.queryById(id);
    }

    @GetMapping("/page")
    public TableDataInfo page(XmTop xmTop){
        startPage();
        List<XmTop> list = this.xmTopService.queryAll(xmTop);
        return getDataTable(list);
    }

}
