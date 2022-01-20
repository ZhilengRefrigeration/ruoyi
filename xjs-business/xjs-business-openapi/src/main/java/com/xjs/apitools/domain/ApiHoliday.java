package com.xjs.apitools.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 节假日api 实体
 *
 * @author xiejs
 * @since 2022-01-17
 */
@Data
public class ApiHoliday implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 节日日期
     */
    private String date;

    /**
     * 节日农历日期
     */
    private String lunarDate;

    /**
     * 节日名称
     */
    private String holidayName;

    /**
     * 距离今日的天数，已经过的节日为负数
     */
    private Integer residueDays;

    /**
     * 是否是农历节日
     */
    private Boolean lunarHoliday;


    /**
     * 最后返回的日期(可能是公历可能是农历)
     */
    private String returnDate;
}
