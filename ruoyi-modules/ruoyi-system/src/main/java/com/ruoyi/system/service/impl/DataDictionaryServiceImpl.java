package com.ruoyi.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DataDictionaryMapper;
import com.ruoyi.system.domain.DataDictionary;
import com.ruoyi.system.service.IDataDictionaryService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Service
public class DataDictionaryServiceImpl implements IDataDictionaryService 
{
    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public DataDictionary selectDataDictionaryById(Long id)
    {
        return dataDictionaryMapper.selectDataDictionaryById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param dataDictionary 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<DataDictionary> selectDataDictionaryList(DataDictionary dataDictionary)
    {
        return dataDictionaryMapper.selectDataDictionaryList(dataDictionary);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param dataDictionary 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertDataDictionary(DataDictionary dataDictionary)
    {
        return dataDictionaryMapper.insertDataDictionary(dataDictionary);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param dataDictionary 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateDataDictionary(DataDictionary dataDictionary)
    {
        return dataDictionaryMapper.updateDataDictionary(dataDictionary);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDataDictionaryByIds(Long[] ids)
    {
        return dataDictionaryMapper.deleteDataDictionaryByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDataDictionaryById(Long id)
    {
        return dataDictionaryMapper.deleteDataDictionaryById(id);
    }

    @Override
    public Map<String,String> getChildByParentKey(String parentKey) {
        Map<String,String> dataMap = new HashMap();
        List<DataDictionary> dictionaryList = dataDictionaryMapper.getChildByParentKey(parentKey);
        for(DataDictionary d:dictionaryList){
            dataMap.put(d.getKey(), d.getName());
        }
        return dataMap;
    }
}
