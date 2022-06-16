package com.xjs.other;

import lombok.Data;

/**
 * 日志次数vo
 * @author xiejs
 * @since 2022-06-15
 */
@Data
public class LogNumberVo {

    /**
     * 今日次数
     */
    private Long todayNumber;

    /**
     * 总次数
     */
    private Long total;
}
