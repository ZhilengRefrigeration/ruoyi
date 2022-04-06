package com.xjs.apilog.vo;

import lombok.Data;

/**
 * api日志vo
 * @author xiejs
 * @since 2022-04-06
 */
@Data
public class ApiLogVo {

    /**
     * api名称
     */
    private String apiName;

    /**
     * 数量
     */
    private Long count;

}
