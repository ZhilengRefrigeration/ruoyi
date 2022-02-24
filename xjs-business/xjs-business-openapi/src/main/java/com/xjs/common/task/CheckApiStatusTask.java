package com.xjs.common.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.xjs.annotation.ApiLog;
import com.xjs.business.warning.RemoteWarningCRUDFeign;
import com.xjs.business.warning.domain.ApiRecord;
import com.xjs.common.client.api.alapi.AlapiJokeAllFeignClient;
import com.xjs.common.client.api.baidu.BaiduTranslationFeignClient;
import com.xjs.common.client.api.gaode.GaodeWeatherFeignClient;
import com.xjs.common.client.api.lq.LqAWordFeignClient;
import com.xjs.common.client.api.lq.LqDogDiaryFeignClient;
import com.xjs.common.client.api.lq.LqPoisonChickenFeignClient;
import com.xjs.common.client.api.roll.*;
import com.xjs.common.client.api.tianxing.*;
import com.xjs.common.client.api.youdao.YouDaoFeignClient;
import com.xjs.consts.ApiConst;
import com.xjs.properties.*;
import com.xjs.translation.domain.qo.translation.BaiDuTranslationQo;
import com.xjs.translation.domain.qo.translation.RollTranslationQo;
import com.xjs.translation.domain.qo.translation.YouDaoTranslationQo;
import com.xjs.weather.domain.IPInfoVo;
import com.xjs.weather.domain.RequestBody;
import com.xjs.weather.service.IPService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;
import static com.xjs.consts.ApiConst.GAODE_EXTENSIONS_BASE;
import static com.xjs.consts.ReqConst.ERROR;

/**
 * 检查api状态任务
 *
 * @author xiejs
 * @since 2022-02-21
 */
@Component
@Log4j2
public class CheckApiStatusTask {
    /**
     * 需要输入的文本内容
     */
    private static final String content = "测试";

    /**
     * 城市编码
     */
    private static final String cityId = "360100";

    /**
     * 城市名称
     */
    public static final String city = "南昌";

    /**
     * 手机号码
     */
    public static final String mobile = "18907084291";


    @Autowired
    private RemoteWarningCRUDFeign remoteWarningCRUDFeign;

    @Autowired
    private IPService ipService;


    @Autowired
    private AlApiProperties alApiProperties;
    @Autowired
    private BaiduProperties baiduProperties;
    @Autowired
    private GaodeProperties gaodeProperties;
    @Autowired
    private RollProperties rollProperties;
    @Autowired
    private TianXingProperties tianXingProperties;

