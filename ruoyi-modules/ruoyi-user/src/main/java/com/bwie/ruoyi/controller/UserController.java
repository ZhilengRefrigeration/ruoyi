package com.bwie.ruoyi.controller;

import com.bwie.ruoyi.pojo.TbUser;
import com.bwie.ruoyi.service.TbUserService;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.system.api.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 苏海龙
 * @version 1.0
 * @description: TODO
 * @date 2023/1/13 14:40
 */

@RestController
@RequestMapping("user")
@Slf4j
public class UserController extends BaseController {
    @Resource
    private TbUserService tbUserServicet;
    @PostMapping("getuser")
    private TableDataInfo getuser(@RequestBody PageInfoVo pageInfoVo){
        PageHelper.startPage(pageInfoVo.getPageNum(),pageInfoVo.getPageSize());
         List<TbUserVo> list=tbUserServicet.getuser();
        return getDataTable(list);
    }
    @PostMapping("deluser")
    private AjaxResult deluser(@RequestBody IdVo idVo)  {
        return tbUserServicet.deluser(idVo);
    }
    @PostMapping("adduser")
    private AjaxResult adduser(@RequestBody TbUserVo tbUserVo){
        return tbUserServicet.adduser(tbUserVo);
    }

    @PostMapping("updateuser")
    private AjaxResult updateuser(@RequestBody TbUserVo tbUserVo){
        return tbUserServicet.updateuser(tbUserVo);
    }
    //修改密码
    @PostMapping("uppassword")
    private AjaxResult uppassword(@RequestBody PassWordCode passWordCode){
        return tbUserServicet.uppassword(passWordCode);
    }
    //登录
    @PostMapping("loginUser")
    private AjaxResult loginuser(@RequestBody LoginVo loginVo){
        return tbUserServicet.loginuser(loginVo);
    }
}
