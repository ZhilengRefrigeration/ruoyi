package com.xjs.sina.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
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
    private String title;

    /**
     * 新闻分类
     */
    private String category;

    /**
     * 新闻地址
     */
    private String url;

    private Date createTime;
}
