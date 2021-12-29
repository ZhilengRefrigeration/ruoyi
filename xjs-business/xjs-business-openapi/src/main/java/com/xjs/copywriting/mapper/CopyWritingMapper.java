package com.xjs.copywriting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.copywriting.domain.CopyWriting;

import java.util.List;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-27
 */
public interface CopyWritingMapper extends BaseMapper<CopyWriting> {
    /**
     * 获取数据库最新一条数据（根据时间分组）
     * @return CopyWriting
     */
    CopyWriting getOneToNew();

    /**
     * 从数据库随机获取一条数据
     * @return CopyWriting
     */
    CopyWriting getOneToRandom();


    //---------------------代码自动生成-------------------------
    /**
     * 查询文案api，通过api获取文案信息
     *
     * @param id 文案api，通过api获取文案信息主键
     * @return 文案api，通过api获取文案信息
     */
    CopyWriting selectCopyWritingById(Long id);

    /**
     * 查询文案api，通过api获取文案信息列表
     *
     * @param copyWriting 文案api，通过api获取文案信息
     * @return 文案api，通过api获取文案信息集合
     */
    List<CopyWriting> selectCopyWritingList(CopyWriting copyWriting);

    /**
     * 删除文案api，通过api获取文案信息
     *
     * @param id 文案api，通过api获取文案信息主键
     * @return 结果
     */
    int deleteCopyWritingById(Long id);

    /**
     * 批量删除文案api，通过api获取文案信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCopyWritingByIds(Long[] ids);

}
