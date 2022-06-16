package com.xjs.business.english.domain;

import com.ruoyi.common.core.annotation.Excel;
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
public class EnglishWordDTO implements Serializable {

    /**
     * 英语单词
     */
    private String englishWord;

    /**
     * 对应的中文
     */
    private String chineseWord;

    /**
     * 查看次数
     */
    private Long lookCount;

    /**
     * 创建时间
     */
    private Date createTime;


}
