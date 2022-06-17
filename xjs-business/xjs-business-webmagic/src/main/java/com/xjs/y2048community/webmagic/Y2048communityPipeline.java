package com.xjs.y2048community.webmagic;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.api.RemoteConfigService;
import com.xjs.common.util.WeiXinUtils;
import com.xjs.utils.RandomUtils;
import com.xjs.weixin.consts.WeiXinConst;
import com.xjs.y2048community.consts.InitConst;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static com.xjs.weixin.consts.WeiXinConst.*;

/**
 * 2048爬虫数据处理
 *
 * @author xiejs
 * @since 2022-06-17
 */
@Component
@Log4j2
public class Y2048communityPipeline implements Pipeline {
    @Autowired
    private RedisService redisService;
    @Resource
    private RemoteConfigService remoteConfigService;


    @Override
    public void process(ResultItems resultItems, Task task) {

        List<String> srcs = resultItems.get("srcs");

        String title = resultItems.get("title");

        String type = resultItems.get("type");

        if (CollUtil.isNotEmpty(srcs) && StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(type)) {
            String appendPath = this.getAppendPath(title, type);

            File file = new File(appendPath);
            if (file.exists()) {
                return;
            }

            for (String link : srcs) {
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
                            continue;
                        } else {
                            suffix = JPG;
                        }

                        String chars = "ABCDEFGHIZKLMNOPQRSTUVWXYZ";
                        char c = chars.charAt((int) (Math.random() * 1));


                        String fileName = RandomUtils.randomZm() + RandomUtil.randomLong(100000, 1000000) + DOT + suffix;

                        this.downloadPicture(inputStream, this.getPath(), fileName, title, type);
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


    }

    /**
     * 链接url下载图片
     *
     * @param inputStream 输入流
     * @param path        磁盘地址
     * @param fileName    文件名称
     * @param title       标题名称
     */
    private void downloadPicture(InputStream inputStream, String path, String fileName, String title, String type) {
        String appendPath = this.getAppendPath(title, type);
        WeiXinUtils.downloadPicture(inputStream, path, fileName, title, appendPath);
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
        Boolean hasKey = redisService.hasKey(REDIS_KEY_Y_2048);
        if (hasKey) {
            path = redisService.getCacheObject(REDIS_KEY_Y_2048);
        } else {
            String data = remoteConfigService.getConfigKeyForRPC(CONFIG_KEY_Y_2048).getData();
            if (StringUtils.isNotEmpty(data)) {
                path = data;
            } else {
                path = WeiXinConst.PATH;
            }
        }
        return path;
    }

    /**
     * 获取拼接后的磁盘路径
     *
     * @param title 拼接的最后的文件夹
     * @return str
     */
    private String getAppendPath(String title, String type) {
        title = WeiXinUtils.filterTitle(title);

        String path = this.getPath() + File.separator + DateUtil.format(new Date(),
                DatePattern.NORM_MONTH_PATTERN) + File.separator + type + File.separator + title;

        if (InitConst.CONTROL) {
            path = this.getPath() + File.separator + type;
        }

        return path;
    }
}





