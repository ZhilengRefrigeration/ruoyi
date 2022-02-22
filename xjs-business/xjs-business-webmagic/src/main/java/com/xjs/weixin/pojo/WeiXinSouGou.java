package com.xjs.weixin.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;
import com.ruoyi.common.core.annotation.Excel;
import com.xjs.entity.BaseEntity;
import com.xjs.validation.group.SelectGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 微信搜狗爬虫数据实体类
 * @author xiejs
 * @since 2022-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("webmagic_weixin_sougou")
@SearchBean(
        tables = "webmagic_weixin_sougou"
)
public class WeiXinSouGou extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    @DbField("id")
    private Long id;

    /** 文章标题 */
    @Excel(name = "文章标题")
    @DbField("title")
    @Size(max = 100, message = "请控制文章标题长度在100字符", groups = { SelectGroup.class})
    private String title;

    /** 简略的内容 */
    @Excel(name = "简略的内容")
    @DbField("content")
    @Size(max = 100, message = "请控制简略的内容长度在100字符", groups = { SelectGroup.class})
    private String content;

    /** 文章来源 */
    @Excel(name = "文章来源")
    @DbField("source")
    @Size(max = 20, message = "请控制文章来源长度在20字符", groups = { SelectGroup.class})
    private String source;

    /** 文章的链接 */
    @Excel(name = "文章的链接")
    @DbField("url")
    private String url;

    /** 图片的链接 */
    @Excel(name = "图片的链接")
    @DbField("img_url")
    private String imgUrl;

    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    @DbField("create_time")
    private Date createTime;

}
