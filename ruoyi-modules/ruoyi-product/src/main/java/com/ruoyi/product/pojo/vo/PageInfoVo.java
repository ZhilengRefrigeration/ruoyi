package com.ruoyi.product.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @description: TODO
 * @author杨宗理
 * @date 2023/1/15 16:46
 */
@Data
public class PageInfoVo implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
    //模糊查询数据
    private String keyword;
}
