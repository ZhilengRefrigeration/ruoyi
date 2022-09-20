package com.xjs.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 校验图片相关工具类
 * @author xiejs
 * @since 2022-07-02
 */
public class CheckImagesFormatUtil {
    /**
     * 图片的像素判断
     * @param file 文件
     * @param imageWidth 图片宽度
     * @param imageHeight 图片高度
     * @return true：上传图片宽度和高度都小于等于规定最大值
     * @throws IOException
     */
    public static boolean checkImageElement(File file, int imageWidth, int imageHeight) throws IOException {
        Boolean result = false;
        if (!file.exists()) {
            return false;
        }
        BufferedImage bufferedImage = ImageIO.read(file);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        if (bufferedImage != null && height == imageHeight && width == imageWidth) {
            result = true;
        }
        return result;
    }

    /**
     * 校验图片比例
     * @param file 图片
     * @param imageWidth 宽
     * @param imageHeight 高
     * @return true：符合要求
     * @throws IOException
     */
    public static boolean checkImageScale(File file, int imageWidth, int imageHeight) throws IOException {
        Boolean result = false;
        if (!file.exists()) {
            return false;
        }
        BufferedImage bufferedImage = ImageIO.read(file);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        if (imageHeight != 0 && height != 0) {
            int scale1 = imageHeight / imageWidth;
            int scale2 = height / width;
            if (scale1 == scale2) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 校验图片的大小
     * @param file 文件
     * @param imageSize 图片最大值(KB)
     * @return true：上传图片小于图片的最大值
     */
    public static boolean checkImageSize(File file, Long imageSize) {
        if (!file.exists()) {
            return false;
        }
        Long size = file.length() / 1024; // 图片大小
        if (imageSize == null) {
            imageSize = 5 * 1024L;
        } else {
            imageSize = imageSize * 1024;
        }
        if (size > imageSize) {
            return false;
        }
        if (imageSize == null) {
            return true;
        }
        if (size.intValue() <= imageSize) {
            return true;
        }
        return false;
    }


}
