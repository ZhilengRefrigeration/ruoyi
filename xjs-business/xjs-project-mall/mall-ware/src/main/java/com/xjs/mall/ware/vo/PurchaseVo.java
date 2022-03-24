package com.xjs.mall.ware.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购信息vo
 * @author xiejs
 * @since 2022-03-24
 */
@Data
public class PurchaseVo {
    @TableId
    private Long id;

    /**
     *用户id
     */
    private Long assigneeId;
    /**
     *用户名
     */
    private String assigneeName;
    /**
     *手机号
     */
    private String phone;

    /**
     * 优先级
     */
    private Integer priority;
    /**
     *采购单状态
     */
    private Integer status;
    /**
     *仓库id
     */
    private Long wareId;
    /**
     *总金额
     */
    private BigDecimal amount;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;


    /**
     * 仓库名称
     */
    private String wareName;
}
