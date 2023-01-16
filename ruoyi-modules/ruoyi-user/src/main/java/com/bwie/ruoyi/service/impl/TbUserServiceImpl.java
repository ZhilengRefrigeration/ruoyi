package com.bwie.ruoyi.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bwie.ruoyi.pojo.TbUser;
import com.bwie.ruoyi.service.TbUserService;
import com.bwie.ruoyi.mapper.TbUserMapper;
import com.bwie.ruoyi.utils.DuanxinUtils;
import com.bwie.ruoyi.utils.TokenUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.system.api.model.*;
import org.apache.poi.ss.formula.functions.T;
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
    public AjaxResult adduser(TbUserVo tbUserVo) {
        LambdaQueryWrapper<TbUser> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbUser::getPhonenumber,tbUserVo.getPhonenumber());
        //判断手机号
        if(tbUserMapper.selectOne(wrapper)!=null){
            return AjaxResult.error("手机号已经注册请检查手机号");
        }
        //判断用户的昵称
        LambdaQueryWrapper<TbUser> wrapper1=new LambdaQueryWrapper<>();
        wrapper1.eq(TbUser::getNickName,tbUserVo.getNickName() );
        if(tbUserMapper.selectOne(wrapper1)!=null){
            return AjaxResult.error("用户昵称已经被注册请换一个");
        }
        //加密密码
        String hashpw = BCrypt.hashpw(tbUserVo.getPassword(), BCrypt.gensalt());
        TbUser tbUser = new TbUser();
        BeanUtils.copyProperties(tbUserVo, tbUser);
        //更换加密密码
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
        //第一步判断账号
        LambdaQueryWrapper<TbUser> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TbUser::getNickName,loginVo.getNickName());
        TbUser tbUser = tbUserMapper.selectOne(wrapper);
        if(tbUser==null){
            return AjaxResult.error("账号不存在");
        }
        boolean checkpw = BCrypt.checkpw(loginVo.getUserPass(), tbUser.getPassword());
        if(!checkpw){
            return AjaxResult.error("密码不正确");
        }
        //判断code
        String o =(String) redisTemplate.opsForValue().get(loginVo.getPhone());
        if(!loginVo.getCode().equals(o)){
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




