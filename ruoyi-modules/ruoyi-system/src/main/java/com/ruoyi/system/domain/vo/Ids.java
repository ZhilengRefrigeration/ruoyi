package com.ruoyi.system.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * guocai
 */
@Setter
@Getter
public class Ids {

    private String ids;
    /**
     * 前端传的ids 数组
     */
    private List<Long> idList;
}
