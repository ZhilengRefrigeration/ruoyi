package com.xjs.common.controller;

import cn.hutool.core.map.MapUtil;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.system.api.RemoteLogService;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.domain.SysOperLog;
import com.ruoyi.system.api.model.LoginUser;
import com.xjs.apitools.domain.ApiBeautyPicture;
import com.xjs.apitools.service.ApiToolsService;
import com.xjs.apitools.service.BeautyPictureService;
import com.xjs.business.english.RemoteEnglishFeign;
import com.xjs.business.english.domain.EnglishWordDTO;
import com.xjs.business.log.RemoteLogFeign;
import com.xjs.business.webmagic.RemoteWebmagicCopyWritingNetworkFeign;
import com.xjs.business.webmagic.RemoteWebmagicSinaFeign;
import com.xjs.business.webmagic.domain.CopyWritingNetworkDTO;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.service.CopyWritingService;
import com.xjs.topsearch.domain.ApiTopsearchWeibo;
import com.xjs.topsearch.service.ApiTopsearchWeiboService;
import com.xjs.weather.domain.IPInfoVo;
import com.xjs.weather.service.IPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 主页控制器
 *
 * @author xiejs
 * @since 2022-06-14
 */
@RestController
@RequestMapping("index")
@Api(tags = "业务模块-主页")
@Log4j2
public class IndexController {

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @Autowired
    private ApiTopsearchWeiboService apiTopsearchWeiboService;
    @Autowired
    private CopyWritingService copyWritingService;
    @Autowired      //解决aop 调用this不代理问题
    private IndexController indexController;
    @Resource
    private RemoteWebmagicCopyWritingNetworkFeign remoteWebmagicCopyWritingNetworkFeign;
    @Resource
    private RemoteLogFeign remoteLogFeign;
    @Resource
    private RemoteUserService remoteUserService;
    @Resource
    private RemoteLogService remoteLogService;
    @Resource
    private RemoteEnglishFeign remoteEnglishFeign;
    @Resource
    private RemoteWebmagicSinaFeign remoteWebmagicSinaFeign;
    @Autowired
    private ApiToolsService apiToolsService;
    @Autowired
    private IPService ipService;
    @Autowired
    private BeautyPictureService beautyPictureService;

