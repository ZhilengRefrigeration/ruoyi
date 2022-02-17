package com.xjs.copywritingNetwork.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.copywritingNetwork.pojo.CopyWritingNetwork;

import java.util.List;

/**
 * 文案网service接口
 *
 * @author xiejs
 * @since 2022-02-16
 */
public interface CopyWritingNetworkService extends IService<CopyWritingNetwork> {
    /**
     * 删除重复数据
     *
     * @return int
     */
    int deleteRepeatData();

    //---------------------------代码生成--------------------------------

    /**
     * 查询文案网列表
     *
     * @param copyWritingNetwork 文案网
     * @return 文案网集合
     */
    List<CopyWritingNetwork> selectCopyWritingNetworkList(CopyWritingNetwork copyWritingNetwork);

    /**
     * 批量删除文案网
     *
     * @param ids 需要删除的文案网主键集合
     * @return 结果
     */
    int deleteCopyWritingNetworkByIds(Long[] ids);

    /**
     * 删除文案网信息
     *
     * @param id 文案网主键
     * @return 结果
     */
    int deleteCopyWritingNetworkById(Long id);
}
