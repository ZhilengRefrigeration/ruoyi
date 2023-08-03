package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 客户-订车对象 f_customer_order
 * 
 * @author ruoyi
 * @date 2023-08-01
 */
@Data
public class CustomerOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId;

    /** 订车时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订车时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 车辆详细 */
    @Excel(name = "车辆详细")
    private String carInfo;

    /** 车辆VIN */
    @Excel(name = "车辆VIN")
    private String carVin;

    /** 车辆状态 */
    @Excel(name = "车辆状态")
    private String carStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date outDate;
}
