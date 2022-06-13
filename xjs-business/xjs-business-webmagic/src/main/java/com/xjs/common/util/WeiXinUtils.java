package com.xjs.common.util;

import java.io.*;

/**
 * 抽出相同的方法（微信爬虫）
 * @author xiejs
 * @since 2022-06-13
 */
public class WeiXinUtils {

    public static String filterTitle(String title) {
        //过滤title字段
        title = title.replace(" ", "");
        //替换\ 防止报错
        if (title.contains("/")) {
            title = title.replace("/", "-");
        }
        if (title.contains("\\")) {
            title = title.replace("\\", "-");
        }
        if (title.contains(":")) {
            title = title.replace(":", "-");
        }
        if (title.contains("*")) {
            title = title.replace("*", "-");
        }
        if (title.contains("?")) {
            title = title.replace("?", "-");
        }
        if (title.contains("\"")) {
            title = title.replace("\"", "-");
        }
        if (title.contains("<")) {
            title = title.replace("<", "-");
        }
        if (title.contains(">")) {
            title = title.replace(">", "-");
        }
        if (title.contains("|")) {
            title = title.replace("|", "-");
        }
        return title;
    }

    /**
     * 链接url下载图片
     *
     * @param inputStream 输入流
     * @param path        磁盘地址
     * @param fileName    文件名称
     * @param title       标题名称
     * @param appendPath  拼接的地址
     */
    public static void downloadPicture(InputStream inputStream, String path, String fileName, String title,String appendPath) {

        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            //如果文件夹不存在则创建
            File file = new File(appendPath);

            if (!file.exists()) {
                boolean mkdirs = file.mkdirs();
            }

            String absolutePath = file.getAbsolutePath();
            String absolute = absolutePath + File.separator + fileName;

            FileOutputStream f = new FileOutputStream(absolute);

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] bf = new byte[1024];
            int length;

            while ((length = dataInputStream.read(bf)) > 0) {
                out.write(bf, 0, length);
            }

            f.write(out.toByteArray());
            dataInputStream.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
