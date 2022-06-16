package com.xjs;

import cn.hutool.core.map.MapUtil;
import com.ruoyi.common.core.domain.R;
import com.xjs.apilog.service.IApiLogService;
import com.xjs.maillog.service.MailLogService;
import com.xjs.other.LogNumberVo;
import com.xjs.reptileLog.service.WebmagicLogService;
import com.xjs.tasklog.service.TaskLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通用日志控制器
 * @author xiejs
 * @since 2022-06-15
 */
@RestController
@RequestMapping("log")
@Api(tags = "业务模块-通用日志")
public class CommonLogController {
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @Autowired
    private IApiLogService apiLogService;
    @Autowired
    private MailLogService mailLogService;
    @Autowired
    private WebmagicLogService webmagicLogService;
    @Autowired
    private TaskLogService taskLogService;

    //---------------------------内部调用----------------------------------------------

    @GetMapping("groupLogNumberForRPC")
    @ApiOperation("聚合日志次数")
    public R<Map<Object, Object>> groupLogNumber() throws ExecutionException, InterruptedException {
        CompletableFuture<LogNumberVo> apiLogFuture = CompletableFuture.supplyAsync(() ->
                apiLogService.getCount(), executor);

        CompletableFuture<LogNumberVo> mailLogFuture = CompletableFuture.supplyAsync(() ->
                mailLogService.getCount(), executor);

        CompletableFuture<LogNumberVo> webmagicLogFuture = CompletableFuture.supplyAsync(() ->
                webmagicLogService.getCount(), executor);

        CompletableFuture<LogNumberVo> taskLogFuture = CompletableFuture.supplyAsync(() ->
                taskLogService.getCount(), executor);

        CompletableFuture.allOf(apiLogFuture,mailLogFuture,webmagicLogFuture,taskLogFuture).get();

        Map<Object, Object> map = MapUtil.builder()
                .put("apiLog",apiLogFuture.get())
                .put("mailLog",mailLogFuture.get())
                .put("webmagicLog",webmagicLogFuture.get())
                .put("taskLog",taskLogFuture.get())
                .build();
        return R.ok(map);
    }


}
