package com.ruoyi.common.core.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * mybatiesplus实现类
 *
 * @author pinn
 */
public abstract class MybatiesPlusService<M extends BaseMapper<T>, T> extends ServiceImpl implements IService {
}
