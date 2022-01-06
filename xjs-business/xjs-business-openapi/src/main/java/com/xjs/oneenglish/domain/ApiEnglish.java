package com.xjs.oneenglish.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 英语一言对象 api_english
 *
 * @author xjs
 * @date 2021-12-31
 */
@Data
public class ApiEnglish implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;

    /**
     * 英文
     */
    @Excel(name = "英文")
    private String en;

    /**
     * 中文
     */
    @Excel(name = "中文")
    private String zh;


    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
