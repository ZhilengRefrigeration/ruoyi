package com.xjs.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 属性分组
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:16:53
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分组id
     */
    @TableId
    private Long attrGroupId;
    /**
     * 组名
     */
    @NotBlank(message = "组名不能为空", groups = {UpdateGroup.class, AddGroup.class})
    @Size(max = 20, groups = {UpdateGroup.class, AddGroup.class}, message = "图标大小长度大于 20 字符")
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空", groups = {UpdateGroup.class, AddGroup.class})
    @Size(max = 255, groups = {UpdateGroup.class, AddGroup.class}, message = "图标大小长度大于 255 字符")
    private String descript;
    /**
     * 组图标
     */
    @Size(max = 50, groups = {UpdateGroup.class, AddGroup.class}, message = "图标大小长度大于 50 字符")
    private String icon;
    /**
     * 所属分类id
     */
    @NotBlank(message = "所属分类id不能为空", groups = {UpdateGroup.class, AddGroup.class})
    private Long catelogId;

    /**
     * 分类id完整路径
     */
    @TableField(exist = false)
    private List<String> catelogPath;

}
