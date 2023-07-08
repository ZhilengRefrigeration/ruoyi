package com.ruoyi.system.domain.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 *
 */
@ApiModel(value = "个人生涯Vo")
@Setter
@Getter
public class PersonalCareerVo {

    private static final long serialVersionUID = 1L;

    /**
     *场均得分
     */
    @ApiModelProperty(value="场均得分",required=false)
    private BigDecimal totalScore;

    /**
     *场均篮板
     */
    @ApiModelProperty(value="场均篮板",required=false)
    private BigDecimal backboard;

    /**
     *场均3分
     */
    @ApiModelProperty(value="场均3分",required=false)
    private BigDecimal threePoints;

    /**
     *场均2分
     */
    @ApiModelProperty(value="场均2分",required=false)
    private BigDecimal twoPoints;

    /**
     *场均罚球
     */
    @ApiModelProperty(value="场均罚球",required=false)
    private BigDecimal penalty;

    /**
     *场均抢断
     */
    @ApiModelProperty(value="场均抢断",required=false)
    private BigDecimal snatch;

    /**
     *场均盖帽
     */
    @ApiModelProperty(value="场均盖帽",required=false)
    private BigDecimal block;

}
