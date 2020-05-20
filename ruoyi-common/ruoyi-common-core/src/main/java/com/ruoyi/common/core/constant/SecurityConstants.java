package com.ruoyi.common.core.constant;

/**
 * 权限相关通用常量
 * 
 * @author ruoyi
 */
public class SecurityConstants
{
    /**
     * 标志
     */
    public static final String FROM = "from";

    /**
     * 内部
     */
    public static final String FROM_IN = "Y";

    /***
     * 资源服务器默认bean名称
     */
    public static final String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";

    /**
     * 授权token url
     */
    public static final String AUTH_TOKEN = "/oauth/token";
    /**
     * 注销token url
     */
    public static final String TOKEN_LOGOUT = "/token/logout";

    /**
     * 用户ID字段
     */
    public static final String DETAILS_USER_ID = "user_id";

    /**
     * 用户名字段
     */
    public static final String DETAILS_USERNAME = "username";

    /**
     * 用户部门字段
     */
    public static final String DETAILS_DEPT_ID = "dept_id";

    /**
     * 协议字段
     */
    public static final String DETAILS_LICENSE = "license";

    /**
     * 项目的license
     */
    public static final String LICENSE = "made by ruoyi";

    /**
     * sys_oauth_client_details 表的字段，不包括client_id、client_secret
     */
    public static final String CLIENT_FIELDS = "client_id, client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    public static final String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS + " from sys_oauth_client_details";

    /**
     * 按条件client_id 查询
     */
    public static final String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

    /**
     * 默认的查询语句
     */
    public static final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";
}
