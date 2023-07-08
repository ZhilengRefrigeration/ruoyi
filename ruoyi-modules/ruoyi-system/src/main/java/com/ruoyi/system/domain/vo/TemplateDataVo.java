package com.ruoyi.system.domain.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
 * 设置推送的文字和颜色
 * */
@Builder
@Data
@NoArgsConstructor
public class TemplateDataVo implements Serializable {
    //字段值例如：keyword1：订单类型，keyword2：下单金额，keyword3：配送地址，keyword4：取件地址，keyword5备注
    private String value;//依次排下去
    private String color;
    public TemplateDataVo(String value) {
        this.value = value;
    }
    public TemplateDataVo(String value, String color) {
        this.value = value;
        this.color = color;
    }
}
