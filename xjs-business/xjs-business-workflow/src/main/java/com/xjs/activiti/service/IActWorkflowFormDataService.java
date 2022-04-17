package com.xjs.activiti.service;


import com.xjs.activiti.domain.ActWorkflowFormData;

import java.util.List;

/**
 * 动态单Service接口
 *
 * @author xiejs
 * @since 2022-04-17 01:49:39
 */
public interface IActWorkflowFormDataService {
    /**
     * 查询动态单
     *
     * @param id 动态单ID
     * @return 动态单
     */
    ActWorkflowFormData selectActWorkflowFormDataById(Long id);

    List<ActWorkflowFormData> selectActWorkflowFormDataByBusinessKey(String businessKey);

    /**
     * 查询动态单列表
     *
     * @param ActWorkflowFormData 动态单
     * @return 动态单集合
     */
    List<ActWorkflowFormData> selectActWorkflowFormDataList(ActWorkflowFormData ActWorkflowFormData);

    /**
     * 新增动态单
     *
     * @param ActWorkflowFormData 动态单
     * @return 结果
     */
    int insertActWorkflowFormData(ActWorkflowFormData ActWorkflowFormData);

    /**
     * 新增动态单集合
     *
     * @param ActWorkflowFormDatas 动态表单集合
     * @return
     */
    int insertActWorkflowFormDatas(List<ActWorkflowFormData> ActWorkflowFormDatas);

    /**
     * 修改动态单
     *
     * @param ActWorkflowFormData 动态单
     * @return 结果
     */
    int updateActWorkflowFormData(ActWorkflowFormData ActWorkflowFormData);

    /**
     * 批量删除动态单
     *
     * @param ids 需要删除的动态单ID
     * @return 结果
     */
    int deleteActWorkflowFormDataByIds(Long[] ids);

    /**
     * 删除动态单信息
     *
     * @param id 动态单ID
     * @return 结果
     */
    int deleteActWorkflowFormDataById(Long id);
}
