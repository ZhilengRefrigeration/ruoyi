package com.xjs.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.xjs.validation.group.SelectGroup;
import com.xjs.validation.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xiejs
 * @desc api记录实体类
 * @create 2021-12-31
 */
@TableName("api_record")
@Data
public class ApiRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * api名称
     */
    @Excel(name = "api名称")
    @Size(max = 20, message = "请控制api名称长度在20字符", groups = { SelectGroup.class})
    private String apiName;

    /**
     * api地址
     */
    @Excel(name = "api地址")
    private String apiUrl;

    /**
     * 请求耗费时间
     */
    @Excel(name = "请求耗费时间")
    private Integer requestTime;

    /**
     * api总请求次数
     */
    @Excel(name = "api总请求次数")
    private Long totalCount;

    /**
     * api限制请求次数每天
     */
    @Excel(name = "api限制请求次数每天")
    @NotNull(message = "api每天限制请求次数不能为空",groups = UpdateGroup.class)
    @Max(value = 9999,message = "超过最大值！api每天限制请求次数最大为9999",groups = UpdateGroup.class)
    @Min(value = 0,message = "低于最小值！api每天限制请求次数最小为0",groups = UpdateGroup.class)
    private Long limitCount;

    @Excel(name = "api每天请求次数")
    private Long dayCount;

    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Excel(name = "修改时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
