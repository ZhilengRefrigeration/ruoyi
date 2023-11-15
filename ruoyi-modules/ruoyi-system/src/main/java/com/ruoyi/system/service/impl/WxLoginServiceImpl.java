package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.api.model.WxLoginUser;
import com.ruoyi.system.domain.UserRole;
import com.ruoyi.system.domain.WxUser;
import com.ruoyi.system.domain.enums.UserRoles;
import com.ruoyi.system.domain.vo.WxLoginRequest;
import com.ruoyi.system.mapper.UserRoleMapper;
import com.ruoyi.system.mapper.WxUserMapper;
import com.ruoyi.system.service.WxLoginService;
import com.ruoyi.system.utils.jwt.JwtUtil;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 吴一博
 * @date 2023年07月06日 11:08
 * @Description
 */
@Service
public class WxLoginServiceImpl  implements WxLoginService {
    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public WxLoginUser loginInFromWx(WxLoginRequest entity) {
        //获取openId
        String openId="";
        //多平台的唯一id
        String unionid="";
        //登录成功后返回的 sessionKey
        String sessionKey = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 参数
        StringBuffer params = new StringBuffer();
        try {
            //组装参数
            params.append("appid=" + URLEncoder.encode(Constants.WX_APPLETS_APP_ID, "utf-8"));
            params.append("&");
            params.append("secret="+Constants.WX_APPLETS_APP_SERCERT);
            params.append("&");
            params.append("js_code="+entity.getLoginName());
            params.append("&");
            params.append("grant_type=authorization_code");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        // 创建Get请求微信接口获取openId
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/sns/jscode2session" + "?" + params);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                String responseStr= EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:"+responseStr);
                JSONObject jsonObject = JSONObject.parseObject(responseStr);
                openId = jsonObject.getString("openid");
                unionid = jsonObject.getString("unionid");
                sessionKey = jsonObject.getString("session_key");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放请求资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<String> codes =new ArrayList<>();
        if(StringUtils.isEmpty(openId)){
            throw new ServiceException("获取openId失败");
        }
        //openId="oQUGq5cR1a1X_1_cqJZOC5AOeXHk";
        //根据openid查询登录信息是否存在
        WxUser login= wxUserMapper.selectByOpenId(openId);
        //如果为空，则表示是新用户登录，将该用户写入信息
        if(login==null){
            login=new WxUser();
            login.setUserName(entity.getNickname());
            if(StringUtils.isEmpty(entity.getTelephone())){
                login.setLoginName(entity.getLoginName());
            }else {
                login.setLoginName(entity.getTelephone());
            }
            login.setAvatar(entity.getAvatar());
            login.setGender(entity.getGender());
            login.setTelephone(entity.getTelephone());
            login.setOpenid(openId);
            login.setUnionid(unionid);
            login.setEnabled(1);
            login.setCreatedBy("admin");
            login.setCreatedTime(new Date());
            wxUserMapper.insertWxUser(login);
            //插入用户角色关联信息
            UserRole userRole=new UserRole();
            userRole.setRoleCode(UserRoles.customer.code());
            userRole.setUserId(login.getId());
            UserRole role = userRoleMapper.selectByCode(userRole.getRoleCode());
            if(StringUtils.isEmpty(role)){
                userRole.setRoleId(3L);
            }else {
                userRole.setRoleId(role.getId());
            }
            userRoleMapper.insertUserRole(userRole);
            codes.add(userRole.getRoleCode());
        }else{
            if(login.getEnabled()!=null&&0==login.getEnabled()){
                throw new ServiceException("账号已被冻结，请联系管理员！");
            }
            //唯一id为空就更新
            if(StringUtils.isEmpty(login.getUnionid()) && !StringUtils.isEmpty(unionid)){
                WxUser userInfo1=new WxUser();
                userInfo1.setId(login.getId());
                userInfo1.setUnionid(unionid);
                userInfo1.setLastUpdatedTime(new Date());
                wxUserMapper.updateWxUser(userInfo1);
            }

            //查询登录用户的系统角色
            UserRole userRole = new UserRole();
            userRole.setUserId(login.getId());
            List<UserRole> userRoles = userRoleMapper.selectUserRoleList(userRole);
            codes = userRoles.stream().map(UserRole::getRoleCode).collect(Collectors.toList());
        }
        //查询用户角色
        //List<String> roleCodes=userRoleService.findUserRoleListByUserId(login.getBusinessId());
        //查询用户权限
        //List<String> permissions=rolePermissionService.findUserPermissionByUserId(login.getBusinessId());
        Map<String,Object> claims=new HashMap<>();
        //claims.put(Constants.JWT_PERMISSIONS_KEY,permissions);
        //claims.put(Constants.JWT_ROLES_KEY,roleCodes);
        claims.put(Constants.JWT_USER_ID,login.getId());
        claims.put(Constants.JWT_USER_NAME,login.getUserName());
        claims.put(Constants.JWT_LOGIN_NAME,login.getLoginName());
        claims.put(Constants.JWT_UNIONID,unionid);
        claims.put(Constants.JWT_SESSION_KEY,sessionKey);
        claims.put(Constants.JWT_OPEN_ID,openId);
        claims.put(Constants.JWT_REAL_NAME,login.getRealName());
        //生成token
        String accessToken= JwtUtil.getAccessToken(login.getId().toString(),claims);
        //生产刷新token
        String refreshToken= JwtUtil.getWxRefreshToken(login.getId().toString(),claims);
        WxLoginUser loginUser=new WxLoginUser();
        loginUser.setUserId(login.getId());
        loginUser.setLoginName(login.getLoginName());
        loginUser.setUserName(login.getUserName());
        loginUser.setAccessToken(accessToken);
        loginUser.setRefreshToken(refreshToken);
        loginUser.setUnionid(unionid);
        loginUser.setRoleCodes(codes);
        return loginUser;
    }

