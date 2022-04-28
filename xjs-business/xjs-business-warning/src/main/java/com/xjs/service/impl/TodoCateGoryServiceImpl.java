package com.xjs.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.domain.todo.TodoCategory;
import com.xjs.exception.BusinessException;
import com.xjs.mapper.TodoCategoryMapper;
import com.xjs.service.TodoCateGoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 待办事项service接口实现
 *
 * @author xiejs
 * @since 2022-04-28
 */
@Service
public class TodoCateGoryServiceImpl extends ServiceImpl<TodoCategoryMapper, TodoCategory> implements TodoCateGoryService {

    @Override
    @Transactional
    public boolean save(TodoCategory entity) {
        this.hasExist(entity);
        return super.save(entity);
    }

    @Override
    @Transactional
    public boolean updateById(TodoCategory entity) {
        //顺序不能乱
        boolean b = super.updateById(entity);
        this.hasExist(entity);
        return b;
    }


    /**
     * 检查数据库是否存在该数据
     *
     * @param entity 实体类
     */
    private void hasExist(TodoCategory entity) {
        LambdaQueryWrapper<TodoCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TodoCategory::getName, entity.getName());
        List<TodoCategory> categoryList = super.list(wrapper);
        //根据id判断是添加还是修改
        if (entity.getId() != null) {
            if (CollUtil.isNotEmpty(categoryList) && categoryList.size() > 1) {
                throw new BusinessException("已存在" + entity.getName());
            }
        } else {
            if (CollUtil.isNotEmpty(categoryList)) {
                throw new BusinessException("已存在" + entity.getName());
            }
        }
    }


}
