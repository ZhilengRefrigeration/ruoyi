package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.system.domain.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author 吴一博
 * @date 2023年05月09日 15:48
 * @Description
 */
@Setter
@Getter
public class CustomerVo extends Customer {
    @Excel(name = "跟进次数")
    private String followUpTimes;
    @Excel(name = "最新跟进日期")
    private String followUpLastDate;
    @Excel(name = "最新跟进级别")
    private String followUpLastLevel;
    @Excel(name = "建议下次跟进日期")
    private String proposalNextFollowDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "跟进超期", width = 30, dateFormat = "yyyy-MM-dd")
    private String followUpOverdueDate;
}
