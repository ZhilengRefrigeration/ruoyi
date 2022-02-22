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
import com.xjs.common.client.api.baidu.BaiduFeignClient;
import com.xjs.common.client.api.gaode.GaodeWeatherFeignClient;
import com.xjs.common.client.api.lq.LqAWordFeignClient;
import com.xjs.common.client.api.lq.LqDogDiaryFeignClient;
import com.xjs.common.client.api.lq.LqPoisonChickenFeignClient;
import com.xjs.common.client.api.roll.RollBeautyPictureFeignClient;
import com.xjs.common.client.api.roll.RollChineseDictFeignClient;
import com.xjs.properties.AlApiProperties;
import com.xjs.properties.BaiduProperties;
import com.xjs.properties.GaodeProperties;
import com.xjs.properties.RollProperties;
import com.xjs.translation.domain.qo.translation.BaiDuTranslationQo;
import com.xjs.weather.domain.IPInfoVo;
import com.xjs.weather.domain.RequestBody;
import com.xjs.weather.service.IPService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
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
    public static final String content = "test";

    /**
     * 城市编码
     */
    public static final String cityId = "360100";


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
    private AlapiJokeAllFeignClient alapiJokeAllFeignClient;
    @Autowired
    private BaiduFeignClient baiduFeignClient;
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

    /**
     * 检查api状态 <br>
     * 后续实现新API需要在这新增检查项
     */
    public void checkApiStatus() {
        try {
            this.checkAlapiJoke();

            this.checkBaiduTranslation();

            this.checkGaodeWeather();

            this.checkLqAWord();

            this.checkLqDogDiary();

            this.checkRollBeautyPicture();



        } catch (Exception e) {
            e.printStackTrace();
        }


        // todo 还剩20多个api检查没写

    }

    /**
     * 检查alapi平台笑话大全API
     */
    private void checkAlapiJoke() {
        JSONObject jsonObject = alapiJokeAllFeignClient.alapiJokeAllApi(alApiProperties.getToken());

        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(AlapiJokeAllFeignClient.class);

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

        JSONObject jsonObject = baiduFeignClient.translationApi(baiDuTranslationQo);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(BaiduFeignClient.class);

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

        String[] info = this.getAnnotationInfo(GaodeWeatherFeignClient.class);

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

        String[] info = this.getAnnotationInfo(GaodeWeatherFeignClient.class);
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

        String[] info = this.getAnnotationInfo(GaodeWeatherFeignClient.class);
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

        String[] info = this.getAnnotationInfo(GaodeWeatherFeignClient.class);
        this.selectAndUpdate(info);
        log.error("检查发现零七平台 毒鸡汤API异常");
    }


    /**
     * 检查ROLL平台 美女图片API
     */
    private void checkRollBeautyPicture() {
        com.xjs.apitools.domain.RequestBody requestBody = new com.xjs.apitools.domain.RequestBody();
        requestBody.setApp_secret(rollProperties.getApp_secret());
        requestBody.setApp_id(rollProperties.getApp_id());
        JSONObject jsonObject = rollBeautyPictureFeignClient.beautyPictureApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            return;
        }

        String[] info = this.getAnnotationInfo(GaodeWeatherFeignClient.class);
        this.selectAndUpdate(info);
        log.error("检查发现ROLL平台 美女图片API异常");

    }





















    /**
     * 反射获取注解信息
     *
     * @param cls 反射类
     * @return 注解信息 [0]=name [1]=url
     */
    private String[] getAnnotationInfo(Class<?> cls) {

        Method[] publicMethods = ReflectUtil.getPublicMethods(cls);

        if (publicMethods.length > 0) {
            for (Method method : publicMethods) {
                ApiLog annotation = method.getAnnotation(ApiLog.class);
                if (annotation != null) {
                    String name = annotation.name();
                    String url = annotation.url();

                    return new String[]{name, url};
                }
            }
        }
        return null;
    }


    /**
     * 查询和修改状态
     *
     * @param info 注解信息 [0]=name [1]=url
     */
    private void selectAndUpdate(String[] info) {
        if (info != null) {
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
