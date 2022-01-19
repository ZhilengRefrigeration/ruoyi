package com.xjs.apitools.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * api历史今天实体
 *
 * @author xiejs
 * @since 2022-01-19
 */
@Data
public class ApiHistoryToday implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 历史事件所对应的图片，可能为空
     */
    private String picUrl;

    /**
     * 历史事件的名称
     */
    private String title;

    /**
     * 该历史事件发生所对应的年份
     */
    private String year;

    /**
     * 该历史事件发生所对应的月份
     */
    private String month;

    /**
     * 该历史事件发生所对应的日期
     */
    private String day;

    /**
     * 历史事件的详细介绍，如果type=1，则此字段有返回值，否则不返回
     */
    private String details;

    /**
     * 拼接的具体时间
     */
    private String date;

    public String getDate() {
        String m = this.month;
        try {
            if (Integer.parseInt(month) > 0 && Integer.parseInt(month) < 10) {
                m = "0" + this.month;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.year + "-" + m + "-" + this.day;

    }


}
