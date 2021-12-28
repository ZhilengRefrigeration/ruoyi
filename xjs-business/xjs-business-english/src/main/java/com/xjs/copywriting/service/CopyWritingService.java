package com.xjs.copywriting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.copywriting.domain.CopyWriting;

import java.util.List;

/**
 * @author xiejs
 * @desc  文案服务接口
 * @create 2021-12-28
 */
public interface CopyWritingService extends IService<CopyWriting> {

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


    //-------------------------代码自动生成----------------------------------
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
     * 批量删除文案api，通过api获取文案信息
     *
     * @param ids 需要删除的文案api，通过api获取文案信息主键集合
     * @return 结果
     */
    int deleteCopyWritingByIds(Long[] ids);

    /**
     * 删除文案api，通过api获取文案信息信息
     *
     * @param id 文案api，通过api获取文案信息主键
     * @return 结果
     */
    int deleteCopyWritingById(Long id);
}
