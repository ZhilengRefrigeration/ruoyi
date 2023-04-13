package com.bwie.user.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bwie.user.domain.TbUser;
import com.bwie.user.service.TbUserService;
import com.bwie.user.mapper.TbUserMapper;
import com.bwie.user.utils.DuanxinUtils;
import com.bwie.user.utils.TokenUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.system.api.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
* @author 海龙
* @description 针对表【tb_user(用户信息表)】的数据库操作Service实现
* @createDate 2023-01-13 14:34:44
*/
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser>
    implements TbUserService{
    @Resource
    private TbUserMapper tbUserMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public   List<TbUserVo> getuser() {
        List<TbUser> tbUsers = tbUserMapper.selectList(null);
        List<TbUserVo> collect = tbUsers.stream().map(item -> {
            TbUserVo tbUserVo = new TbUserVo();
            BeanUtils.copyProperties(item, tbUserVo);
            return tbUserVo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public AjaxResult deluser(IdVo idVo) {
        tbUserMapper.deleteById(idVo.getId());
        return AjaxResult.success();
    }

    @Override
    public AjaxResult adduser(PhoneVo phoneVo) {
        LambdaQueryWrapper<TbUser> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbUser::getPhonenumber,phoneVo.getPhone());
        TbUser tbUser1 = tbUserMapper.selectOne(wrapper);
        if(tbUser1!=null){
            return AjaxResult.error("手机号已被注册");
        }
        Object o = redisTemplate.opsForValue().get(phoneVo.getPhone());
        if(!phoneVo.getCode().equals(o)){
            return AjaxResult.error("验证码不正确");
        }
        TbUser tbUser = new TbUser();
        tbUser.setNickName("tbuser_"+RandomUtil.randomNumbers(4));
        tbUser.setPhonenumber(phoneVo.getPhone());
        String hashpw = BCrypt.hashpw("123456", BCrypt.gensalt());
        tbUser.setPassword(hashpw);
        tbUserMapper.insert(tbUser);
        return AjaxResult.success(tbUser);
    }

    @Override
    public AjaxResult phoneSend(PhoneVo phoneVo) {
        //随机生成的四位数
        String nums = RandomUtil.randomNumbers(6);
        //存入redis用来做登录
        redisTemplate.opsForValue().set(phoneVo.getPhone(), nums);
        //向手机发送信息
        DuanxinUtils.sendDuanxin(phoneVo.getPhone(), nums);
        return AjaxResult.success("验证码已经发送是:"+nums+"请注意查收");
    }

    @Override
    public AjaxResult updateuser(TbUserVo tbUserVo) {
        LambdaQueryWrapper<TbUser> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbUser::getUserId,tbUserVo.getUserId());
        if(tbUserMapper.selectOne(wrapper)==null){
            return AjaxResult.error();
        }
        TbUser tbUser = new TbUser();
        BeanUtils.copyProperties(tbUserVo, tbUser);
        tbUserMapper.updateById(tbUser);
        return AjaxResult.success("修改完成");
    }

    @Override
    public AjaxResult uppassword(PassWordCode passWordCode) {
        LambdaQueryWrapper<TbUser> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbUser::getUserId,passWordCode.getUserId());
        if(tbUserMapper.selectOne(wrapper)==null){
            return AjaxResult.error("用户不存在");
        }
        boolean b = BCrypt.checkpw(passWordCode.getOldPassword(), tbUserMapper.selectOne(wrapper).getPassword());
        if(!b){
            return AjaxResult.error("输入的老密码不正确请重新输入");
        }
        String hashpw = BCrypt.hashpw(passWordCode.getNewPassword(), BCrypt.gensalt());
        TbUser tbUser = new TbUser();
        BeanUtils.copyProperties(tbUserMapper.selectOne(wrapper), tbUser);
        tbUser.setPassword(hashpw);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult loginuser(LoginVo loginVo) {
        LambdaQueryWrapper<TbUser> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbUser::getPhonenumber,loginVo.getPhone());
        TbUser tbUser = tbUserMapper.selectOne(wrapper);
        if(tbUser==null){
            return AjaxResult.error("电话号没有被注册请先注册");
        }
        //在进行判断code
        String code =(String) redisTemplate.opsForValue().get(loginVo.getPhone());
        if(!loginVo.getCode().equals(code)) {
            return AjaxResult.error("验证码不正确");
        }
        //生成token
        String token = TokenUtils.token().setKey("123456").setClaim("userId", tbUser.getUserId() + "").setClaim("nickName", tbUser.getNickName()).makeToken();
        //存入redis
        redisTemplate.opsForValue().set(token, token,1, TimeUnit.MINUTES);
        //返回类
        TbUserVo tbUserVo = new TbUserVo();
        BeanUtils.copyProperties(tbUser, tbUserVo);
        tbUserVo.setToken(token);
        tbUserVo.setPassword(null);
        tbUserVo.setUserCard(null);
        return AjaxResult.success(tbUserVo);
    }


}




