package com.ruoyi.system.api.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Set;

/**
 * 角色表 sys_role
 * 
 * @author ruoyi
 */
@Data
public class RoleAndPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色集合
     */
    private Set<String> roles ;

    /**
     * 权限集合
     */
    private Set<String> permissions ;

}
