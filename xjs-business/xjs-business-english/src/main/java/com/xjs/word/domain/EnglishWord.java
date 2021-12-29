package com.xjs.word.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 英语单词对象 english_word
 *
 * @author xjs
 * @date 2021-12-29
 */
@Data
@TableName("english_word")
public class EnglishWord  {

    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 英语单词
     */
    @Excel(name = "英语单词")
    private String englishWord;

    /**
     * 对应的中文
     */
    @Excel(name = "中文")
    private String chineseWord;

    /**
     * 排序1,2,3,4
     */
    @Excel(name = "排序")
    private Integer sort;

    /**
     * 是否收藏 1收藏 2不收藏
     */
    @Excel(name = "是否收藏",readConverterExp = "1=收藏,2=不收藏")
    private Integer isCollect;

    /**
     * 置顶 1置顶 2不置顶
     */
    @Excel(name = "置顶",readConverterExp = "1=置顶,2=不置顶")
    private Integer top;

    /**
     * 查看次数
     */
    @Excel(name = "查看次数")
    private Long lookCount = 0L;

    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    @TableField(exist = false)
    private Date endCreateTime;

}
