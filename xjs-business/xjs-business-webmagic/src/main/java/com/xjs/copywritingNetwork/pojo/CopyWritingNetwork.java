package com.xjs.copywritingNetwork.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.xjs.validation.group.SelectGroup;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 文案网数据实体表
 * @author xiejs
 * @since 2022-02-16
 */
@Data
@TableName("webmagic_copywriting_network")
public class CopyWritingNetwork implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /** 文案标签 */
    @Excel(name = "文案标签")
    @Size(max = 20, message = "请控制文案标签长度在20字符", groups = {SelectGroup.class})
    private String type;

    /** 文案主题 */
    @Excel(name = "文案主题")
    @Size(max = 50, message = "请控制文案主题长度在50字符", groups = {SelectGroup.class})
    private String theme;

    /** 文案内容 */
    @Excel(name = "文案内容")
    @Size(max = 100, message = "请控制文案内容长度在100字符", groups = {SelectGroup.class})
    private String content;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /**
     * 查询条件的结束时间
     */
    @TableField(exist = false)
    private Date endCreateTime;
}
