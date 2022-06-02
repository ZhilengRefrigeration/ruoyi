package com.xjs;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author xiejs
 * @since 2022-06-01
 */
public class SentinelHandler {

    public static String handlePort(BlockException e) {
        return "异常了啊！！！！";
    }

}
