package com.xjs.copywritingNetwork.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文案网数据实体表
 * @author xiejs
 * @since 2022-02-16
 */
@Data
@TableName("webmagic_copywriting_network")
public class CopyWritingNetwork implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /** 文案标签 */
    private String type;

    /** 文案主题 */
    private String theme;

    /** 文案内容 */
    private String content;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    /**
     * 查询条件的结束时间
     */
    @TableField(exist = false)
    private Date endCreateTime;
}
