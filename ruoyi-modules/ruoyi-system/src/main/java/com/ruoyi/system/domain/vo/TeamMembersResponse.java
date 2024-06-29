package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.TeamMembers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <B>系统名称：future篮球后台系统系统</B><BR>
 * <B>模块名称：BASKETBALL-DOMAIN</B><BR>
 * <B>中文类名：球队人员表 实体类</B><BR>
 * <B>概要说明：球队人员表 实体类</B><BR>
 * <B>@version：v1.0</B><BR>
 * <B>版本 修改人 备注</B><BR>
 *
 * @author : gc
 * @date : 2020年08月06日
 */
@ApiModel(value = "参与球队人员数据")
@Setter
@Getter
public class TeamMembersResponse extends TeamMembers {

    private static final long serialVersionUID = 1L;

    /**
     *用户名称
     */
    @ApiModelProperty(value="用户名称",required=false)
    private String userName;
    /**
     *用户CODE
     */
    @ApiModelProperty(value="用户CODE",required=false)
    private String userCode;
    /**
     *登录名
     */
    @ApiModelProperty(value="登录名",required=false)
    private String loginName;

    /**
     *角色
     */
    @ApiModelProperty(value="角色",required=false)
    private String role;
    /**
     *头像
     */
    @ApiModelProperty(value="头像",required=false)
    private String avatar;
    /**
     *性别
     */
    @ApiModelProperty(value="性别",required=false)
    private String gender;
    /**
     *手机号
     */
    @ApiModelProperty(value="手机号",required=false)
    private String telephone;
}