    @Autowired
    private AlapiJokeAllFeignClient alapiJokeAllFeignClient;
    @Autowired
    private BaiduTranslationFeignClient baiduTranslationFeignClient;
    @Autowired
    private GaodeWeatherFeignClient gaodeWeatherFeignClient;
    @Autowired
    private LqAWordFeignClient lqAWordFeignClient;
    @Autowired
    private LqDogDiaryFeignClient lqDogDiaryFeignClient;
    @Autowired
    private LqPoisonChickenFeignClient lqPoisonChickenFeignClient;
    @Autowired
    private RollBeautyPictureFeignClient rollBeautyPictureFeignClient;
    @Autowired
    private RollChineseDictFeignClient rollChineseDictFeignClient;
    @Autowired
    private RollGarbageSortingDeignClient rollGarbageSortingDeignClient;
    @Autowired
    private RollHistoryTodayFeignClient rollHistoryTodayFeignClient;
    @Autowired
    private RollHolidayFeignClient rollHolidayFeignClient;
    @Autowired
    private RollIdcardQueryFeignClient rollIdcardQueryFeignClient;
    @Autowired
    private RollIPFeignClient rollIPFeignClient;
    @Autowired
    private RollJokeFeignClient rollJokeFeignClient;
    @Autowired
    private RollMMYJFeignClient rollMMYJFeignClient;
    @Autowired
    private RollMobileBelongFeignClient rollMobileBelongFeignClient;
    @Autowired
    private RollSimpleComplexFeignClient rollSimpleComplexFeignClient;
    @Autowired
    private RollTranslationFeignClient rollTranslationFeignClient;
    @Autowired
    private RollWeatherFeignClient rollWeatherFeignClient;
    @Autowired
    private TianXingBDRSFeignClient tianXingBDRSFeignClient;
    @Autowired
    private TianXingDYRSFeignClient tianXingDYRSFeignClient;
    @Autowired
    private TianXingJDTCFeignClient tianXingJDTCFeignClient;
    @Autowired
    private TianXingMMMYFeignClient tianXingMMMYFeignClient;
    @Autowired
    private TianXingMMYJFeignClient tianXingMMYJFeignClient;
    @Autowired
    private TianXingOneEnglishFeignClient tianXingOneEnglishFeignClient;
    @Autowired
    private TianXingPYQFeignClient tianXingPYQFeignClient;
    @Autowired
    private TianXingQWRSFeignClient tianXingQWRSFeignClient;
    @Autowired
    private TianXingTranDictClient tianXingTranDictClient;
    @Autowired
    private TianXingWBRSFeignClient tianXingWBRSFeignClient;
    @Autowired
    private TianXingWXRSFeignClient tianXingWXRSFeignClient;
    @Autowired
    private TianXingWYYFeignClient tianXingWYYFeignClient;
    @Autowired
    private YouDaoFeignClient youDaoFeignClient;

    /**
     * 检查api状态 <br>
     * 后续实现新API需要在这新增检查项
     */
    public void checkApiStatus() {
        try {

            Runnable runCheckAlapiJoke = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkAlapiJoke();
            };
            new Thread(runCheckAlapiJoke).start();

            Runnable runCheckBaiduTranslation = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkBaiduTranslation();
            };
            new Thread(runCheckBaiduTranslation).start();

