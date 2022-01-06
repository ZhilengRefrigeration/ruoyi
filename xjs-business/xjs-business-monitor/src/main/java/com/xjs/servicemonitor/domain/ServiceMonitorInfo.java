package com.xjs.servicemonitor.domain;

import lombok.Data;

/**
 * @author xiejs
 * @desc  业务监控实体类
 * @create 2022-01-02
 */
@Data
public class ServiceMonitorInfo {


    /**
     * jvm版本
     */
    private String jvmVersion;


    /**
     * jre安装路径
     */
    private String jreHoneDir;

    /**
     * jre版本
     */
    private String jreVersion;

    /**
     * jre名称
     */
    private String jreName;


    /**
     * 系统名称
     */
    private String osName;

    /**
     * 系统版本
     */
    private String osVersion;

    /**
     * 当前系统架构
     */
    private String osArch;

    /**
     * 主机名
     */
    private String hostName;
    /**
     * 主机地址
     */
    private String hostAddress;

    /**
     * 当前系统登录名
     */
    private String userName;

    /**
     * 当前用户路径
     */
    private String homeDir;

    /**
     * current 当前目录
     */
    private String currentDir;

    /**
     * 当前用户登录语言
     */
    private String userLanguage;

    /**
     * 当前用户登录区域
     */
    private String userCountry;


    /**
     * 最大jvm内存
     */
    private Long maxMemory;
    /**
     * 已分配内存
     */
    private Long totalMemory;
    /**
     已分配内存中的剩余空间
     */
    private Long freeMemory;

    /**
     最大可用内存
     */
    private Long usableMemory;

}
