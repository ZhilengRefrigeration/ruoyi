package com.ruoyi.common.services;

import com.ruoyi.common.core.exception.NoSuchDataException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.uuid.snowflake.SnowFlakeIdGenerator;
import com.ruoyi.common.services.domain.SysSeqResult;
import com.ruoyi.common.services.domain.vo.SeqGenResult;
import com.ruoyi.common.services.domain.vo.SeqVo;
import com.ruoyi.common.services.mapper.SysSeqResultMapper;
import com.ruoyi.common.services.mapper.SysSequenceExtMapper;
import com.ruoyi.common.services.sequence.ISequenceGenerator;
import com.ruoyi.common.services.sequence.SequenceConfig;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 序列号生成的业务接口
 *
 * @author Alan Scipio
 * created on 2024/2/18
 */
@Service
public class SysSequenceServiceImpl implements ISysSequenceService {

    @Resource
    private SequenceConfig sequenceConfig;
    @Resource
    private SysSequenceExtMapper extMapper;
    @Resource
    private SysSeqResultMapper resultMapper;

    @Override
    public String getNextSequence(String seqDistCd, boolean update) {
        if (StringUtils.isBlank(seqDistCd)) {
            throw new IllegalArgumentException("seqDistCd can not be blank");
        }
        //查询规则和实绩（第一次生成的话实绩为空）
        SeqVo seqVo = extMapper.selectMaxSeq(seqDistCd);
        if (seqVo == null) {
            throw new NoSuchDataException("seqGen", "No sequence rule found by seqDistCd: [" + seqDistCd + "]");
        }
        //检查规则是否启用
        if (!seqVo.isEnable()) {
            return null;
        }
        //获取生成器
        ISequenceGenerator generator;
        if (StringUtils.isNotBlank(seqVo.getGeneratorName())) {
            generator = sequenceConfig.getOrCreateGenerator(seqVo.getGeneratorName());
        } else {
            generator = sequenceConfig.getDefaultGenerator();
        }
        //生成序列号
        SeqGenResult result = generator.nextSequenceGen(seqVo, null);
        //更新记录
        if (update) {
            SysSeqResult updateRecord = result.getUpdateRecord();
            if (result.getSeqId() == null) {
                updateRecord.setSeqId(SnowFlakeIdGenerator.nextIdLong());
                resultMapper.insertSelective(updateRecord);
            } else {
                resultMapper.updateByPrimaryKeySelective(updateRecord);
            }
        }
        return result.getSequenceResult();
    }

}
