package com.ruoyi.system.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description //TODO
 * @Author 王辉
 * @Date 2022/11/14 18:34
 */
@Data
public class PageInfoVo implements Serializable {

    //分页
    private Integer pageNum;
    private Integer pageSize;
    //区间查询
    private Integer maxprice;
    private Integer minprice;
    //模糊条件查询
    private String keyWord;
    //根据类型
    private Integer id;
    private Integer pid;















}
