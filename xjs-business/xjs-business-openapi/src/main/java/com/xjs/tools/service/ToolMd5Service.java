package com.xjs.tools.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.tools.domain.ToolMd5;

/**
 * md5工具service接口
 * @author xiejs
 * @since 2022-05-10
 */
public interface ToolMd5Service extends IService<ToolMd5> {

    /**
     * 加密md5
     * @param str 来源字符串
     * @return 16位和32位md5
     */
    ToolMd5 encryption(String str);

    /**
     * 解密md5
     * @param str md5
     * @return 解密的字符串
     */
    String decrypt(String str);
}
