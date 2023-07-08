package com.ruoyi.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by liguanghui on 2019/12/26.
 */
public class BasketballTeamRequest implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     *市
     */
    @Setter
    @Getter
    private String cityCode;
    /**
     *球队名称
     */
    @Setter
    @Getter
    @ApiModelProperty(value="球队名称",required=false)
    private String teamName;
}
