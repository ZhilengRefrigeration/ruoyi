package com.xjs.sina.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjs.validation.group.SelectGroup;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 新浪新闻爬虫数据实体类
 *
 * @author xiejs
 * @since 2022-02-15
 */
@TableName("webmagic_sina_news")
@Data
public class SinaNews implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    /**
     * 新闻标题
     */
    @Size(max = 100, message = "请控制新闻标题长度在100字符", groups = {SelectGroup.class})
    private String title;

    /**
     * 新闻分类
     */
    @Size(max = 20, message = "请控制新闻分类长度在20字符", groups = {SelectGroup.class})
    private String category;

    /**
     * 新闻地址
     */
    private String url;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 查询条件的结束时间
     */
    @TableField(exist = false)
    private Date endCreateTime;
}
