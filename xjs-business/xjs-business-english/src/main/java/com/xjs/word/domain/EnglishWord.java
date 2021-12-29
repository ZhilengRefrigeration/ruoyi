package com.xjs.word.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 英语单词对象 english_word
 *
 * @author xjs
 * @date 2021-12-29
 */
public class EnglishWord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
    private Long id;

    /**
     * 英语单词
     */
    @Excel(name = "英语单词")
    private String englishWord;

    /**
     * 对应的中文
     */
    @Excel(name = "对应的中文")
    private String chineseWord;

    /**
     * 排序1,2,3,4
     */
    @Excel(name = "排序1,2,3,4")
    private Integer sort;

    /**
     * 是否收藏 1收藏 2不收藏
     */
    @Excel(name = "是否收藏 1收藏 2不收藏")
    private Integer isCollect;

    /**
     * 置顶 1置顶 2不置顶
     */
    @Excel(name = "置顶 1置顶 2不置顶")
    private Integer top;

    /**
     * 查看次数
     */
    @Excel(name = "查看次数")
    private Long lookCount;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setChineseWord(String chineseWord) {
        this.chineseWord = chineseWord;
    }

    public String getChineseWord() {
        return chineseWord;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setIsCollect(Integer isCollect) {
        this.isCollect = isCollect;
    }

    public Integer getIsCollect() {
        return isCollect;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getTop() {
        return top;
    }

    public void setLookCount(Long lookCount) {
        this.lookCount = lookCount;
    }

    public Long getLookCount() {
        return lookCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("englishWord", getEnglishWord())
                .append("chineseWord", getChineseWord())
                .append("sort", getSort())
                .append("isCollect", getIsCollect())
                .append("top", getTop())
                .append("lookCount", getLookCount())
                .append("createTime", getCreateTime())
                .toString();
    }
}
