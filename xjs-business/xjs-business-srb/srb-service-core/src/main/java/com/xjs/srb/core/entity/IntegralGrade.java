package com.xjs.srb.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 积分等级表
 * </p>
 *
 * @author xiejs
 * @since 2022-02-28
 */
@Getter
@Setter
@TableName("integral_grade")
@ApiModel(value = "IntegralGrade对象", description = "积分等级表")
public class IntegralGrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    @TableId(value = "id")
    private Long id;

    @ApiModelProperty("积分区间开始")
    @NotNull(message = "积分区间开始不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @Min(message = "积分区间不能小于0",value = 0,groups = {UpdateGroup.class, AddGroup.class})
    private Integer integralStart;

    @ApiModelProperty("积分区间结束")
    @NotNull(message = "积分区间结束不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @Min(message = "积分区间不能小于0",value = 0,groups = {UpdateGroup.class, AddGroup.class})
    private Integer integralEnd;

    @ApiModelProperty("借款额度")
    @NotNull(message = "借款额度不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @Min(message = "借款额度不能小于0",value = 0,groups = {UpdateGroup.class, AddGroup.class})
    private BigDecimal borrowAmount;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除(1:已删除，0:未删除)")
    @TableLogic
    private Boolean isDeleted;


}
