package com.xjs.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.page.PageDomain;
import com.ruoyi.common.core.web.page.TableSupport;

/**
 * 自定义通用controller
 * @author xiejs
 * @since  2021-12-31
 */
public class MyBaseController extends BaseController {

    /**
     *  mp分页封装
     * @return Page
     */
    protected Page startPageMP( ){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page page = new Page<>();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            page.setSize(pageSize);
            page.setCurrent(pageNum);
        }
        return page;
    }

}
