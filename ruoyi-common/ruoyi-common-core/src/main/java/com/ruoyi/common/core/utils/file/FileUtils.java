package com.ruoyi.common.core.utils.file;

import com.ruoyi.common.core.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 文件处理工具类
 *
 * @author ruoyi
 */
public class FileUtils {
    /**
     * 字符常量：斜杠 {@code '/'}
     */
    public static final char SLASH = '/';

    /**
     * 字符常量：反斜杠 {@code '\\'}
     */
    public static final char BACKSLASH = '\\';

    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-|.\\u4e00-\\u9fa5]+";

    /**
     * 输出指定文件的byte数组
     *
     * @param filePath 文件路径
     * @param os       输出流
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException {
        FileInputStream fis = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件
     *
     * @param filePath 文件
     */
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            flag = file.delete();
        }
        return flag;
    }

    /**
     * 文件名称验证
     *
     * @param filename 文件名称
     * @return true 正常 false 非法
     */
    public static boolean isValidFilename(String filename) {
        return filename.matches(FILENAME_PATTERN);
    }

    /**
     * 检查文件是否可下载
     *
     * @param resource 需要下载的文件
     * @return true 正常 false 非法
     */
    public static boolean checkAllowDownload(String resource) {
        // 禁止目录上跳级别
        if (StringUtils.contains(resource, "..")) {
            return false;
        }
        // 判断是否在允许下载的文件规则内
        return ArrayUtils.contains(MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, FileTypeUtils.getFileType(resource));
    }

    /**
     * 下载文件名重新编码
     *
     * @param request  请求对象
     * @param fileName 文件名
     * @return 编码后的文件名
     */
    public static String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        } else if (agent.contains("Chrome")) {
            // google浏览器
            filename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        }
        return filename;
    }

    /**
     * 返回文件名
     *
     * @param filePath 文件
     * @return 文件名
     */
    public static String getName(String filePath) {
        if (null == filePath) {
            return null;
        }
        int len = filePath.length();
        if (0 == len) {
            return filePath;
        }
        if (isFileSeparator(filePath.charAt(len - 1))) {
            // 以分隔符结尾的去掉结尾分隔符
            len--;
        }

        int begin = 0;
        char c;
        for (int i = len - 1; i > -1; i--) {
            c = filePath.charAt(i);
            if (isFileSeparator(c)) {
                // 查找最后一个路径分隔符（/或者\）
                begin = i + 1;
                break;
            }
        }

        return filePath.substring(begin, len);
    }

    /**
     * 是否为Windows或者Linux（Unix）文件分隔符<br>
     * Windows平台下分隔符为\，Linux（Unix）为/
     *
     * @param c 字符
     * @return 是否为Windows或者Linux（Unix）文件分隔符
     */
    public static boolean isFileSeparator(char c) {
        return SLASH == c || BACKSLASH == c;
    }

    /**
     * 下载文件名重新编码
     *
     * @param response     响应对象
     * @param realFileName 真实文件名
     */
    public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName) throws UnsupportedEncodingException {
        String percentEncodedFileName = percentEncode(realFileName);

        String contentDispositionValue = "attachment; filename=" +
                percentEncodedFileName +
                ";" +
                "filename*=" +
                "utf-8''" +
                percentEncodedFileName;

        response.setHeader("Content-disposition", contentDispositionValue);
        response.setHeader("download-filename", percentEncodedFileName);
    }

    /**
     * 百分号编码工具方法
     *
     * @param s 需要百分号编码的字符串
     * @return 百分号编码后的字符串
     */
    public static String percentEncode(String s) {
        String encode = URLEncoder.encode(s, StandardCharsets.UTF_8);
        return encode.replaceAll("\\+", "%20");
    }

    /**
     * 递归地在指定根目录下查找所有具有特定文件名的文件。
     *
     * @param rootDir        根目录
     * @param searchFileName 要查找的文件名
     * @param caseSensitive  是否大小写敏感
     * @return 找到的具有特定文件名的文件对象列表
     * @throws IOException 如果在访问文件系统时发生错误
     */
    public static List<File> findFiles(File rootDir, String searchFileName, boolean caseSensitive) throws IOException {
        Path startPath = rootDir.toPath();

        if (!Files.exists(startPath)) {
            throw new FileNotFoundException("The provided root path does not exist: " + rootDir.getAbsolutePath());
        }

        List<File> matchingFiles;

        try (var paths = Files.walk(startPath)) {
            matchingFiles = paths
                    .filter(Files::isRegularFile)
                    .filter(path -> {
                        if (caseSensitive) {
                            return path.getFileName().toString().equals(searchFileName);
                        } else {
                            return path.getFileName().toString().equalsIgnoreCase(searchFileName);
                        }
                    })
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        }

        return matchingFiles;
    }

    public static List<File> findFiles(String rootPath, String searchFileName, boolean caseSensitive) throws IOException {
        return findFiles(new File(rootPath), searchFileName, caseSensitive);
    }

    public static List<File> findFiles(String rootPath, String searchFileName) throws IOException {
        return findFiles(rootPath, searchFileName, true);
    }

    /**
     * 读取文件内容并替换多组指定字符串。
     *
     * @param file         文件
     * @param replacements 一个映射表，其中键为要替换的目标字符串，值为替换后的字符串
     * @param handler      替换时的回调
     * @throws IOException 如果读取或写入文件时发生错误
     */
    public static void replaceInFile(File file, Map<String, String> replacements, FileActionHandler handler) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("The provided file is null");
        }
        if (!file.exists()) {
            throw new FileNotFoundException("The provided file does not exist: " + file.getAbsolutePath());
        }
        if (replacements.isEmpty()) {
            return;
        }

        Path path = file.toPath();

        // 读取文件内容到String
        String content = Files.readString(path, StandardCharsets.UTF_8);

        // 遍历replacements Map，对每个要替换的字符串进行处理
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            if (handler != null) {
                handler.onHandle(file, entry);
            }
            content = content.replace(entry.getKey(), entry.getValue());
        }

        // 将修改后的内容写回文件
        Files.writeString(path, content, StandardCharsets.UTF_8);
    }

    public static void replaceInFile(File file, Map<String, String> replacements) throws IOException {
        replaceInFile(file, replacements, null);
    }

    /**
     * 检查并删除指定文件中符合正则表达式的行。
     *
     * @param file    文件
     * @param regex   符合此正则表达式的行将被删除
     * @param handler 找到符合正则表达式的行时的回调
     * @throws IOException 如果读取或写入文件时发生错误
     */
    public static void removeLinesMatchingRegex(File file, String regex, FileActionHandler handler) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("The provided file is null");
        }
        if (!file.exists()) {
            throw new FileNotFoundException("The provided file does not exist: " + file.getAbsolutePath());
        }
        if (StringUtils.isBlank(regex)) {
            return;
        }

        Path path = file.toPath();

        // 读取文件的每一行，过滤掉符合正则表达式的行
        try (var lines = Files.lines(path, StandardCharsets.UTF_8)) {
            AtomicInteger lineNo = new AtomicInteger(1);
            List<String> filteredLines = lines.filter(line -> {
                        boolean matches = line.matches(regex);
                        if (matches) {
                            if (handler != null) {
                                handler.onHandle(file, lineNo.get(), line);
                            }
                        }
                        lineNo.getAndIncrement();
                        return !matches;
                    })
                    .collect(Collectors.toList());

            // 将过滤后的内容写回文件
            Files.write(path, filteredLines, StandardCharsets.UTF_8);
        }
    }

    public static void removeLinesMatchingRegex(File file, String regex) throws IOException {
        removeLinesMatchingRegex(file, regex, null);
    }

}
