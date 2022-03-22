package com.xjs.word.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.core.annotation.Excel;
import com.xjs.entity.BaseEntity;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.SelectGroup;
import com.xjs.validation.group.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 英语单词对象 english_word
 *
 * @author xjs
 * @since 2021-12-29
 */
@Data
@TableName("english_word")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class EnglishWord extends BaseEntity implements Serializable {

    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 英语单词
     */
    @Excel(name = "英语单词")
    @NotBlank(message = "英语单词不能为空", groups = {UpdateGroup.class})
    @Size(max = 20, message = "请控制英语长度在20字符", groups = {UpdateGroup.class, SelectGroup.class})
    private String englishWord;

    /**
     * 对应的中文
     */
    @Excel(name = "中文")
    @NotBlank(message = "中文不能为空", groups = {UpdateGroup.class})
    @Size(max = 10, message = "请控制中文长度在10字符", groups = {UpdateGroup.class,SelectGroup.class})
    private String chineseWord;


    /**
     * 前端传递的内容（可能是中、英文）
     */
    @TableField(exist = false)
    @NotBlank(message = "内容不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "请控制长度在20字符", groups = {AddGroup.class})
    private String content;

    /**
     * 排序1,2,3,4
     */
    @Excel(name = "排序")
    private Integer sort;

    /**
     * 是否收藏 1收藏 2不收藏
     */
    @Excel(name = "是否收藏", readConverterExp = "1=收藏,2=不收藏")
    @NotNull(message = "收藏字段不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer isCollect;

    /**
     * 置顶 1置顶 2不置顶
     */
    @Excel(name = "置顶", readConverterExp = "1=置顶,2=不置顶")
    @NotNull(message = "置顶字段不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer top;

    /**
     * 查看次数
     */
    @Excel(name = "查看次数")
    private Long lookCount;

    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;



}