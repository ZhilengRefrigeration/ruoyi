package com.xjs.weixin.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信搜狗爬虫数据实体类
 * @author xiejs
 * @since 2022-02-22
 */
@Data
@Accessors(chain = true)
@TableName("webmagic_weixin_sougou")
public class WeiXinSouGou implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String title;

    /** 简略的内容 */
    @Excel(name = "简略的内容")
    private String content;

    /** 文章来源 */
    @Excel(name = "文章来源")
    private String source;

    /** 文章的链接 */
    @Excel(name = "文章的链接")
    private String url;

    /** 图片的链接 */
    @Excel(name = "图片的链接")
    private String imgUrl;

    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
