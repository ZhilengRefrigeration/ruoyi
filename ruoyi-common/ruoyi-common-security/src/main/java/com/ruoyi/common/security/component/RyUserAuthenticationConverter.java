package com.ruoyi.common.security.component;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.security.domain.LoginUser;

/**
 * 根据checktoken 的结果转化用户信息
 * 
 * @author ruoyi
 */
public class RyUserAuthenticationConverter implements UserAuthenticationConverter
{
    private static final String N_A = "N/A";

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication)
    {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(USERNAME, authentication.getName());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty())
        {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map)
    {
        if (map.containsKey(USERNAME))
        {
            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);

            String username = (String) map.get(SecurityConstants.DETAILS_USERNAME);
            Long id = Convert.toLong(map.get(SecurityConstants.DETAILS_USER_ID));
            Long deptId = Convert.toLong(map.get(SecurityConstants.DETAILS_DEPT_ID));
            LoginUser user = new LoginUser(id, deptId, username, N_A, true, true, true, true, authorities);
            return new UsernamePasswordAuthenticationToken(user, N_A, authorities);
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map)
    {
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String)
        {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection)
        {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(
                    StringUtils.collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }
}