    @GetMapping("showData")
    @ApiOperation("展示数据")
    public AjaxResult showWbSearch() throws ExecutionException, InterruptedException {
        Map<Object, Object> map = null;
        try {
            CompletableFuture<List<ApiTopsearchWeibo>> weiboListFuture = CompletableFuture.supplyAsync(() ->
                    apiTopsearchWeiboService.showWbSearch(), executor);

            CompletableFuture<List<CopyWritingNetworkDTO>> networkDTOListFuture = CompletableFuture.supplyAsync(indexController::getCopyWritingNetworkList, executor);

            CompletableFuture<List<CopyWriting>> yunListFuture = CompletableFuture.supplyAsync(() ->
                    copyWritingService.NeteaseHotWord(), executor);

            CompletableFuture<Map<Object, Object>> logCountFuture = CompletableFuture.supplyAsync(indexController::getLogCount, executor);

            CompletableFuture<Integer> loginCountFuture = CompletableFuture.supplyAsync(indexController::getLoginCount, executor);

            CompletableFuture<List<SysOperLog>> sysOperLogFuture = CompletableFuture.supplyAsync(indexController::getSysOperLog, executor);

            CompletableFuture<List<EnglishWordDTO>> englishFuture = CompletableFuture.supplyAsync(indexController::getEnglish, executor);

            CompletableFuture<Map<String, List<String>>> beautyPictureFuture = CompletableFuture.supplyAsync(indexController::getBeautyPictureList, executor);

            CompletableFuture<Map<Object, Object>> newsFuture = CompletableFuture.supplyAsync(indexController::getNews, executor);

            CompletableFuture<IPInfoVo> ipInfoFuture = CompletableFuture.supplyAsync(indexController::getIpInfo, executor);

            CompletableFuture.allOf(
                    weiboListFuture,
                    networkDTOListFuture,
                    yunListFuture,
                    logCountFuture,
                    sysOperLogFuture,
                    englishFuture,
                    newsFuture,
                    beautyPictureFuture,
                    ipInfoFuture
            ).get();

            map = MapUtil.builder()
                    .put("weiboList", weiboListFuture.get())
                    .put("networkDTOList", networkDTOListFuture.get())
                    .put("yunList", yunListFuture.get())
                    .put("logCount", logCountFuture.get())
                    .put("loginCount", loginCountFuture.get())
                    .put("sysOperLog", sysOperLogFuture.get())
                    .put("englishWord", englishFuture.get())
                    .put("news", newsFuture.get())
                    .put("beautyPicture", beautyPictureFuture.get())
                    .put("ipInfo", ipInfoFuture.get())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.success(map);
    }

    @Cacheable(cacheNames = "bussiness:index:copy_writing", key = "#root.method.name")
    public List<CopyWritingNetworkDTO> getCopyWritingNetworkList() {
        return remoteWebmagicCopyWritingNetworkFeign.showCopyWriting().getData();
    }

    @Cacheable(cacheNames = "bussiness:index:log_count", key = "#root.method.name")
    public Map<Object, Object> getLogCount() {
        return remoteLogFeign.groupLogNumber().getData();
    }

    @Cacheable(cacheNames = "bussiness:index:login_count", key = "#root.method.name")
    public Integer getLoginCount() {
        R<LoginUser> r = remoteUserService.getUserInfo("admin", SecurityConstants.INNER);
        return r.getData().getSysUser().getLoginCount();
    }

    @Cacheable(cacheNames = "bussiness:index:oper_log", key = "#root.method.name")
    public List<SysOperLog> getSysOperLog() {
        R<List<SysOperLog>> listR = remoteLogService.selectNewOperLog(SecurityConstants.INNER);
        return listR.getData();
    }

    @Cacheable(cacheNames = "bussiness:index:english", key = "#root.method.name")
    public List<EnglishWordDTO> getEnglish() {
        R<List<EnglishWordDTO>> r = remoteEnglishFeign.getEnglishWordByRandom();
        return r.getData();
    }

    @Cacheable(cacheNames = "bussiness:index:news", key = "#root.method.name")
    public Map<Object, Object> getNews() {
        R<Map<Object, Object>> r = remoteWebmagicSinaFeign.getNews();
        return r.getData();
    }

    @SneakyThrows
    @Cacheable(cacheNames = "bussiness:index:beauty_picture", key = "#root.method.name")
    public Map<String, List<String>> getBeautyPictureList() {

        CompletableFuture<List<String>> oneFuture = CompletableFuture.supplyAsync(() ->
                apiToolsService.getBeautyPictureList().stream()
                        .map(ApiBeautyPicture::getImageUrl).collect(Collectors.toList()), executor);

        CompletableFuture<List<String>> twoFuture = CompletableFuture.supplyAsync(() ->
                //apiToolsService.getBeautyPictureList().stream()
                beautyPictureService.getRandomPicture().stream()
                        .map(ApiBeautyPicture::getImageUrl).collect(Collectors.toList()), executor);

        CompletableFuture<List<String>> threeFuture = CompletableFuture.supplyAsync(() ->
                //apiToolsService.getBeautyPictureList().stream()
                beautyPictureService.getRandomPicture().stream()
                        .map(ApiBeautyPicture::getImageUrl).collect(Collectors.toList()), executor);


        CompletableFuture.allOf(oneFuture, twoFuture, threeFuture).get();


        Map<String, List<String>> map = new HashMap<>();
        map.put("one", oneFuture.get());
        map.put("two", twoFuture.get());
        map.put("three", threeFuture.get());

        return map;
    }

    @Cacheable(cacheNames = "bussiness:index:ip_info", key = "#root.method.name")
    public IPInfoVo getIpInfo() {
        return ipService.getIPApiData();
    }


}
