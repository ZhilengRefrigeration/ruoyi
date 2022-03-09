package com.xjs.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 线程不安全List特点
 *
 * @author xiejs
 * @since 2022-03-07
 */
public class ThreadDemo4 {


    public static void main(String[] args) {

        //创建ArrayList
        //List<String> list = new Vector<>();   //通过Vector解决

        //List<String> list = Collections.synchronizedList(new ArrayList<>());    //Collections工具类解决

        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();       //写时复制技术解决


        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                //像集合中添加内容
                list.add(UUID.randomUUID().toString().substring(0, 5));

                System.out.println(list);

            }, String.valueOf(i)).start();
        }


    }

}
