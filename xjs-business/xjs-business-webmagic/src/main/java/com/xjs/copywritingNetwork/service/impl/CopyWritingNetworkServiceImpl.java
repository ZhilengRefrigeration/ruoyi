package com.xjs.copywritingNetwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.copywritingNetwork.mapper.CopyWritingNetworkMapper;
import com.xjs.copywritingNetwork.pojo.CopyWritingNetwork;
import com.xjs.copywritingNetwork.service.CopyWritingNetworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiejs
 * @since 2022-02-16
 */
@Service
public class CopyWritingNetworkServiceImpl extends ServiceImpl<CopyWritingNetworkMapper, CopyWritingNetwork> implements CopyWritingNetworkService {

    @Resource
    private CopyWritingNetworkMapper copyWritingNetworkMapper;

    @Override
    public int deleteRepeatData() {
        return copyWritingNetworkMapper.deleteRepeatData();
    }

    //-----------------------------代码生成-----------------------------

    /**
     * 查询文案网列表
     *
     * @param copyWritingNetwork 文案网
     * @return 文案网
     */
    @Override
    public List<CopyWritingNetwork> selectCopyWritingNetworkList(CopyWritingNetwork copyWritingNetwork) {
        return copyWritingNetworkMapper.selectCopyWritingNetworkList(copyWritingNetwork);
    }

    /**
     * 批量删除文案网
     *
     * @param ids 需要删除的文案网主键
     * @return 结果
     */
    @Override
    public int deleteCopyWritingNetworkByIds(Long[] ids) {
        return copyWritingNetworkMapper.deleteCopyWritingNetworkByIds(ids);
    }

    /**
     * 删除文案网信息
     *
     * @param id 文案网主键
     * @return 结果
     */
    @Override
    public int deleteCopyWritingNetworkById(Long id) {
        return copyWritingNetworkMapper.deleteCopyWritingNetworkById(id);
    }

    @Override
    public List<Object> getType() {
        QueryWrapper<CopyWritingNetwork> wrapper = new QueryWrapper<>();
        wrapper.groupBy("type");
        wrapper.select("type");

        return this.listObjs(wrapper);
    }
}
