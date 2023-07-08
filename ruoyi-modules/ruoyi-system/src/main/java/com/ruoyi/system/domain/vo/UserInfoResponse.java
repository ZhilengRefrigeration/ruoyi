package com.ruoyi.system.domain.vo;
import com.ruoyi.system.domain.WxUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by jackma on 2020/5/25.
 */
@ApiModel(value="个人中心-用户信息")
public class UserInfoResponse extends WxUser {
    private static final long serialVersionUID = 1L;

    /**
     *年龄
     */
    @Setter
    @Getter
    @ApiModelProperty(value="年龄",required=false)
    private int age;

    /**
     *角色名称
     */
    @Setter
    @Getter
    @ApiModelProperty(value="角色名称",required=false)
    private String roleName;

    /**
     *球队位置-中文
     */
    @Setter
    @Getter
    @ApiModelProperty(value="球队位置-中文",required=false)
    private List<String> teamPositionName;

    /**
     *标签名称-中文
     */
    @Setter
    @Getter
    @ApiModelProperty(value="标签名称-中文",required=false)
    private List<String> tagName;
    @Setter
    @Getter
    @ApiModelProperty(value="角色codes",required=false)
    private List<String> roleCodes;

    /**
     * 个人生涯
     */
    @Setter
    @Getter
    @ApiModelProperty(value="个人生涯",required=false)
    private PersonalCareerVo personalCareerVo;

}
