package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @author 吴一博
 * @date 2023年08月03日 8:36
 * @Description
 */
@Data
public class CustomerOrderVo extends CustomerVo{

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
    /** 车辆状态 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String outDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划跟进日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String planFollowUpDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际跟进日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String actualFollowUpDate;
}
