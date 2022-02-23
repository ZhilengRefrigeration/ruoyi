
package com.xjs.utils;

import java.io.Closeable;

/**
 * 用于关闭各种连接，缺啥补啥
 * @author xiejs
 * @since  2021-03-05
 **/
public class CloseUtils {

    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }

    public static void close(AutoCloseable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }
}
