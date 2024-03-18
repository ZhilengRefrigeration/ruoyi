package com.ruoyi.common.core.annotation;

/**
 * ===========================================
 * Copyright  2024 xiaoyang
 * All rights reserved
 * <p>项 目 名 :RuoYi-Cloud</p>
 * <p>文 件 名 :DictTag</p>
 * <p>描    述 :字典类型</p>
 *
 * @author :xiaoyang
 * @date : 2024/1/12 20:27
 * ============================================
 */
public interface DictTag
{
    /**
     * 绑定键
     *
     * @return 绑定的键值
     */
    String getKey();

    /**
     * 绑定名称
     *
     * @return 绑定的名称
     */
    String getName();
}
