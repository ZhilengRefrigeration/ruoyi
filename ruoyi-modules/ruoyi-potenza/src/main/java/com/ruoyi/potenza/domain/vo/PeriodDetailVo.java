package com.ruoyi.potenza.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 木子
 * @version 1.0
 * @description: TODO
 * @date 2023/1/15 20:39
 */
@Data
public class PeriodDetailVo implements Serializable {
    private Double borrowerMoney;

    private Integer wayId;

    private Integer periodsId;
}
