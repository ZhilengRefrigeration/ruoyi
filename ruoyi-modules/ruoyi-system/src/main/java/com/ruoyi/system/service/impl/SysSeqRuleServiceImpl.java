package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.services.domain.SysSeqRule;
import com.ruoyi.common.services.mapper.SysSeqRuleDynamicSqlSupport;
import com.ruoyi.common.services.mapper.SysSeqRuleMapper;
import com.ruoyi.system.service.ISysSeqRuleService;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 序列号生成规则Service业务层处理
 *
 * @author ryas
 * created on 2024-02-20
 */
@Service
public class SysSeqRuleServiceImpl implements ISysSeqRuleService {
    @Autowired
    private SysSeqRuleMapper sysSeqRuleMapper;

    /**
     * 查询序列号生成规则
     *
     * @param ruleId 序列号生成规则主键
     * @return 序列号生成规则
     */
    @Override
    public SysSeqRule selectSysSeqRuleByRuleId(Long ruleId) {
        Optional<SysSeqRule> result = sysSeqRuleMapper.selectOne(dsl -> dsl.where(SysSeqRuleDynamicSqlSupport.ruleId, SqlBuilder.isEqualTo(ruleId)));
        return result.orElse(null);
    }

    /**
     * 查询序列号生成规则列表
     *
     * @param sysSeqRule 序列号生成规则
     * @return 序列号生成规则
     */
    @Override
    public List<SysSeqRule> selectSysSeqRuleList(SysSeqRule sysSeqRule) {
        if (StringUtils.isNotBlank(sysSeqRule.getRuleName()) || StringUtils.isNotBlank(sysSeqRule.getSeqDistCd())) {
            //条件查询
            SelectStatementProvider provider = SqlBuilder.select(SysSeqRuleMapper.selectList)
                    .from(SysSeqRuleDynamicSqlSupport.sysSeqRule)
                    .where(SysSeqRuleDynamicSqlSupport.seqDistCd, SqlBuilder.isEqualToWhenPresent(sysSeqRule.getSeqDistCd()))
                    .and(SysSeqRuleDynamicSqlSupport.ruleName, SqlBuilder.isLikeWhenPresent(sysSeqRule.getRuleName() == null ? null : "%" + sysSeqRule.getRuleName() + "%"))
                    .build()
                    .render(RenderingStrategies.MYBATIS3);
            return sysSeqRuleMapper.selectMany(provider);
        } else {
            //全部查询
            return sysSeqRuleMapper.select(SelectDSLCompleter.allRows());
        }
    }

    /**
     * 新增序列号生成规则
     *
     * @param sysSeqRule 序列号生成规则
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysSeqRule(SysSeqRule sysSeqRule) {
        return sysSeqRuleMapper.insertSelective(sysSeqRule);
    }

    /**
     * 修改序列号生成规则
     *
     * @param sysSeqRule 序列号生成规则
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysSeqRule(SysSeqRule sysSeqRule) {
        return sysSeqRuleMapper.updateByPrimaryKeySelective(sysSeqRule);
    }

    /**
     * 批量删除序列号生成规则
     *
     * @param ruleIds 需要删除的序列号生成规则主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysSeqRuleByRuleIds(Long[] ruleIds) {
        DeleteStatementProvider provider = SqlBuilder.deleteFrom(SysSeqRuleDynamicSqlSupport.sysSeqRule)
                .where(SysSeqRuleDynamicSqlSupport.ruleId, SqlBuilder.isIn(ruleIds))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return sysSeqRuleMapper.delete(provider);
    }

    /**
     * 删除序列号生成规则信息
     *
     * @param ruleId 序列号生成规则主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysSeqRuleByRuleId(Long ruleId) {
        return sysSeqRuleMapper.deleteByPrimaryKey(ruleId);
    }

    /**
     * 更新序列号生成规则启用状态
     *
     * @param rule 序列号生成规则
     * @return 结果
     */
    @Transactional
    @Override
    public int updateEnableFlag(SysSeqRule rule) {
        SysSeqRule updateRule = new SysSeqRule();
        updateRule.setRuleId(rule.getRuleId());
        updateRule.setEnableFlag(rule.getEnableFlag());
        return sysSeqRuleMapper.updateByPrimaryKeySelective(updateRule);
    }
}
