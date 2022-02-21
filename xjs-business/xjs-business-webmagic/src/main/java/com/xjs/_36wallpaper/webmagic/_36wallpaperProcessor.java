package com.xjs._36wallpaper.webmagic;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.api.RemoteConfigService;
import com.xjs._36wallpaper.consts._36wallpaperConst;
import com.xjs._36wallpaper.pojo._36wallpaper;
import com.xjs._36wallpaper.service._36wallpaperService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.xjs._36wallpaper.consts._36wallpaperConst.CONFIG_KEY;
import static com.xjs._36wallpaper.consts._36wallpaperConst.REDIS_KEY;
import static com.xjs.consts.RedisConst.REPTILE_COUNT;
import static com.xjs.consts.ReptileConst._36_WALLPAPER_URL;

/**
 * 36壁纸网站 爬虫处理
 *
 * @author xiejs
 * @since 2022-02-19
 */
@Log4j2
@Component

public class _36wallpaperProcessor implements PageProcessor {

    /**
     * 是否全网爬虫
     */
    private boolean init ;

    /**
     * 是否下载图片带磁盘
     */
    private boolean downloadImg;

    /**
     * 图片保存到磁盘的路径
     */
    private String path ;

    /**
     * 请求头key
     */
    private static final String headerKey = "User-Agent";
    /**
     * 请求头value
     */
    private static final String headerValue = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36";


    private static RemoteConfigService remoteConfigService;

    @Autowired
    public void setRemoteConfigService(RemoteConfigService remoteConfigService) {
        _36wallpaperProcessor.remoteConfigService = remoteConfigService;
    }

    @Autowired
    private _36wallpaperPipeline wallpaperPipeline;

