package com.ruoyi.common.services.domain.vo;

import com.ruoyi.common.services.domain.SysSeqResult;
import lombok.Data;

/**
 * @author Alan Scipio
 * created on 2024/2/19
 */
@Data
public class SeqGenResult {

    /**
     * 序列号实绩ID
     */
    private Long seqId;

    /**
     * 序列号识别码
     */
    private String seqDistCd;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 分隔符1
     */
    private String separator1;

    /**
     * 日期值
     */
    private String dateVal;

    /**
     * 分隔符2
     */
    private String separator2;

    /**
     * 当前序列号
     */
    private Integer seqNo;

    /**
     * 完整生成的序列号结果
     */
    private String sequenceResult;

    public SysSeqResult getUpdateRecord() {
        SysSeqResult result = new SysSeqResult();
        result.setSeqId(seqId);
        result.setSeqDistCd(seqDistCd);
        result.setPrefix(prefix);
        result.setSeparator1(separator1);
        result.setDateVal(dateVal);
        result.setSeparator2(separator2);
        result.setSeqNo(seqNo);
        return result;
    }

}
