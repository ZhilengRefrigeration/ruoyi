package com.xjs._36wallpaper.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 36壁纸网图片数据实体类
 * @author xiejs
 * @since 2022-02-20
 */
@Data
@TableName("webmagic_36wallpaper")
public class _36wallpaper implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId("id")
    private Long id;

    /** 照片存放地址 */
    @Excel(name = "照片存放地址")
    private String pictureUrl;

    /** 照片名称 */
    @Excel(name = "照片名称")
    private String pictureName;

    /** 照片类型 */
    @Excel(name = "照片类型")
    private String type;

    /** 照片标签(多个用 , 分割) */
    @Excel(name = "照片标签(多个用 , 分割)")
    private String label;

    /** 创建时间 */
    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
