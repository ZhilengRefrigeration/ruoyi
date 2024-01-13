package com.ruoyi.system.api.emun;

import com.ruoyi.common.core.annotation.DictTag;

/**
 * ===========================================
 * Copyright  2024 xiaoyang
 * All rights reserved
 * <p>项 目 名 :RuoYi-Cloud</p>
 * <p>文 件 名 :EnableStatusEnum</p>
 * <p>描    述 :启用状态枚举</p>
 *
 * @author :xiaoyang
 * @date : 2024/1/12 20:26
 * ============================================
 */
public enum EnableStatusEnum implements DictTag
{
    /**
     * 正常
     */
    enable("0", "正常"),
    /**
     * 停用
     */
    disable("1", "停用");

    private String code;

    private String name;
    ;

    EnableStatusEnum(String code, String name)
    {
        this.code = code;
        this.name = name;
    }
    @Override
    public String getKey()
    {
        return code;
    }
    /**
     * 获取名称
     *
     * @return
     */
    @Override
    public String getName()
    {
        return name;
    }
    /**
     * 获取编码
     *
     * @return
     */
    public String getCode()
    {
        return code;
    }

    /**
     * 获取键(集成字典类型)
     *
     * @return
     */

}
