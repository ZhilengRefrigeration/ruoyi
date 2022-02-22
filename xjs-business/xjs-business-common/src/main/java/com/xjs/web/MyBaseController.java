package com.xjs.web;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.sql.SqlUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.page.PageDomain;
import com.ruoyi.common.core.web.page.TableSupport;

/**
 * 自定义通用controller
 *
 * @author xiejs
 * @since 2021-12-31
 */
public class MyBaseController<T> extends BaseController {

    /**
     * mp分页封装
     *
     * @return Page
     */
    protected Page<T> startPageMP() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<T> page = new Page<>();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            page.setSize(pageSize);
            page.setCurrent(pageNum);
        }
        String orderByColumn = pageDomain.getOrderByColumn();
        if (StringUtils.isNotNull(orderByColumn)) {
            String orderBy = SqlUtil.escapeOrderBySql(StringUtils.toUnderScoreCase(orderByColumn));

            if (pageDomain.getIsAsc().equals("asc")) {
                page.addOrder(OrderItem.asc(orderBy));
            } else {
                page.addOrder(OrderItem.desc(orderBy));
            }
        }
        return page;
    }

}
