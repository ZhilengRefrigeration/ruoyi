package com.ruoyi.xjt.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.xjt.service.XjtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class XjtUserController {
    @Autowired
    private XjtUserService xjtUserService;
    /**
     * 根据角色编号获取详细信息---测试分支提交--添加
     */
    @RequiresPermissions("system:role:query")
    @GetMapping(value = "/{username}")
    public AjaxResult getInfo(@PathVariable String username)
    {
        System.out.println("用户username:"+username);
        R<Map<String,Object>> userinfo=  xjtUserService.getUserInfo(username, SecurityConstants.INNER);
        System.out.println("用户信息:"+JSON.toJSONString(userinfo));
        LoginUser loginuser = SecurityUtils.getLoginUser();
        System.out.println("登录用户信息"+JSON.toJSONString(loginuser));
        return AjaxResult.success(userinfo);
    }
}
