package com.xjs.article.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.annotation.Excel;
import com.xjs.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 英语文章实体类
 * @author xiejs
 * @since 2022-03-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class EnglishArticle extends BaseEntity {

    /** 主键ID */
    private Long id;

    /** 文章标题 */
    @Excel(name = "文章标题-英文")
    private String titleEnglish;

    /** 文章标题-中文 */
    @Excel(name = "文章标题-中文")
    private String titleChinese;

    /** 文章内容 */
    @Excel(name = "文章内容-英文")
    private String contentEnglish;

    /** 文章内容-中文 */
    @Excel(name = "文章内容-中文")
    private String contentChinese;

    /** 文章图片地址 */
    @Excel(name = "文章图片地址")
    private String pictureUrl;

    /** 创建用户 */
    @Excel(name = "创建用户")
    private String createUser;

    @Excel(name = "创建时间" ,dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Excel(name = "修改时间" ,dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
