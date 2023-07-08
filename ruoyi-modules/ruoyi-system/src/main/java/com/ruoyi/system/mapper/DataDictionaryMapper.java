package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DataDictionary;
import org.apache.ibatis.annotations.Select;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface DataDictionaryMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public DataDictionary selectDataDictionaryById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param dataDictionary 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<DataDictionary> selectDataDictionaryList(DataDictionary dataDictionary);

    /**
     * 新增【请填写功能名称】
     * 
     * @param dataDictionary 【请填写功能名称】
     * @return 结果
     */
    public int insertDataDictionary(DataDictionary dataDictionary);

    /**
     * 修改【请填写功能名称】
     * 
     * @param dataDictionary 【请填写功能名称】
     * @return 结果
     */
    public int updateDataDictionary(DataDictionary dataDictionary);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteDataDictionaryById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDataDictionaryByIds(Long[] ids);

    @Select("SELECT * FROM data_dictionary  a where a.is_deleted = 0 and a.enabled = 1 " +
            "and a.parent_id in (SELECT id FROM data_dictionary b where is_deleted = 0 and enabled = 1 and b.key = #{parentKey})")
    List<DataDictionary> getChildByParentKey(String parentKey);
}
