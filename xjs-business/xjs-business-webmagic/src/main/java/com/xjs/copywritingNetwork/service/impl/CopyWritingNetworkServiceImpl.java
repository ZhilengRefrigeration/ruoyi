package com.xjs.copywritingNetwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.copywritingNetwork.mapper.CopyWritingNetworkMapper;
import com.xjs.copywritingNetwork.pojo.CopyWritingNetwork;
import com.xjs.copywritingNetwork.service.CopyWritingNetworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

import static com.xjs.consts.RegexConst.NUMBER_REGEX;

/**
 * @author xiejs
 * @since 2022-02-16
 */
@Service
public class CopyWritingNetworkServiceImpl extends ServiceImpl<CopyWritingNetworkMapper, CopyWritingNetwork> implements CopyWritingNetworkService {

    @Resource
    private CopyWritingNetworkMapper copyWritingNetworkMapper;

    private static final Pattern pattern = Pattern.compile(NUMBER_REGEX);

    @Override
    public int deleteRepeatData() {
        return copyWritingNetworkMapper.deleteRepeatData();
    }


    /**
     * 查询文案网列表
     *
     * @param copyWritingNetwork 文案网
     * @return 文案网
     */
    @Override
    public List<CopyWritingNetwork> selectCopyWritingNetworkList(CopyWritingNetwork copyWritingNetwork) {
        List<CopyWritingNetwork> list = copyWritingNetworkMapper.selectCopyWritingNetworkList(copyWritingNetwork);
        list.forEach(data -> {
            data.setContent(this.filterContent(data.getContent()));
        });
        return list;
    }

    @Override
    public List<Object> getType() {
        QueryWrapper<CopyWritingNetwork> wrapper = new QueryWrapper<>();
        wrapper.groupBy("type");
        wrapper.select("type");

        return this.listObjs(wrapper);
    }


    /**
     * 过滤数据
     *
     * @param oldStr 原始数据
     * @return newStr
     */
    private String filterContent(String oldStr) {
        try {
            char index0 = oldStr.charAt(0);
            char index1 = oldStr.charAt(1);
            char index2 = oldStr.charAt(2);
            char index3 = oldStr.charAt(3);
            boolean matches0 = pattern.matcher(String.valueOf(index0)).matches();
            boolean matches1 = pattern.matcher(String.valueOf(index1)).matches();
            boolean matches2 = pattern.matcher(String.valueOf(index2)).matches();
            //  1、
            if (matches0 && index1 == '、') {
                return oldStr.substring(2);
            }
            // 15、
            if (matches0 && matches1 && index2 == '、') {
                return oldStr.substring(3);
            }
            //100、
            if (matches0 && matches1 && matches2 && index3 == '、') {
                return oldStr.substring(4);
            }
            //1.
            if (matches0 && index1 == '.') {
                return oldStr.substring(2);
            }
            //13.
            if (matches0 && matches1 && index2 == '.') {
                return oldStr.substring(3);
            }
            //100.
            if (matches0 && matches1 && matches2 && index3 == '.') {
                return oldStr.substring(4);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return oldStr;
        }
        return oldStr;
    }

    //-----------------------------代码生成-----------------------------


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


}
