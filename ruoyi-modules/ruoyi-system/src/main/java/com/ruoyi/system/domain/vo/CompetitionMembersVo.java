package com.ruoyi.system.domain.vo;
import com.ruoyi.system.domain.CompetitionMembers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <B>系统名称：future篮球后台系统系统</B><BR>
 * <B>模块名称：BASKETBALL-DOMAIN</B><BR>
 * <B>中文类名：比赛参与人员表 实体类</B><BR>
 * <B>概要说明：比赛参与人员表 实体类</B><BR>
 * <B>@version：v1.0</B><BR>
 * <B>版本 修改人 备注</B><BR>
 *
 * @author : gc
 * @date : 2020年08月07日
 */
@ApiModel(value = "比赛参与人员Vo")
@Setter
@Getter
public class CompetitionMembersVo extends CompetitionMembers {

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

    @ApiModelProperty(value = "短信验证码", required = false)
    private String captcha;

    @ApiModelProperty(value="个人生涯",required=false)
    private PersonalCareerVo personalCareerVo;
    @ApiModelProperty(value="赛会个人得分",required=false)
    private CompetitionMembersScoreVo competitionMemberScore;
}
