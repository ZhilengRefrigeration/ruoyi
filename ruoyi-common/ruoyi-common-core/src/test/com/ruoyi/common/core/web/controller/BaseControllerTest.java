package com.ruoyi.common.core.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.annotation.order.CustomOrder;
import com.ruoyi.common.core.annotation.order.DefaultOrder;
import com.ruoyi.common.core.annotation.order.TableAlias;
import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.common.core.web.page.PageDomain;
import com.ruoyi.common.core.web.page.TableSupport;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BaseControllerTest {

    @Test
    void testStartPageByDefaultOrder()
    {
        @DefaultOrder(tableName = "user", column = "userName")
        class UserVO {
            private String userName;
        }
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(10);
        pageDomain.setPageSize(20);

        MockedStatic<TableSupport> mocked = Mockito.mockStatic(TableSupport.class);
        mocked.when(TableSupport::buildPageRequest).thenReturn(
                pageDomain
        );
        new BaseController().startPage(UserVO.class);
        Page<Object> localPage = PageHelper.getLocalPage();
        String orderBy = localPage.getOrderBy();
        assertEquals("user.user_name asc", orderBy);
    }

    @Test
    void testStartPageByDefaultOrder2()
    {
        @DefaultOrder(tableName = "user", column = "user_name")
        class UserVO {
            private String userName;
        }
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(10);
        pageDomain.setPageSize(20);

        MockedStatic<TableSupport> mocked = Mockito.mockStatic(TableSupport.class);
        mocked.when(TableSupport::buildPageRequest).thenReturn(
                pageDomain
        );
        new BaseController().startPage(UserVO.class);
        Page<Object> localPage = PageHelper.getLocalPage();
        String orderBy = localPage.getOrderBy();
        assertEquals("user.user_name asc", orderBy);
    }
    @Test
    void testStartPageByTableAlias()
    {
        class UserVO {
            @TableAlias("user")
            private String userName;
        }
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(10);
        pageDomain.setPageSize(20);
        pageDomain.setOrderByColumn("userName");
        MockedStatic<TableSupport> mocked = Mockito.mockStatic(TableSupport.class);
        mocked.when(TableSupport::buildPageRequest).thenReturn(
                pageDomain
        );
        new BaseController().startPage(UserVO.class);
        Page<Object> localPage = PageHelper.getLocalPage();
        String orderBy = localPage.getOrderBy();
        assertEquals("user.user_name asc", orderBy);
    }
    @Test
    void testStartPageByCustomOrder()
    {
        @CustomOrder(tableName = "user", column = "createTime")
        class UserVO extends BaseEntity {
            private String userName;
        }
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(10);
        pageDomain.setPageSize(20);
        pageDomain.setOrderByColumn("createTime");
        pageDomain.setIsAsc("desc");

        MockedStatic<TableSupport> mocked = Mockito.mockStatic(TableSupport.class);
        mocked.when(TableSupport::buildPageRequest).thenReturn(
                pageDomain
        );
        new BaseController().startPage(UserVO.class);
        Page<Object> localPage = PageHelper.getLocalPage();
        String orderBy = localPage.getOrderBy();
        assertEquals("user.create_time desc", orderBy);
    }

    @Test
    void testStartPageByCustomOrder2()
    {
        @CustomOrder(tableName = "user", column = "create_time")
        class UserVO extends BaseEntity {
            private String userName;
        }
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(10);
        pageDomain.setPageSize(20);
        pageDomain.setOrderByColumn("createTime");
        pageDomain.setIsAsc("desc");

        MockedStatic<TableSupport> mocked = Mockito.mockStatic(TableSupport.class);
        mocked.when(TableSupport::buildPageRequest).thenReturn(
                pageDomain
        );
        new BaseController().startPage(UserVO.class);
        Page<Object> localPage = PageHelper.getLocalPage();
        String orderBy = localPage.getOrderBy();
        assertEquals("user.create_time desc", orderBy);
    }
}