package com.ruoyi.common.log.utils;

import com.ruoyi.common.log.aspect.LogAspect;
import org.fusesource.jansi.Ansi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class JansiUtils {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    public static void info(String text){
        info(text,null);
    }
    public static void debug(String text){
        debug(text,null);
    }
    public static void warn(String text){
        warn(text,null);
    }
    public static void err(String text){
        err(text,null);
    }
    public static void trace(String text){
        trace(text,null);
    }



    public static void info(String text,Object o){
        setColor(BLUE);
        log.info(text,o);
        reset();
    }
    public static void debug(String text,Object o){
        setColor(GREEN);
        log.debug(text,o);
        reset();
    }
    public static void warn(String text,Object o){
        setColor(YELLOW);
        log.warn(text,o);
        reset();
    }
    public static void err(String text,Object o){
        setColor(RED);
        log.error(text,o);
        reset();
    }
    public static void trace(String text,Object o){
        setColor(CYAN);
        log.trace(text,o);
        reset();
    }


    /**
     * 设置颜色
     * @param color
     */
    private static void setColor(Ansi.Color color){
        System.out.print(ansi().eraseScreen().fg(color));
    }

    /**
     * 关闭颜色输出
     */
    private static void reset(){
        System.out.print(ansi().eraseScreen().reset());
    }
}
