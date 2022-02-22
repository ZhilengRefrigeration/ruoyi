package com.xjs.oneenglish.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import com.xjs.validation.group.SelectGroup;
import lombok.Data;

import javax.validation.constraints.Size;
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
    @Size(max = 100, message = "请控制英文长度在100字符", groups = { SelectGroup.class})
    private String en;

    /**
     * 中文
     */
    @Excel(name = "中文")
    @Size(max = 100, message = "请控制中文长度在100字符", groups = { SelectGroup.class})
    private String zh;


    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
