package com.ruoyi.system.domain.vo;
import com.ruoyi.system.domain.Competition;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CompetitionVo extends Competition {
    @ApiModelProperty(value = "多字段过滤条件", required = false)
    private String word;

    @ApiModelProperty(value = "用户id", required = false)
    private Long userId;

    @ApiModelProperty(value = "短信验证码", required = false)
    private String captcha;
}
