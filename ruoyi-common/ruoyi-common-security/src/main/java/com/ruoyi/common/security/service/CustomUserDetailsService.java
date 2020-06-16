package com.ruoyi.common.security.service;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.enums.UserStatus;
import com.ruoyi.common.core.exception.BaseException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.domain.LoginUser;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private RemoteUserService remoteUserService;

    public UserDetails loadUserByUsernameAndPassword(String username, String password) {
        // 判断成功后返回用户细节
        R<UserInfo> userResult = remoteUserService.login(username,password);
        checkUser(userResult, username,password);
        return getUserDetails(userResult);
    }
    public void checkUser(R<UserInfo> userResult, String username, String password)
    {
        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData()))
        {
            log.info("登录用户：{} 不存在.", username);
//            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
            throw new OAuth2Exception("账号或密码不正确");//
        }
        else if (UserStatus.DELETED.getCode().equals(userResult.getData().getSysUser().getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new OAuth2Exception("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(userResult.getData().getSysUser().getStatus()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new OAuth2Exception("对不起，您的账号：" + username + " 已停用");
        }
    }
    private UserDetails getUserDetails(R<UserInfo> result)
    {
        UserInfo info = result.getData();
        Set<String> dbAuthsSet = new HashSet<String>();
        if (StringUtils.isNotEmpty(info.getRoles()))
        {
            // 获取角色
            dbAuthsSet.addAll(info.getRoles());
            // 获取权限
            dbAuthsSet.addAll(info.getPermissions());
        }

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();

        return new LoginUser(user.getUserId(), user.getUserName(), user.getPassword(), true, true, true, true,
                authorities);
    }

}
