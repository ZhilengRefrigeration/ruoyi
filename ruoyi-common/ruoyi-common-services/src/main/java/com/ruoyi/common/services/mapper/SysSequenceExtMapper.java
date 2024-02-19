package com.ruoyi.common.services.mapper;

import com.ruoyi.common.services.domain.vo.SeqVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author Alan Scipio
 * created on 2024/2/18
 */
public interface SysSequenceExtMapper {

    SeqVo selectMaxSeq(@Param("seqDistCd") String seqDistCd);

}
