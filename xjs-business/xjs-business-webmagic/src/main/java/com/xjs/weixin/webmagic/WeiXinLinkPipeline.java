package com.xjs.weixin.webmagic;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.api.RemoteConfigService;
import com.xjs.weixin.consts.WeiXinConst;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.xjs.weixin.consts.WeiXinConst.*;


/**
 * 微信链接爬虫数据处理
 *
 * @author xiejs
 * @since 2022-03-17
 */
@Component
@Log4j2
public class WeiXinLinkPipeline implements Pipeline {

    @Autowired
    private RedisService redisService;
    @Resource
    private RemoteConfigService remoteConfigService;





    @Override
    public void process(ResultItems resultItems, Task task) {

        List<String> linkList = resultItems.get("linkList");
        String title = resultItems.get("title");

        //如果磁盘存在该路径则不进行后续操作（已经有数据）  ----只能判断当天是否存在同一个文件夹，隔天失效
        String appendPath = this.getAppendPath(title);

        File file = new File(appendPath);
        if (file.exists()) {
            return;
        }

        for (String link : linkList) {
            InputStream inputStream = null;

            // 创建GET请求
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = null;
            try {
                httpGet = new HttpGet(link);
                HttpResponse response = httpClient.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SUCCESS) {
                    inputStream = response.getEntity().getContent();

                    //文件小于30kb则不写入
                    long contentLength = response.getEntity().getContentLength();
                    long kb = contentLength / 1024;
                    if (SIZE_KB > kb) {
                        continue;
                    }

                    //拼接文件后缀
                    String suffix;
                    if (link.contains(JPEG)) {
                        suffix = JPEG;
                    } else if (link.contains(JPG)) {
                        suffix = JPG;
                    } else if (link.contains(PNG)) {
                        suffix = PNG;
                    } else if (link.contains(GIF)) {
                        suffix = GIF;
                    } else {
                        suffix = JPG;
                    }
                    String fileName = UUID.randomUUID() + DOT + suffix;

                    this.downloadPicture(inputStream, this.getPath(), fileName, title);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (httpGet != null) {
                        httpGet.clone();
                    }
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }


    }

    /**
     * 链接url下载图片
     *
     * @param inputStream 输入流
     * @param path        磁盘地址
     * @param fileName    文件名称
     * @param title       标题名称
     */
    private void downloadPicture(InputStream inputStream, String path, String fileName, String title) {

        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            //拼接文件路径
            String appendPath = this.getAppendPath(title);

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


    /**
     * 获取拼接后的磁盘路径
     * @param title 拼接的最后的文件夹
     * @return str
     */
    private String getAppendPath(String title) {
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


        return this.getPath() + File.separator + DateUtil.format(new Date(),
                DatePattern.NORM_MONTH_PATTERN) + File.separator
                + DateUtil.format(new Date(), "dd") + "日" + File.separator + title;
    }


    /**
     * 从缓存 -> 数据库 -> 内存 中获取磁盘地址
     *
     * @return 地址
     */
    private String getPath() {
        //磁盘路径
        String path;
        //判断redis中是否存在
        Boolean hasKey = redisService.hasKey(REDIS_KEY);
        if (hasKey) {
            path = redisService.getCacheObject(REDIS_KEY);
        } else {
            String data = remoteConfigService.getConfigKeyForRPC(CONFIG_KEY).getData();
            if (StringUtils.isNotEmpty(data)) {
                path = data;
            } else {
                path = WeiXinConst.PATH;
            }
        }
        return path;
    }


}
