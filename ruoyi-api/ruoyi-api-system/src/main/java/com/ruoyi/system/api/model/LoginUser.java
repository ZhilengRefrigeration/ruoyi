package com.ruoyi.system.api.model;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

import com.ruoyi.system.api.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Slf4j
public class LoginUser implements Serializable, UserDetails,CredentialsContainer
{
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 用户名id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 角色列表
     */
    private Set<String> roles;

    /**
     * 用户信息
     */
    private SysUser sysUser;

    private String password;

    private  Set<GrantedAuthority> authorities;

    private  boolean accountNonExpired;

    private  boolean accountNonLocked;

    private  boolean credentialsNonExpired;

    private  boolean enabled;

    public LoginUser() {
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    public LoginUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    public LoginUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.username = username;
        this.password = password;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public LoginUser(Long userid, String username, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.username = username;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        this.userId = userid;
    }

    public LoginUser(Long userid, String username,String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.username = username;
        this.password = password;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        this.userId = userid;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Long getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Long loginTime)
    {
        this.loginTime = loginTime;
    }

    public Long getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Long expireTime)
    {
        this.expireTime = expireTime;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
    }

    public Set<String> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(Set<String> permissions)
    {
        this.permissions = permissions;
    }

    public Set<String> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<String> roles)
    {
        this.roles = roles;
    }

    public SysUser getSysUser()
    {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser)
    {
        this.sysUser = sysUser;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet(new LoginUser.AuthorityComparator());
        Iterator var2 = authorities.iterator();

        while(var2.hasNext()) {
            GrantedAuthority grantedAuthority = (GrantedAuthority)var2.next();
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    @Override
    public boolean equals(Object rhs) {
        return rhs instanceof LoginUser ? this.username.equals(((LoginUser)rhs).username) : false;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");
        if (!this.authorities.isEmpty()) {
            sb.append("Granted Authorities: ");
            boolean first = true;
            Iterator var3 = this.authorities.iterator();

            while(var3.hasNext()) {
                GrantedAuthority auth = (GrantedAuthority)var3.next();
                if (!first) {
                    sb.append(",");
                }

                first = false;
                sb.append(auth);
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }

    public static LoginUser.UserBuilder withUsername(String username) {
        return builder().username(username);
    }

    public static LoginUser.UserBuilder builder() {
        return new LoginUser.UserBuilder();
    }

    /** @deprecated */
    @Deprecated
    public static LoginUser.UserBuilder withDefaultPasswordEncoder() {
        log.warn("User.withDefaultPasswordEncoder() is considered unsafe for production and is only intended for sample applications.");
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        LoginUser.UserBuilder var10000 = builder();
        encoder.getClass();
        return var10000.passwordEncoder(encoder::encode);
    }

    public static LoginUser.UserBuilder withUserDetails(UserDetails userDetails) {
        return withUsername(userDetails.getUsername()).password(userDetails.getPassword()).accountExpired(!userDetails.isAccountNonExpired()).accountLocked(!userDetails.isAccountNonLocked()).authorities(userDetails.getAuthorities()).credentialsExpired(!userDetails.isCredentialsNonExpired()).disabled(!userDetails.isEnabled());
    }

    public static class UserBuilder {
        private String username;
        private String password;
        private List<GrantedAuthority> authorities;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;
        private boolean disabled;
        private Function<String, String> passwordEncoder;

        private UserBuilder() {
            this.passwordEncoder = (password) -> {
                return password;
            };
        }

        public LoginUser.UserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        public LoginUser.UserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        public LoginUser.UserBuilder passwordEncoder(Function<String, String> encoder) {
            Assert.notNull(encoder, "encoder cannot be null");
            this.passwordEncoder = encoder;
            return this;
        }

        public LoginUser.UserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList(roles.length);
            String[] var3 = roles;
            int var4 = roles.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String role = var3[var5];
                Assert.isTrue(!role.startsWith("ROLE_"), () -> {
                    return role + " cannot start with ROLE_ (it is automatically added)";
                });
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }

            return this.authorities((Collection)authorities);
        }

        public LoginUser.UserBuilder authorities(GrantedAuthority... authorities) {
            return this.authorities((Collection)Arrays.asList(authorities));
        }

        public LoginUser.UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList(authorities);
            return this;
        }

        public LoginUser.UserBuilder authorities(String... authorities) {
            return this.authorities((Collection) AuthorityUtils.createAuthorityList(authorities));
        }

        public LoginUser.UserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public LoginUser.UserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public LoginUser.UserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public LoginUser.UserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public UserDetails build() {
            String encodedPassword = (String)this.passwordEncoder.apply(this.password);
            return new User(this.username, encodedPassword, !this.disabled, !this.accountExpired, !this.credentialsExpired, !this.accountLocked, this.authorities);
        }
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = 530L;

        private AuthorityComparator() {
        }

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            } else {
                return g1.getAuthority() == null ? 1 : g1.getAuthority().compareTo(g2.getAuthority());
            }
        }
    }
}
