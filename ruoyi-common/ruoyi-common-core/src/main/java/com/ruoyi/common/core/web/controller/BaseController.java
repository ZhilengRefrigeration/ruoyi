package com.ruoyi.common.core.web.controller;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.ruoyi.common.core.annotation.order.CustomOrder;
import com.ruoyi.common.core.annotation.order.DefaultOrder;
import com.ruoyi.common.core.annotation.order.TableAlias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.sql.SqlUtil;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.PageDomain;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.core.web.page.TableSupport;

/**
 * web层通用数据处理
 *
 * @author ruoyi
 */
public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }

    /**
     * 设置请求分页数据
     * 并根据class的注解设置排序列
     * 列对应表名优先级
     * TableAlias > CustomOrder > DefaultOrder
     * @param clazz
     */
    protected void startPage(Class<?> clazz) {
        // 正常分页的情况
        if (clazz == null) {
            startPage();
            return;
        }
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }

        // 处理orderBy的情况。
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        String column = SqlUtil.escapeOrderBySql(pageDomain.getOrderByColumn());
        // 获取默认排序
        if (StringUtils.isEmpty(orderBy)) {
            orderBy = getDefaultOrderByAnnotation(clazz);
        }

        if (StringUtils.isEmpty(orderBy)) {
            return;
        }
        // 根据列获取表名
        String tableName = getTableNameByColumn(clazz, column);
        if (StringUtils.isNotEmpty(tableName)) {
            orderBy = tableName + "." + orderBy;
        }
        PageHelper.orderBy(orderBy);
    }

    /**
     * 尝试从field的TableAlias获取表别名
     * @param clazz
     * @param column
     * @return
     */
    private static String getFieldTableName(Class<?> clazz, String column) {
        // 获取对象中所有的成员变量
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            String fieldName = field.getName();
            if (Objects.equals(column, fieldName)) {
                if (field.isAnnotationPresent(TableAlias.class)) {
                    TableAlias orderTableAlias = field.getAnnotation(TableAlias.class);
                    return orderTableAlias.value();
                }
            }
        }
        return null;
    }

    /**
     * 尝试从CustomOrder获取表别名
     * @param clazz
     * @param column
     * @return
     */
    private static String getCustomOrderTableName(Class<?> clazz, String column) {
        // 获取对象中所有的成员变量
        if (clazz.isAnnotationPresent(CustomOrder.class)) {
            CustomOrder[] customOrders = clazz.getAnnotationsByType(CustomOrder.class);
            for (CustomOrder customOrder : customOrders) {
                String realColumn = column;
                if (column.contains("_")) {
                    realColumn = StringUtils.toCamelCase(customOrder.column());
                }
                if (Objects.equals(column, realColumn)) {
                    return customOrder.tableName();
                }
            }
        }
        return null;
    }
    /**
     * 获取默认排序
     * @param clazz
     * @return
     */
    private static String getDefaultTableName(Class<?> clazz) {
        // 最后，查找类上的tableName
        if (clazz.isAnnotationPresent(DefaultOrder.class)) {
            DefaultOrder defaultOrder = clazz.getAnnotation(DefaultOrder.class);
            if (StringUtils.isNotEmpty(defaultOrder.tableName())) {
                return defaultOrder.tableName();
            }
        }
        return null;
    }

    /**
     * 通过过滤列获取对应的table
     * @param clazz
     * @param column
     * @return
     */
    private static String getTableNameByColumn(Class<?> clazz, String column) {
        if (clazz == null) {
            return null;
        }
        String tableName = null;
        // 未指定排序 则尝试设置默认排序的Table
        if (StringUtils.isEmpty(column)) {
            tableName = getDefaultTableName(clazz);
            return tableName;
        }


        // 优先获取字段的tableName
        tableName = getFieldTableName(clazz, column);
        if (StringUtils.isNotEmpty(tableName)) {
            return tableName;
        }

        // 其次查找CustomOrder定义的column对应的TableName
        tableName = getCustomOrderTableName(clazz, column);
        if (StringUtils.isNotEmpty(tableName)) {
            return tableName;
        }

        // 获取默认排序
        tableName = getDefaultTableName(clazz);

        return tableName;
    }

    /**
     * 获取类注解获取默认过滤列
     * @param clazz
     * @return
     */
    private String getDefaultOrderByAnnotation(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(DefaultOrder.class)) {
            return null;
        }
        DefaultOrder defaultOrder = clazz.getAnnotation(DefaultOrder.class);
        String orderBy = null;
        if (StringUtils.isNotEmpty(defaultOrder.column())) {
            orderBy = StringUtils.toUnderScoreCase(defaultOrder.column()) + " " + defaultOrder.orderType();
        }
        return orderBy;
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        rspData.setMsg("查询成功");
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }
}
