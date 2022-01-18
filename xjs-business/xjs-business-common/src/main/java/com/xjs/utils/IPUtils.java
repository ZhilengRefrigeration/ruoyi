package com.xjs.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ip工具类
 *
 * @author xiejs
 * @since 2022-01-15
 */
public class IPUtils {

    /**
     * 获取公网ip
     *
     * @return
     */
    public static String getV4IP() {
        String ip = null;
        // 第二种方式
        try {
            ip = IPUtils.getNowIP2();
            ip.trim();
        } catch (Exception e) {
            System.out.println("getPublicIP - getNowIP2 failed ~ ");
        }
        if (!StringUtils.isEmpty(ip))
            return ip;
        // 第一种方式
        try {
            ip = IPUtils.getNowIP1();
            ip.trim();
        } catch (Exception e) {
            System.out.println("getPublicIP - getNowIP1 failed ~ ");
        }
        if (!StringUtils.isEmpty(ip))
            return ip;
        // 第五种方式
        try {
            ip = IPUtils.getNowIP5();
            ip.trim();
        } catch (Exception e) {
            System.out.println("getPublicIP - getNowIP5 failed ~ ");
        }
        if (!StringUtils.isEmpty(ip))
            return ip;
        // 第六种方式
        try {
            ip = IPUtils.getNowIP6();
            ip.trim();
        } catch (Exception e) {
            System.out.println("getPublicIP - getNowIP6 failed ~ ");
        }
        if (!StringUtils.isEmpty(ip))
            return ip;
        // 第三种方式
        try {
            ip = IPUtils.getNowIP3();
            ip.trim();
        } catch (Exception e) {
            System.out.println("getPublicIP - getNowIP3 failed ~ ");
        }
        if (!StringUtils.isEmpty(ip))
            return ip;
        // 第四种方式
        try {
            ip = IPUtils.getNowIP4();
            ip.trim();
        } catch (Exception e) {
            System.out.println("getPublicIP - getNowIP4 failed ~ ");
        }
        if (!StringUtils.isEmpty(ip))
            return ip;
        return ip;
    }

    // 方法1
    private static String getNowIP1() throws IOException {
        String ip = null;
        String chinaz = "https://ip.chinaz.com";
        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
            Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
            Matcher m = p.matcher(inputLine.toString());
            if (m.find()) {
                String ipstr = m.group(1);
                ip = ipstr;
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        if (StringUtils.isEmpty(ip)) {
            throw new RuntimeException();
        }
        return ip;
    }

    // 方法2
    private static String getNowIP2() throws IOException {
        String ip = null;
        BufferedReader br = null;
        try {
            URL url = new URL("https://v6r.ipip.net/?format=callback");
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            StringBuffer sb = new StringBuffer("");
            String webContent = "";
            while ((s = br.readLine()) != null) {
                sb.append(s + "\r\n");
            }
            webContent = sb.toString();
            int start = webContent.indexOf("(") + 2;
            int end = webContent.indexOf(")") - 1;
            webContent = webContent.substring(start, end);
            ip = webContent;
        } finally {
            if (br != null)
                br.close();
        }
        if (StringUtils.isEmpty(ip)) {
            throw new RuntimeException();
        }
        return ip;
    }

    // 方法3
    private static String getNowIP3() throws IOException {
        String ip = null;
        String objWebURL = "https://ip.900cha.com/";
        BufferedReader br = null;
        try {
            URL url = new URL(objWebURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            String webContent = "";
            while ((s = br.readLine()) != null) {
                if (s.indexOf("我的IP:") != -1) {
                    ip = s.substring(s.indexOf(":") + 1);
                    break;
                }
            }
        } finally {
            if (br != null)
                br.close();
        }
        if (StringUtils.isEmpty(ip)) {
            throw new RuntimeException();
        }
        return ip;
    }

    // 方法4
    private static String getNowIP4() throws IOException {
        String ip = null;
        String objWebURL = "https://bajiu.cn/ip/";
        BufferedReader br = null;
        try {
            URL url = new URL(objWebURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            while ((s = br.readLine()) != null) {
                if (s.indexOf("互联网IP") != -1) {
                    ip = s.substring(s.indexOf("'") + 1, s.lastIndexOf("'"));
                    break;
                }
            }
        } finally {
            if (br != null)
                br.close();
        }
        if (StringUtils.isEmpty(ip)) {
            throw new RuntimeException();
        }
        return ip;
    }

    /**
     * 方法5
     * @return ip
     * @throws IOException 异常
     */
    private static String getNowIP5() throws IOException {
        String ip = null;
        String objWebURL = "https://www.slogra.com/";
        BufferedReader br = null;
        URL url = new URL(objWebURL);
        br = new BufferedReader(new InputStreamReader(url.openStream()));
        String s = "";
        while ((s = br.readLine()) != null) {
            if (s.indexOf("您的IP地址是") != -1) {
                String[] split = s.split("<font color=#FF0000>");
                String ipInfo = split[1];
                String[] strings = ipInfo.split("</font>");
                ip = strings[0];
            }
        }
        return ip;
    }


    private static String getNowIP6() throws IOException {
        String ip = null;
        String objWebURL = "http://www.133ip.com/";
        BufferedReader br = null;
        URL url = new URL(objWebURL);
        br = new BufferedReader(new InputStreamReader(url.openStream()));
        String s = "";
        while ((s = br.readLine()) != null) {
            if (s.indexOf("[<font color=#0000FF>") != -1) {
                String[] split = s.split("<font color=#0000FF>");
                String ipInfo = split[1];
                String[] strings = ipInfo.split("</font>");
                ip = strings[0];
            }
        }
            return ip;
    }




    /**
     * 获取当前机器的IP
     *
     * @return /
     */
    public static String getLocalIp() {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements();) {
                NetworkInterface anInterface = interfaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration<InetAddress> inetAddresses = anInterface.getInetAddresses(); inetAddresses.hasMoreElements();) {
                    InetAddress inetAddr = inetAddresses.nextElement();
                    // 排除loopback类型地址
                    if (!inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr.getHostAddress();
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress.getHostAddress();
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                return "";
            }
            return jdkSuppliedAddress.getHostAddress();
        } catch (Exception e) {
            return "";
        }
    }


    public static void main(String[] args) throws IOException {
        //String nowIP1 = IPUtils.getNowIP1();
        //String nowIP2 = IPUtils.getNowIP2();
        //String nowIP3 = IPUtils.getNowIP3();
        //String nowIP4 = IPUtils.getNowIP4();
        //System.out.println(nowIP1);
        //System.out.println(nowIP2);
        //System.out.println(nowIP3);
        //System.out.println(nowIP4);

        //String nowIP5 = IPUtils.getNowIP5();
        //System.out.println(nowIP5);

        String nowIP6 = IPUtils.getNowIP6();
        System.out.println(nowIP6);

    }


}