    private static RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        _36wallpaperProcessor.redisService = redisService;
    }


    private static _36wallpaperService wallpaperService;

    @Autowired
    public void setWallpaperService(_36wallpaperService wallpaperService) {
        _36wallpaperProcessor.wallpaperService = wallpaperService;
    }


    /**
     * 初始化参数
     */
    private void initParameter() {
        //先赋予默认值
        this.init= _36wallpaperConst.INIT;
        this.path= _36wallpaperConst.PATH;
        this.downloadImg= _36wallpaperConst.DOWNLOAD_IMG;

        //判断redis中是否存在
        Boolean hasKey = redisService.hasKey(REDIS_KEY);
        JSONObject json;
        String downloadImg = "downloadImg";
        String path = "path";
        String init = "init";
        if (hasKey) {
            String cacheObject = redisService.getCacheObject(REDIS_KEY);
            try {
                json = JSONObject.parseObject(cacheObject);
                if(json.containsKey(init) && json.containsKey(downloadImg) && json.containsKey(path)){
                    this.init = json.getBoolean(init);
                    this.downloadImg = json.getBoolean(downloadImg);
                    this.path = json.getString(path);
                }
            } catch (Exception e) {
                log.error("JSON转换异常:"+e.getMessage());
            }

        } else if (StringUtils.isNotEmpty(remoteConfigService.getConfigKeyForRPC(CONFIG_KEY).getData())) {
            String data = remoteConfigService.getConfigKeyForRPC(CONFIG_KEY).getData();
            try {
                json = JSONObject.parseObject(data);
                if(json.containsKey(init) && json.containsKey(downloadImg) && json.containsKey(path)){
                    this.init = json.getBoolean(init);
                    this.downloadImg = json.getBoolean(downloadImg);
                    this.path = json.getString(path);
                }
            } catch (Exception e) {
                log.error("JSON转换异常:"+e.getMessage());
            }
        }
    }


    /**
     * 解析页面
     *
     * @param page 页面
     */
    @Override
    public void process(Page page) {
        //初始化配置 (放在此处而不放在run方法原因，每次执行该方法都是创建线程拿到当前的类属性不一致)
        initParameter();

        //解析返回的数据page,并且把解析的结果放到ResultItems中

        /*//第一种写法:css选择器
        page.putField("url_css", page.getHtml().css(".tmenu li a").all());


        //第二种写法:xpath
        page.putField("url_xpath",page.getHtml().xpath("//ul[@class=tmenu]/li/a").all());


        //第三种写法:正则表达式
        page.putField("url_regex", page.getHtml().css(".tmenu li a").regex(".*图片*.*").all());*/

        try {
            //获取链接
            page.addTargetRequests(page.getHtml().css(".topmenuc .tmenu li").links().all());

            //爬取所有分页---后期这两段可以注释
            //当init为true时全网都爬
            if (init) {
                page.addTargetRequests(page.getHtml().css("#pageNum a").links().all());
            }

            page.addTargetRequests(page.getHtml().css(".contlistw > .cl > li > a").links().all());


            ArrayList<_36wallpaper> wallpapers = new ArrayList<>();
            List<Selectable> bodyNote = page.getHtml().css("body").nodes();
            for (Selectable body : bodyNote) {
                _36wallpaper wallpaper = new _36wallpaper();

                //爬取图片分类
                String titleHtml = body.css(".catpos a:nth-child(3)").get();

                String title = "null";
                if (titleHtml != null) {
                    Document titleDom = Jsoup.parse(titleHtml);
                    title = titleDom.text();
                    wallpaper.setType(title);
                }


                //爬取图片名称
                String html = body.css(".showtitle h2").get();
                String pictureName = "null";
                if (html != null) {
                    pictureName = Jsoup.parse(html).text();
                    wallpaper.setPictureName(pictureName);
                }


                //爬取图片路径
                String link = body.css(".morew").links().get();
                wallpaper.setPictureUrl(link);


                //保存到磁盘
                if (downloadImg) {
                    if (link != null) {
                        String thisPath = path + File.separator + title;
                        downloadPicture(link, thisPath, pictureName + ".jpg");
                    }
                }

                //爬取图片标签
                List<String> tagList = body.css(".showcontw > .showtaglistw a").all();
                StringBuilder bf = new StringBuilder();

                for (int i = 0; i < tagList.size(); i++) {
                    String text = Jsoup.parse(tagList.get(i)).text();
                    if (i != tagList.size() - 1) {
                        bf.append(text + ",");
                    } else {
                        bf.append(text);
                    }
                }

                wallpaper.setLabel(bf.toString());

                if (wallpaper.getPictureUrl() != null) {
                    wallpapers.add(wallpaper);
                }
            }

            //持久化  --使用Pipeline实现持久化了
            wallpaperService.saveBatch(wallpapers, 25);

            //暂时保存到内存中，后续实现Pipeline接口保存到数据库--效率低下
            //page.putField("_36wallpaperData",wallpapers);

            //循环次数存入redis中
            Integer count = redisService.getCacheObject(REPTILE_COUNT);
            if (count == null) {
                count = 0;
            }
            redisService.setCacheObject(REPTILE_COUNT, count + 1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisService.expire(REPTILE_COUNT, 1, TimeUnit.HOURS);
        }

    }


    @Override
    public Site getSite() {
        return Site.me()
                .addHeader(headerKey, headerValue)
                .setCharset("utf8")//设置字符编码
                .setTimeOut(2000)//设置超时时间
                .setRetrySleepTime(100)//设置重试间隔时间
                .setCycleRetryTimes(10)//设置重试次数
                .setSleepTime(1)//设置两个页面之间的间隔时间
                ;
    }

    /**
     * 执行爬虫
     *
     * @return 返回循环次数
     */
    public Long run() {
        //执行爬虫
        Spider.create(new _36wallpaperProcessor())
                .addUrl(_36_WALLPAPER_URL)//设置爬取地址
                .thread(30)//设置爬取线程数
                .setScheduler(new QueueScheduler()
                        .setDuplicateRemover(new BloomFilterDuplicateRemover(110000)))//设置url去重过滤器
                //.addPipeline(wallpaperPipeline)//设置爬取之后的数据操作
                .run();//执行

        //删除重复数据
        int count = wallpaperService.deleteRepeatData();
        log.info("36壁纸删除重复数据数：" + count);

        //从redis中获取循环次数
        Integer cache = redisService.getCacheObject(REPTILE_COUNT);
        redisService.deleteObject(REPTILE_COUNT);

        if (cache != null) {
            return Long.valueOf(cache);
        }
        return 0L;
    }


    //链接url下载图片
    private void downloadPicture(String urlList, String path, String fileName) {
        java.net.URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }

            //替换\ 防止报错
            if (fileName.contains("/") || fileName.contains("\\")) {
                fileName = fileName.replace("/", "-");
            }

            String absolutePath = file.getAbsolutePath() + File.separator + fileName;

            FileOutputStream fileOutputStream = new FileOutputStream(absolutePath);
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
