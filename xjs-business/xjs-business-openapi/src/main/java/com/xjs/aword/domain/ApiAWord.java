package com.xjs.aword.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.xjs.validation.group.SelectGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * api天行每日一句实体类
 * @author xiejs
 * @since 2022-01-08
 */
@Data
@TableName("api_a_word")
@Accessors(chain = true)
public class ApiAWord implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId
    private Long id;

    /** 数据id */
    @Excel(name = "数据id")
    private Long dataId;

    /** 句子内容 */
    @Excel(name = "句子内容")
    @Size(max = 100, message = "请控制句子内容长度在100字符", groups = { SelectGroup.class})
    private String content;

    /** 来源 */
    @Excel(name = "来源")
    @Size(max = 50, message = "请控制来源长度在50字符", groups = { SelectGroup.class})
    private String source;

    /** 释义 */
    @Excel(name = "释义")
    @Size(max = 100, message = "请控制释义长度在100字符", groups = { SelectGroup.class})
    private String note;

    /** 音频地址 */
    @Excel(name = "音频地址")
    private String tts;

    /** 分享图片地址 */
    @Excel(name = "分享图片地址")
    private String imgurl;

    /** 该句子产生时间 */
    @Excel(name = "该句子产生时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 创建时间 */
    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
