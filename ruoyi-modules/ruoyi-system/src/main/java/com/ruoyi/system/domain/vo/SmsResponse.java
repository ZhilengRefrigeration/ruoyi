package com.ruoyi.system.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SmsResponse implements Serializable {
    private int status;
    private String batchNo;
    private String msg;
}
