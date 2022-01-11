package com.xjs.topsearch.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信热搜榜实体
 * @author xiejs
 * @since 2022-01-11
 */
@Data
@TableName("api_topsearch_wechat")
public class ApiTopsearchWechat implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId("id")
    private Long id;

    /** 话题 */
    @Excel(name = "话题")
    private String word;


    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
