package com.xjs.web;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * mvc拦截器
 * @author xiejs
 * @since 2022-01-13
 */
public class MyHandlerInterceptor implements HandlerInterceptor {

    /**
     * 会在handler方法业务逻辑执行之后尚未跳转页面之前执行
     * @param request 请求参数
     * @param response 响应参数
     * @param handler 处理方法
     * @param modelAndView 封装了视图数据
     * @throws Exception e
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        //System.out.println("handler1"+handler);

    }


    /**
     * 会在handler方法业务逻辑执行之前执行
     * @param request 请求参数
     * @param response 响应参数
     * @param handler 处理方法
     * @return boolean  true放行 false阻止
     * @throws Exception e
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
