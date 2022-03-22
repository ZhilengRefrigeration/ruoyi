package com.xjs.area.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 区域编码实体类
 *
 * @author xiejs
 * @since 2022-03-22
 */
@Data
@TableName("api_area")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 城市编码
     */
    private String citycode;

    /**
     * 区域编码
     */
    private String adcode;

    /**
     * 行政区名称
     */
    private String name;

    /**
     * 区域中心点
     */
    private String center;

    /**
     * 行政区划级别
     * <br>
     * country:国家
     * <br>
     * province:省份（直辖市会在province和city显示）
     * <br>
     * city:市（直辖市会在province和city显示）
     * <br>
     * district:区县
     * <br>
     * street:街道
     */
    private String level;

    /**
     * 嵌套自身
     */
    @TableField(exist = false)
    private List<Area> districts;

}
