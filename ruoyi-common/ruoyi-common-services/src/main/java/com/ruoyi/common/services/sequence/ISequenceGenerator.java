package com.ruoyi.common.services.sequence;

import com.ruoyi.common.services.domain.vo.SeqGenResult;
import com.ruoyi.common.services.domain.vo.SeqVo;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Date;

/**
 * 序列号生成的实现接口
 *
 * @author Alan Scipio
 * created on 2024/2/18
 */
public interface ISequenceGenerator {

    /**
     * 获取下一个序列号
     *
     * @param param 规则参数
     * @param date  给定的日期（为空则使用当前日期）
     */
    SeqGenResult nextSequenceGen(@Nonnull SeqVo param, @Nullable Date date);

}
