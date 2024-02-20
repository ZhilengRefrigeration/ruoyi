package com.ruoyi.common.services.constants;

import com.ruoyi.common.core.constant.IEnum;
import lombok.Getter;

/**
 * @author Alan Scipio
 * created on 2024/2/18
 */
@Getter
public enum SeqType implements IEnum {

    UNIT_CD(1, "UNIT", "单位代码"),

    ITEM_TYPE_CD(2, "ITYPE", "商品类型代码"),

    WHS_CD(3, "WHS", "仓库代码"),

    ;

    private final int code;

    private final String seqDistCd;

    private final String name;

    SeqType(int code, String seqDistCd, String name) {
        this.code = code;
        this.seqDistCd = seqDistCd;
        this.name = name;
    }

}
