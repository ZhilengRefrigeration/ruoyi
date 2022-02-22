package com.xjs._36wallpaper.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.api.RemoteConfigService;
import com.xjs._36wallpaper.mapper._36wallpaperMapper;
import com.xjs._36wallpaper.pojo._36wallpaper;
import com.xjs._36wallpaper.service._36wallpaperService;
import com.xjs.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.regex.Pattern;

import static com.xjs._36wallpaper.consts._36wallpaperConst.*;
import static com.xjs.consts.RedisConst.REPTILE_36_WALLPAPER_COUNT;
import static com.xjs.consts.RegexConst.FILE_PATH_REGEX;

/**
 * 36壁纸网service实现
 * @author xiejs
 * @since 2022-02-20
 */
@Service
public class _36wallpaperServiceImpl extends ServiceImpl<_36wallpaperMapper, _36wallpaper> implements _36wallpaperService {


    @Resource
    private _36wallpaperMapper wallpaperMapper;

    @Resource
    private RemoteConfigService remoteConfigService;

    @Autowired
    private RedisService redisService;



    @Override
    public int deleteRepeatData() {
        return wallpaperMapper.deleteRepeatData();
    }

    @Override
    public JSONObject getSettings() {
        JSONObject json;

        //先从redis中查，没有就远程调用系统配置服务查数据库
        if (redisService.hasKey(REDIS_KEY)) {
            String cacheObject = redisService.getCacheObject(REDIS_KEY);

            try {
                json = JSONObject.parseObject(cacheObject);
                return json;
            } catch (Exception e) {
                log.error("json格式转换异常："+e.getMessage());
            }
        }

        R<String> r = remoteConfigService.getConfigKeyForRPC(CONFIG_KEY);
        if (r.getCode() == HttpStatus.SUCCESS) {
            try {
                json = JSONObject.parseObject(r.getData());
                return json;
            } catch (Exception e) {
                log.error("json格式转换异常："+e.getMessage());
            }
        }
        return null;
    }

    @Override
    public boolean updateSettings(String json) {
        this.checkRunning();

        //校验json格式是否正确
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            String downloadImg = "downloadImg";
            String path = "path";
            String init = "init";
            if (jsonObject.containsKey(init) && jsonObject.containsKey(downloadImg) && jsonObject.containsKey(path)) {
                //校验json内的参数key是否正确
                jsonObject.getBoolean(init);
                jsonObject.getBoolean(downloadImg);
                String pathValue = jsonObject.getString(path);

                //校验磁盘地址是否正确
                boolean matches = Pattern.matches(FILE_PATH_REGEX, pathValue);
                if (!matches) {
                    throw new BusinessException("文件路径格式不正确");
                }

                R r = remoteConfigService.editForRPC(CONFIG_KEY, json);
                if (r.getCode() == HttpStatus.SUCCESS) {
                    return true;
                }
            }else {
                throw new BusinessException("json属性不正确,必须包含 init、path、downloadImg 三个属性");
            }

        } catch (Exception e) {
            log.error("json格式转换异常"+e.getMessage());
            throw new BusinessException("json格式转换异常" + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean resetSettings() {
        this.checkRunning();

        //构建json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("path",PATH);
        jsonObject.put("init",INIT);
        jsonObject.put("downloadImg",DOWNLOAD_IMG);

        R r = remoteConfigService.editForRPC(CONFIG_KEY, jsonObject.toJSONString());
        if (r.getCode() == HttpStatus.SUCCESS) {
            return true;
        }
        return false;
    }


    /**
     * 检测是否正在运行
     */
    private void checkRunning() {
        //判断爬虫是否正在执行，正在执行不可修改！
        if(redisService.hasKey(REPTILE_36_WALLPAPER_COUNT)){
            throw new BusinessException("爬虫正在执行中！暂时无法修改，请稍后再试");
        }
    }
}