            Runnable runCheckGaodeWeather = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkGaodeWeather();
            };
            new Thread(runCheckGaodeWeather).start();

            Runnable runCheckLqAWord = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkLqAWord();
            };
            new Thread(runCheckLqAWord).start();

            Runnable runCheckLqDogDiary = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkLqDogDiary();
            };
            new Thread(runCheckLqDogDiary).start();

            Runnable runCheckLqPoisonChicken = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkLqPoisonChicken();
            };
            new Thread(runCheckLqPoisonChicken).start();

            Runnable runCheckRollBeautyPicture = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkRollBeautyPicture();
            };
            new Thread(runCheckRollBeautyPicture).start();

            Runnable runCheckRollChineseDict = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkRollChineseDict();
            };
            new Thread(runCheckRollChineseDict).start();

            Runnable runCheckRollGarbageSorting = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkRollGarbageSorting();
            };
            new Thread(runCheckRollGarbageSorting).start();

            Runnable runCheckRollHistoryToday = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkRollHistoryToday();
            };
            new Thread(runCheckRollHistoryToday).start();

            Runnable runCheckRollHoliday = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkRollHoliday();
            };
            new Thread(runCheckRollHoliday).start();

            Runnable runCheckRollJoke = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkRollJoke();
            };
            new Thread(runCheckRollJoke).start();

            Runnable runCheckRollIp = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkRollIp();
            };
            new Thread(runCheckRollIp).start();

            Runnable runCheckRollIdCardQuery = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkRollIdCardQuery();
            };
            new Thread(runCheckRollIdCardQuery).start();

            Runnable runCheckRollMMYJ = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkRollMMYJ();
            };
            new Thread(runCheckRollMMYJ).start();

            Runnable runCheckRollMobileBelong = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkRollMobileBelong();
            };
            new Thread(runCheckRollMobileBelong).start();

            Runnable runCheckRollSimpleComplex = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkRollSimpleComplex();
            };
            new Thread(runCheckRollSimpleComplex).start();

            Runnable runCheckRollTranslation = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkRollTranslation();
            };
            new Thread(runCheckRollTranslation).start();

            Runnable runCheckRollWeather = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkRollWeather();
            };
            new Thread(runCheckRollWeather).start();

            Runnable runCheckTianXingBDRS = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkTianXingBDRS();
            };
            new Thread(runCheckTianXingBDRS).start();

            Runnable runCheckTianXingDYRS = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkTianXingDYRS();
            };
            new Thread(runCheckTianXingDYRS).start();

            Runnable runCheckTianXingJDTC = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkTianXingJDTC();
            };
            new Thread(runCheckTianXingJDTC).start();

            Runnable runCheckTianXingMMMY = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkTianXingMMMY();
            };
            new Thread(runCheckTianXingMMMY).start();

            Runnable runCheckTianXingMMYJ = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkTianXingMMYJ();
            };
            new Thread(runCheckTianXingMMYJ).start();

            Runnable runCheckTianXingOneEnglish = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkTianXingOneEnglish();
            };
            new Thread(runCheckTianXingOneEnglish).start();

            Runnable runCheckTianXingPYQ = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkTianXingPYQ();
            };
            new Thread(runCheckTianXingPYQ).start();

            Runnable runCheckTianXingQWRS = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkTianXingQWRS();
            };
            new Thread(runCheckTianXingQWRS).start();

            Runnable runCheckTianXingTranDict = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkTianXingTranDict();
            };
            new Thread(runCheckTianXingTranDict).start();

            Runnable runCheckTianXingWBRS = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkTianXingWBRS();
            };
            new Thread(runCheckTianXingWBRS).start();

            Runnable runCheckTianXingWXRS = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkTianXingWXRS();
            };
            new Thread(runCheckTianXingWXRS).start();

            Runnable runCheckTianXingWYY = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkTianXingWYY();
            };
            new Thread(runCheckTianXingWYY).start();

            Runnable runCheckYouDaoTranslation = () -> {
                log.info("线程启动：" + Thread.currentThread().getName());
                this.checkYouDaoTranslation();
            };
            new Thread(runCheckYouDaoTranslation).start();

            //this.checkAlapiJoke();
            //this.checkBaiduTranslation();
            //this.checkGaodeWeather();
            //this.checkLqAWord();
            //this.checkLqDogDiary();
            //this.checkLqPoisonChicken();
            //this.checkRollBeautyPicture();
            //this.checkRollChineseDict();
            //this.checkRollGarbageSorting();
            //this.checkRollHistoryToday();
            //this.checkRollHoliday();
            //this.checkRollJoke();
            //this.checkRollIp();
            //this.checkRollIdCardQuery();
            //this.checkRollMMYJ();
            //this.checkRollMobileBelong();
            //this.checkRollSimpleComplex();
            //this.checkRollTranslation();
            //this.checkRollWeather();
            //this.checkTianXingBDRS();
            //this.checkTianXingDYRS();
            //this.checkTianXingJDTC();
            //this.checkTianXingMMMY();
            //this.checkTianXingMMYJ();
            //this.checkTianXingOneEnglish();
            //this.checkTianXingPYQ();
            //this.checkTianXingQWRS();
            //this.checkTianXingTranDict();
            //this.checkTianXingWBRS();
            //this.checkTianXingWXRS();
            //this.checkTianXingWYY();
            //this.checkYouDaoTranslation();


        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    /**
     * 检查有道平台 翻译API
     */
    private void checkYouDaoTranslation() {
        YouDaoTranslationQo youDaoTranslationQo = new YouDaoTranslationQo();
        youDaoTranslationQo.setI(content);
        JSONObject jsonObject = youDaoFeignClient.translationApi(youDaoTranslationQo);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(YouDaoFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现有道平台 翻译API异常");
    }


    /**
     * 检查天行平台 网易云热评API
     */
    private void checkTianXingWYY() {
        com.xjs.copywriting.domain.RequestBody requestBody = new com.xjs.copywriting.domain.RequestBody();
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingWYYFeignClient.copyWritingApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(TianXingWYYFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现天行平台 网易云热评API异常");
    }


    /**
     * 检查天行平台 微信热搜榜API
     */
    private void checkTianXingWXRS() {
        JSONObject jsonObject = tianXingWXRSFeignClient.topSearchApi(tianXingProperties.getKey());
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(TianXingWXRSFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现天行平台 微信热搜榜API异常");
    }

    /**
     * 检查天行平台 微博热搜榜API
     */
    private void checkTianXingWBRS() {
        JSONObject jsonObject = tianXingWBRSFeignClient.topSearchApi(tianXingProperties.getKey());
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(TianXingWBRSFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现天行平台 微博热搜榜API异常");
    }


    /**
     * 检查天行平台 翻译字典API
     */
    private void checkTianXingTranDict() {
        com.xjs.copywriting.domain.RequestBody requestBody = new com.xjs.copywriting.domain.RequestBody();
        requestBody.setWord(content);
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingTranDictClient.tranDictApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(TianXingTranDictClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现天行平台 翻译字典API异常");

    }



    /**
     * 检查天行平台 全网热搜榜API
     */
    private void checkTianXingQWRS() {
        JSONObject jsonObject = tianXingQWRSFeignClient.topSearchApi(tianXingProperties.getKey());
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(TianXingQWRSFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现天行平台 全网热搜榜API异常");
    }


    /**
     * 检查天行平台 朋友圈文案API
     */
    private void checkTianXingPYQ() {
        com.xjs.copywriting.domain.RequestBody requestBody = new com.xjs.copywriting.domain.RequestBody();
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingPYQFeignClient.copyWritingApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(TianXingPYQFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现天行平台 朋友圈文案API异常");
    }


    /**
     * 检查天行平台 英语一言API
     */
    private void checkTianXingOneEnglish() {
        com.xjs.oneenglish.domain.RequestBody requestBody = new com.xjs.oneenglish.domain.RequestBody();
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingOneEnglishFeignClient.oneEnglishApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(TianXingOneEnglishFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现天行平台 英语一言API异常");

    }


    /**
     * 检查天行平台 每日一句API
     */
    private void checkTianXingMMYJ() {
        com.xjs.aword.domain.RequestBody requestBody = new com.xjs.aword.domain.RequestBody();
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingMMYJFeignClient.aWordApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(TianXingMMYJFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现天行平台 每日一句API异常");
    }



    /**
     * 检查天行平台 名人名言API
     */
    private void checkTianXingMMMY() {
        com.xjs.copywriting.domain.RequestBody requestBody = new com.xjs.copywriting.domain.RequestBody();
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingMMMYFeignClient.copyWritingApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(TianXingMMMYFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现天行平台 名人名言API异常");

    }


    /**
     * 检查天行平台 经典台词API
     */
    private void checkTianXingJDTC() {
        com.xjs.copywriting.domain.RequestBody requestBody = new com.xjs.copywriting.domain.RequestBody();
        requestBody.setKey(tianXingProperties.getKey());
        JSONObject jsonObject = tianXingJDTCFeignClient.copyWritingApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(TianXingJDTCFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现天行平台 经典台词API异常");
    }


    /**
     * 检查天行平台 抖音热搜榜API
     */
    private void checkTianXingDYRS() {
        JSONObject jsonObject = tianXingDYRSFeignClient.topSearchApi(tianXingProperties.getKey());
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(TianXingDYRSFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现天行平台 抖音热搜榜API异常");
    }

    /**
     * 检查天行平台 百度热搜榜API
     */
    private void checkTianXingBDRS() {
        JSONObject jsonObject = tianXingBDRSFeignClient.topSearchApi(tianXingProperties.getKey());
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(TianXingBDRSFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现天行平台 百度热搜榜API异常");
    }

    /**
     * 检查ROLL平台 天气API
     */
    private void checkRollWeather() {
        com.xjs.apitools.domain.RequestBody requestBody = getRequestBody();

        JSONObject jsonObject1 = rollWeatherFeignClient.nowWeatherApi(requestBody, city);
        JSONObject jsonObject2 = rollWeatherFeignClient.forecastWeatherApi(requestBody, city);

        if (jsonObject1.containsKey(DEMOTE_ERROR)) {
            String[] info = this.getAnnotationInfo(RollWeatherFeignClient.class).get(0);
            this.selectAndUpdate(info);
            log.error("检查发现ROLL平台 实时天气API异常");
        }

        if (jsonObject2.containsKey(DEMOTE_ERROR)) {
            String[] info = this.getAnnotationInfo(RollWeatherFeignClient.class).get(1);
            this.selectAndUpdate(info);
            log.error("检查发现ROLL平台 预报天气API异常");
        }

    }


    /**
     * 检查ROLL平台 翻译API
     */
    private void checkRollTranslation() {
        RollTranslationQo rollTranslationQo = new RollTranslationQo();
        rollTranslationQo.setApp_id(rollProperties.getApp_id());
        rollTranslationQo.setApp_secret(rollProperties.getApp_secret());
        rollTranslationQo.setContent(content);
        JSONObject jsonObject = rollTranslationFeignClient.translationApi(rollTranslationQo);

        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(RollTranslationFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现ROLL平台 翻译API异常");
    }


    /**
     * 检查ROLL平台 简繁转换API
     */
    private void checkRollSimpleComplex() {
        com.xjs.apitools.domain.RequestBody requestBody = getRequestBody();
        requestBody.setType(1);
        requestBody.setContent(content);
        JSONObject jsonObject = rollSimpleComplexFeignClient.simpleComplexApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(RollSimpleComplexFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现ROLL平台 简繁转换API异常");
    }


    /**
     * 检查ROLL平台 手机归属地API
     */
    private void checkRollMobileBelong() {
        com.xjs.apitools.domain.RequestBody requestBody = getRequestBody();
        requestBody.setMobile(mobile);
        JSONObject jsonObject = rollMobileBelongFeignClient.mobileBelongApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(RollMobileBelongFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现ROLL平台 手机归属地API异常");
    }


    /**
     * 检查ROLL平台 每日一句API
     */
    private void checkRollMMYJ() {
        com.xjs.copywriting.domain.RequestBody requestBody = new com.xjs.copywriting.domain.RequestBody();
        requestBody.setCount(20);
        requestBody.setApp_id(rollProperties.getApp_id());
        requestBody.setApp_secret(rollProperties.getApp_secret());
        JSONObject jsonObject = rollMMYJFeignClient.copyWritingApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(RollMMYJFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现ROLL平台 每日一句API异常");
    }


    /**
     * 检查ROLL平台 搞笑段子API
     */
    private void checkRollJoke() {
        com.xjs.copywriting.domain.RequestBody requestBody = new com.xjs.copywriting.domain.RequestBody();
        requestBody.setApp_id(rollProperties.getApp_id());
        requestBody.setApp_secret(rollProperties.getApp_secret());
        JSONObject jsonObject = rollJokeFeignClient.jokeApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(RollJokeFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现ROLL平台 搞笑段子API异常");
    }


    /**
     * 检查ROLL平台 IP信息API
     */
    private void checkRollIp() {
        com.xjs.copywriting.domain.RequestBody requestBody = new com.xjs.copywriting.domain.RequestBody();
        requestBody.setIp(ApiConst.LOCAL_IP);
        requestBody.setApp_id(rollProperties.getApp_id());
        requestBody.setApp_secret(rollProperties.getApp_secret());
        JSONObject jsonObject = rollIPFeignClient.ipApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(RollIPFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现ROLL平台 IP信息API异常");

    }


    /**
     * 检查ROLL平台 身份证查询API
     */
    private void checkRollIdCardQuery() {
        com.xjs.apitools.domain.RequestBody requestBody = getRequestBody();
        JSONObject jsonObject = rollIdcardQueryFeignClient.idcardQueryApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        String[] info = this.getAnnotationInfo(RollIdcardQueryFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现ROLL平台 身份证查询API异常");
    }

    /**
     * 检查alapi平台笑话大全API
     */
    private void checkAlapiJoke() {
        JSONObject jsonObject = alapiJokeAllFeignClient.alapiJokeAllApi(alApiProperties.getToken());

        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(AlapiJokeAllFeignClient.class).get(0);

        this.selectAndUpdate(info);

        log.error("检查发现alapi平台笑话大全API异常");
    }

    /**
     * 检查百度平台翻译API
     */
    private void checkBaiduTranslation() {
        //构建请求参数
        String appId = baiduProperties.getAppId();
        String key = baiduProperties.getKey();
        BaiDuTranslationQo baiDuTranslationQo = new BaiDuTranslationQo();
        baiDuTranslationQo.setAppid(appId);
        //生成签名(appid+q+salt+密钥的MD5值)
        String append = appId + content + baiduProperties.getSalt() + key;
        String sign = SecureUtil.md5(append);
        baiDuTranslationQo.setSign(sign);
        baiDuTranslationQo.setQ(content);
        baiDuTranslationQo.setSalt(baiduProperties.getSalt());

        JSONObject jsonObject = baiduTranslationFeignClient.translationApi(baiDuTranslationQo);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(BaiduTranslationFeignClient.class).get(0);

        this.selectAndUpdate(info);

        log.error("检查发现百度平台翻译API异常");

    }

    /**
     * 检查高德平台天气API
     */
    private void checkGaodeWeather() {
        //构建请求参数
        RequestBody requestBody = new RequestBody();
        //获取城市编码
        IPInfoVo ipApiData = null;
        try {
            ipApiData = ipService.getIPApiData();
        } catch (Exception e) {
            ipApiData = new IPInfoVo();
            ipApiData.setCityId(cityId);
        }
        requestBody.setKey(gaodeProperties.getKey())
                .setCity(ipApiData.getCityId())
                .setExtensions(GAODE_EXTENSIONS_BASE);

        JSONObject jsonObject = gaodeWeatherFeignClient.WeatherApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(GaodeWeatherFeignClient.class).get(0);

        this.selectAndUpdate(info);

        log.error("检查发现高德平台天气API异常");
    }


    /**
     * 检查零七平台 一言API
     */
    private void checkLqAWord() {
        String data = lqAWordFeignClient.aWordApi();
        if (!StringUtils.isEmpty(data)) {
            return;
        }

        String[] info = this.getAnnotationInfo(LqAWordFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现零七平台 一言API异常");
    }


    /**
     * 检查零七平台舔狗日记API
     */
    private void checkLqDogDiary() {
        String data = lqDogDiaryFeignClient.dogDiaryApi();
        if (!StringUtils.isEmpty(data)) {
            return;
        }

        String[] info = this.getAnnotationInfo(LqDogDiaryFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现零七平台 舔狗日记API异常");
    }

    /**
     * 检查零七平台毒鸡汤API
     */
    private void checkLqPoisonChicken() {
        String data = lqPoisonChickenFeignClient.poisonChickenApi();
        if (!StringUtils.isEmpty(data)) {
            return;
        }

        String[] info = this.getAnnotationInfo(LqPoisonChickenFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现零七平台 毒鸡汤API异常");
    }


    /**
     * 检查ROLL平台 美女图片API
     */
    private void checkRollBeautyPicture() {
        com.xjs.apitools.domain.RequestBody requestBody = getRequestBody();
        JSONObject jsonObject = rollBeautyPictureFeignClient.beautyPictureApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(RollBeautyPictureFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现ROLL平台 美女图片API异常");

    }

    /**
     * 检查ROLL平台 汉语字典API
     */
    private void checkRollChineseDict() {
        com.xjs.apitools.domain.RequestBody requestBody = getRequestBody();
        JSONObject jsonObject = rollChineseDictFeignClient.chineseDictApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(RollChineseDictFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现ROLL平台 汉语字典API异常");

    }

    /**
     * 检查ROLL平台 垃圾分类API
     */
    private void checkRollGarbageSorting() {
        com.xjs.apitools.domain.RequestBody requestBody = getRequestBody();
        JSONObject jsonObject = rollGarbageSortingDeignClient.garbageSortingApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(RollGarbageSortingDeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现ROLL平台 垃圾分类API异常");
    }

    /**
     * 检查ROLL平台 历史今天API
     */
    private void checkRollHistoryToday() {
        com.xjs.apitools.domain.RequestBody requestBody = getRequestBody();
        requestBody.setType(0);
        JSONObject jsonObject = rollHistoryTodayFeignClient.historyTodayApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(RollHistoryTodayFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现ROLL平台 历史今天API异常");
    }

    /**
     * 检查ROLL平台 节假日API
     */
    private void checkRollHoliday() {
        com.xjs.apitools.domain.RequestBody requestBody = getRequestBody();
        JSONObject jsonObject = rollHolidayFeignClient.holidayApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }
        this.getAnnotationInfo(RollHolidayFeignClient.class);
        String[] info = this.getAnnotationInfo(RollHolidayFeignClient.class).get(0);
        this.selectAndUpdate(info);
        log.error("检查发现ROLL平台 节假日API异常");
    }


    /**
     * 获取requestBody并封装密钥
     *
     * @return requestBody
     */
    private com.xjs.apitools.domain.RequestBody getRequestBody() {
        com.xjs.apitools.domain.RequestBody requestBody = new com.xjs.apitools.domain.RequestBody();
        requestBody.setApp_secret(rollProperties.getApp_secret());
        requestBody.setApp_id(rollProperties.getApp_id());
        return requestBody;
    }


    /**
     * 反射获取注解信息
     *
     * @param cls 反射类
     * @return 注解信息 [0]=name [1]=url
     */
    private List<String[]> getAnnotationInfo(Class<?> cls) {

        Method[] publicMethods = ReflectUtil.getPublicMethods(cls);
        List<String[]> strings = new ArrayList<>();
        if (publicMethods.length > 0) {

            for (Method method : publicMethods) {
                ApiLog annotation = method.getAnnotation(ApiLog.class);
                if (annotation != null) {
                    String name = annotation.name();
                    String url = annotation.url();
                    strings.add(new String[]{name, url});
                }
            }

            return strings;
        }
        strings.add(new String[]{});
        return strings;
    }


    /**
     * 查询和修改状态
     *
     * @param info 注解信息 [0]=name [1]=url
     */
    private void selectAndUpdate(String[] info) {
        if (info != null && info.length != 0) {
            ApiRecord apiRecord = new ApiRecord();
            apiRecord.setApiName(info[0]);
            apiRecord.setApiUrl(info[1]);
            R<List<ApiRecord>> listR = remoteWarningCRUDFeign.selectApiRecordListForRPC(apiRecord);
            if (listR.getCode() == R.SUCCESS) {
                List<ApiRecord> data = listR.getData();
                if (CollUtil.isNotEmpty(data)) {
                    ApiRecord haveApiRecord = data.get(0);
                    haveApiRecord.setStatus(ERROR);
                    //置为空让mp自动填充
                    haveApiRecord.setUpdateTime(null);
                    remoteWarningCRUDFeign.updateApiRecordForRPC(haveApiRecord);
                }
            }

        }
    }


}
