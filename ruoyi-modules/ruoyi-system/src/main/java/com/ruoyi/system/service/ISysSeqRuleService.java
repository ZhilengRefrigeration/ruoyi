package com.ruoyi.system.service;

import com.ruoyi.common.services.domain.SysSeqRule;

import java.util.List;

/**
 * 序列号生成规则Service接口
 *
 * @author ryas
 * created on 2024-02-20
 */
public interface ISysSeqRuleService {
    /**
     * 查询序列号生成规则
     *
     * @param ruleId 序列号生成规则主键
     * @return 序列号生成规则
     */
    SysSeqRule selectSysSeqRuleByRuleId(Long ruleId);

    /**
     * 查询序列号生成规则列表
     *
     * @param sysSeqRule 序列号生成规则
     * @return 序列号生成规则集合
     */
    List<SysSeqRule> selectSysSeqRuleList(SysSeqRule sysSeqRule);

    /**
     * 新增序列号生成规则
     *
     * @param sysSeqRule 序列号生成规则
     * @return 结果
     */
    int insertSysSeqRule(SysSeqRule sysSeqRule);

    /**
     * 修改序列号生成规则
     *
     * @param sysSeqRule 序列号生成规则
     * @return 结果
     */
    int updateSysSeqRule(SysSeqRule sysSeqRule);

    /**
     * 批量删除序列号生成规则
     *
     * @param ruleIds 需要删除的序列号生成规则主键集合
     * @return 结果
     */
    int deleteSysSeqRuleByRuleIds(Long[] ruleIds);

    /**
     * 删除序列号生成规则信息
     *
     * @param ruleId 序列号生成规则主键
     * @return 结果
     */
    int deleteSysSeqRuleByRuleId(Long ruleId);

    /**
     * 更新序列号生成规则启用状态
     *
     * @param rule 序列号生成规则
     * @return 结果
     */
    int updateEnableFlag(SysSeqRule rule);
}
