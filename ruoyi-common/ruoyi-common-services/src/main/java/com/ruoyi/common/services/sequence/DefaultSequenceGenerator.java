package com.ruoyi.common.services.sequence;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.services.domain.vo.SeqGenResult;
import com.ruoyi.common.services.domain.vo.SeqVo;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Date;

/**
 * 默认的序列号生成器
 *
 * @author Alan Scipio
 * created on 2024/2/19
 */
public class DefaultSequenceGenerator implements ISequenceGenerator{

    @Override
    public SeqGenResult nextSequenceGen(@Nonnull SeqVo param, @Nullable Date date) {
        SeqGenResult result = new SeqGenResult();
        StringBuilder seq = new StringBuilder();
        result.setSeqDistCd(param.getSeqDistCd());

        //前缀
        seq.append(param.getPrefix());
        result.setPrefix(param.getPrefix());
        //分隔符1
        if (param.getSeparator1() != null) {
            seq.append(param.getSeparator1());
            result.setSeparator1(param.getSeparator1());
        }
        //日期
        if (StringUtils.isNotBlank(param.getDateFormat())) {
            if (date == null) {
                date = new Date();
            }
            String dateVal = DateUtils.parseDateToStr(param.getDateFormat(), date);
            seq.append(dateVal);
            result.setDateVal(dateVal);
            //日期值相同，update。否则insert
            if (dateVal.equals(param.getDateVal())) {
                result.setSeqId(param.getSeqId());
            }
        } else {
            //没有日期值，直接根据查询出的seqId判断是insert还是update
            result.setSeqId(param.getSeqId());
        }
        //分隔符2
        if (param.getSeparator2() != null) {
            seq.append(param.getSeparator2());
            result.setSeparator2(param.getSeparator2());
        }
        //数字序号
        int nextSeqNo = (param.getSeqNo() == null ? 0 : param.getSeqNo()) + 1;
        if (param.getMinDigits() != null && param.getMinDigits() > 0) {
            seq.append(String.format("%0" + param.getMinDigits() + "d", nextSeqNo));
        } else {
            seq.append(nextSeqNo);
        }
        result.setSeqNo(nextSeqNo);
        result.setSequenceResult(seq.toString());

        return result;
    }

}
