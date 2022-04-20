package com.ruoyi.common.core.utils.file;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 文件类型工具类
 *
 * @author ruoyi
 */
public class FileTypeUtils
{
    /**
     * 获取文件类型
     * <p>
     * 例如: ruoyi.txt, 返回: txt
     * 
     * @param file 文件名
     * @return 后缀（不含".")
     */
    public static String getFileType(File file)
    {
        if (null == file)
        {
            return StringUtils.EMPTY;
        }
        return getFileType(file.getName());
    }

    /**
     * 获取文件类型
     * <p>
     * 例如: ruoyi.txt, 返回: txt
     *
     * @param fileName 文件名
     * @return 后缀（不含".")
     */
    public static String getFileType(String fileName)
    {
        int separatorIndex = fileName.lastIndexOf(".");
        if (separatorIndex < 0)
        {
            return "";
        }
        return fileName.substring(separatorIndex + 1).toLowerCase();
    }

    /**
     * 获取文件类型
     * 
     * @param photoByte 文件字节码
     * @return 后缀（不含".")
     */
    public static String getFileExtendName(byte[] photoByte)
    {
        String strFileExtendName = "JPG";
        int i71 = 71;
        int i73 = 73;
        int i2 = 2;
        int i70 = 70;
        int i3 = 3;
        int i56 = 56;
        int i5 = 5;
        int i97 = 97;
        int i4 = 4;
        int i55 = 55;
        int i57 = 57;
        int i6 = 6;
        int i74 = 74;
        int i7 = 7;
        int i8 = 8;
        int i9 = 9;
        int i66 = 66;
        int i77 = 77;
        int i80 = 80;
        int i78 = 78;
        if ((photoByte[0] == i71) && (photoByte[1] == i73) && (photoByte[i2] == i70) && (photoByte[i3] == i56) && (photoByte[i5] == i97)) {
            if ((photoByte[i4] == i55) || (photoByte[i4] == i57)) {
                strFileExtendName = "GIF";
            }
        }
        else if ((photoByte[i6] == i74) && (photoByte[i7] == i70) && (photoByte[i8] == i73) && (photoByte[i9] == i70))
        {
            strFileExtendName = "JPG";
        }
        else if ((photoByte[0] == i66) && (photoByte[1] == i77))
        {
            strFileExtendName = "BMP";
        }
        else if ((photoByte[1] == i80) && (photoByte[i2] == i78) && (photoByte[i3] == i71))
        {
            strFileExtendName = "PNG";
        }
        return strFileExtendName;
    }
}
