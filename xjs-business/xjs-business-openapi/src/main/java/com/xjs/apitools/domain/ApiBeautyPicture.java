package com.xjs.apitools.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * api mm图片实体
 *
 * @author xiejs
 * @since 2022-01-19
 */
@Data
@TableName("api_beauty_picture")
public class ApiBeautyPicture implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;


    /**
     * 福利图片链接
     */
    @TableField("url")
    private String imageUrl;


    /**
     * 福利图片尺寸
     */
    @TableField("size")
    private String imageSize;


    /**
     * 福利图片文件大小
     */
    @TableField("length")
    private String imageFileLength;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
