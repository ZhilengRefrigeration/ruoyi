package com.bwie.ruoyi.service;

import com.bwie.ruoyi.pojo.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.system.api.model.*;

import java.util.List;

/**
* @author 海龙
* @description 针对表【tb_user(用户信息表)】的数据库操作Service
* @createDate 2023-01-13 14:34:44
*/
public interface TbUserService extends IService<TbUser> {

    List<TbUserVo> getuser();

    AjaxResult deluser(IdVo idVo);

    AjaxResult adduser(TbUserVo tbUserVo);

    AjaxResult phoneSend(PhoneVo phoneVo);

    AjaxResult updateuser(TbUserVo tbUserVo);

    AjaxResult uppassword(PassWordCode passWordCode);

    AjaxResult loginuser(LoginVo loginVo);
}