    @Override
    public LoginUser loginFromWx(LoginUser entity) {
        //获取openId
        String openId="";
        //多平台的唯一id
        String unionid="";
        //登录成功后返回的 sessionKey
        String sessionKey = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 参数
        StringBuffer params = new StringBuffer();
        try {
            //组装参数
            params.append("appid=" + URLEncoder.encode(Constants.WX_APPLETS_APP_ID, "utf-8"));
            params.append("&");
            params.append("secret="+Constants.WX_APPLETS_APP_SERCERT);
            params.append("&");
            params.append("js_code="+entity.getUsername());
            params.append("&");
            params.append("grant_type=authorization_code");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        // 创建Get请求微信接口获取openId
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/sns/jscode2session" + "?" + params);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                String responseStr= EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:"+responseStr);
                JSONObject jsonObject = JSONObject.parseObject(responseStr);
                openId = jsonObject.getString("openid");
                unionid = jsonObject.getString("unionid");
                sessionKey = jsonObject.getString("session_key");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放请求资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Set<String> codes =new HashSet<>();
        if(StringUtils.isEmpty(openId)){
            throw new ServiceException("获取openId失败");
        }
        //openId="oQUGq5cR1a1X_1_cqJZOC5AOeXHk";
        //根据openid查询登录信息是否存在
        //SysUser sysUser = userService.selectUserByUserName(username);
        WxUser login= wxUserMapper.selectByOpenId(openId);
        //如果为空，则表示是新用户登录，将该用户写入信息
        if(login==null){
            login=new WxUser();
            login.setUserName(entity.getNickname());
            login.setLoginName(entity.getUsername());
/*            if(StringUtils.isEmpty(entity.getTelephone())){
                login.setLoginName(entity.getLoginName());
            }else {
                login.setLoginName(entity.getTelephone());
            }
            login.setAvatar(entity.getAvatar());
            login.setGender(entity.getGender());
            login.setTelephone(entity.getTelephone());*/
            login.setOpenid(openId);
            login.setUnionid(unionid);
            login.setEnabled(1);
            login.setCreatedBy("admin");
            login.setCreatedTime(new Date());
            wxUserMapper.insertWxUser(login);
            //插入用户角色关联信息
            UserRole userRole=new UserRole();
            userRole.setRoleCode(UserRoles.customer.code());
            userRole.setUserId(login.getId());
            UserRole role = userRoleMapper.selectByCode(userRole.getRoleCode());
            if(StringUtils.isEmpty(role)){
                userRole.setRoleId(3L);
            }else {
                userRole.setRoleId(role.getId());
            }
            userRoleMapper.insertUserRole(userRole);
            codes.add(userRole.getRoleCode());
        }else{
            if(login.getEnabled()!=null&&0==login.getEnabled()){
                throw new ServiceException("账号已被冻结，请联系管理员！");
            }
            //唯一id为空就更新
            if(StringUtils.isEmpty(login.getUnionid()) && !StringUtils.isEmpty(unionid)){
                WxUser userInfo1=new WxUser();
                userInfo1.setId(login.getId());
                userInfo1.setUnionid(unionid);
                userInfo1.setLastUpdatedTime(new Date());
                wxUserMapper.updateWxUser(userInfo1);
            }

            //查询登录用户的系统角色
            UserRole userRole = new UserRole();
            userRole.setUserId(login.getId());
            List<UserRole> userRoles = userRoleMapper.selectUserRoleList(userRole);
            for (UserRole role:userRoles){
                codes.add(role.getRoleCode());
            }
           // codes = (Set<String>) userRoles.stream().map(UserRole::getRoleCode).collect(Collectors.toList());
        }
        SysUser sysUser  = new SysUser();
        BeanUtil.copyProperties(login,sysUser);
        //查询用户角色
        //List<String> roleCodes=userRoleService.findUserRoleListByUserId(login.getBusinessId());
        //查询用户权限
        //List<String> permissions=rolePermissionService.findUserPermissionByUserId(login.getBusinessId());
        LoginUser loginUser=new LoginUser();
        loginUser.setUserid(Long.valueOf(login.getId()));
        loginUser.setUsername(login.getOpenid());
        loginUser.setRoles(codes);
        loginUser.setSysUser(sysUser);
        return loginUser;
    }
}
