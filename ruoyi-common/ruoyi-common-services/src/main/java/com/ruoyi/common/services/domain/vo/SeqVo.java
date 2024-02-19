package com.ruoyi.common.services.domain.vo;

import lombok.Data;

/**
 * @author Alan Scipio
 * created on 2024/2/19
 */
@Data
public class SeqVo {

    /**
     * 规则ID
     */
    private Long ruleId;

    /**
     * 序列号实绩ID
     */
    private Long seqId;

    /**
     * 序列号识别码
     */
    private String seqDistCd;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 分隔符1
     */
    private String separator1;

    /**
     * 日期格式
     */
    private String dateFormat;

    /**
     * 序列号数字部分的最小位数，不足补0
     */
    private Integer minDigits;

    /**
     * 分隔符2
     */
    private String separator2;

    /**
     * 生成器名称(或类全名)，自定义的生成器可忽略前面的规则自行生成
     */
    private String generatorName;

    /**
     * 是否启用
     */
    private Integer enableFlag;

    /**
     * 日期值
     */
    private String dateVal;

    /**
     * 当前序列号
     */
    private Integer seqNo;

    public boolean isEnable() {
        return enableFlag != null && enableFlag == 1;
    }

}
