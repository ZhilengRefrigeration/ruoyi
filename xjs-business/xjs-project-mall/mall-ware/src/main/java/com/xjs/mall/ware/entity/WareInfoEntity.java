package com.xjs.mall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 仓库信息
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 09:56:19
 */
@Data
@TableName("wms_ware_info")
public class WareInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 仓库名
     */
    @NotBlank(message = "仓库名不能为空", groups = {UpdateGroup.class, AddGroup.class})
    @Size(max = 20, message = "请控制仓库名长度在20字符", groups = {UpdateGroup.class, AddGroup.class})
    private String name;
    /**
     * 仓库地址
     */
    @NotBlank(message = "仓库地址不能为空", groups = {UpdateGroup.class, AddGroup.class})
    @Size(max = 100, message = "请控制仓库地址长度在100字符", groups = {UpdateGroup.class, AddGroup.class})
    private String address;
    /**
     * 区域编码
     */
    @NotNull(message = "区域编码不能为空", groups = {UpdateGroup.class, AddGroup.class})
    @Max(message = "区域编码在六位数", groups = {UpdateGroup.class, AddGroup.class}, value = 999999)
    @Min(message = "区域编码在六位数", groups = {UpdateGroup.class, AddGroup.class}, value = 100000)
    private Integer areacode;

}
