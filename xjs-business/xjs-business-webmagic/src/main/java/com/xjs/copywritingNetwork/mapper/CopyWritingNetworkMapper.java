package com.xjs.copywritingNetwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.copywritingNetwork.pojo.CopyWritingNetwork;

import java.util.List;

/**
 * 文案网mapper
 * @author xiejs
 * @since 2022-02-16
 */
public interface CopyWritingNetworkMapper extends BaseMapper<CopyWritingNetwork> {

    /**
     * 删除重复数据
     * @return int
     */
    int deleteRepeatData();


    //------------------------------代码生成-----------------------------------

    /**
     * 查询文案网列表
     *
     * @param copyWritingNetwork 文案网
     * @return 文案网集合
     */
    List<CopyWritingNetwork> selectCopyWritingNetworkList(CopyWritingNetwork copyWritingNetwork);

    /**
     * 删除文案网
     *
     * @param id 文案网主键
     * @return 结果
     */
    public int deleteCopyWritingNetworkById(Long id);

    /**
     * 批量删除文案网
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCopyWritingNetworkByIds(Long[] ids);

    /**
     * 获取文案的标签
     * @return list
     */
    List<String> getType();
}
