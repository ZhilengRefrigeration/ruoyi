package com.xjs.zol.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.zol.mapper.ZolNotebookMapper;
import com.xjs.zol.pojo.ZolNotebook;
import com.xjs.zol.service.ZolNotebookService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 爬虫数据中关村笔记本service接口实现
 *
 * @author xiejs
 * @since 2022-04-18
 */
@Service
public class ZolNotebookServiceImpl extends ServiceImpl<ZolNotebookMapper, ZolNotebook> implements ZolNotebookService {

    @Override
    public IPage<ZolNotebook> selectZolPhoneByPage(Page<ZolNotebook> startPageMP, ZolNotebook zolNotebook) {
        String condition = zolNotebook.getCondition();
        LambdaQueryWrapper<ZolNotebook> wr = new LambdaQueryWrapper<>();
        boolean b = Objects.nonNull(zolNotebook.getCreateTime()) && Objects.nonNull(zolNotebook.getEndCreateTime());
        wr.between(b, ZolNotebook::getCreateTime, zolNotebook.getCreateTime(), zolNotebook.getEndCreateTime());
        //通用查询/组合查询
        wr.and(StringUtils.isNotEmpty(condition), obj -> {
            obj.like(ZolNotebook::getNotebookName, condition)
                    .or()
                    .like(ZolNotebook::getDescription, condition);
        });
        //排序规则
        wr.orderByAsc(ZolNotebook::getSort).orderByDesc(ZolNotebook::getId);
        return this.page(startPageMP, wr);
    }
}
