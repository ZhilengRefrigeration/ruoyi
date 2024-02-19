package com.ruoyi.common.services;

import com.ruoyi.common.services.constants.SeqType;

/**
 * 序列号生成的业务接口
 *
 * @author Alan Scipio
 * created on 2024/2/18
 */
public interface ISysSequenceService {

    /**
     * 获取下一个序列号
     *
     * @param seqDistCd 序列号识别码
     * @param update    是否更新序列号到DB（不更新就相当于预览）
     * @return 下一个序列号
     */
    String getNextSequence(String seqDistCd, boolean update);

    default String getNextSequence(String seqDistCd) {
        return getNextSequence(seqDistCd, true);
    }

    default String getNextSequence(SeqType seqType, boolean update) {
        return getNextSequence(seqType.getSeqDistCd(), update);
    }

    default String getNextSequence(SeqType seqType) {
        return getNextSequence(seqType, true);
    }

}
