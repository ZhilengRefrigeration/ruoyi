package com.xjs.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.domain.ApiRecord;
import com.xjs.domain.ApiWarning;
import com.xjs.server.WebSocketServer;
import com.xjs.service.ApiWarningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.xjs.consts.ApiWarnHandleConst.NO;
import static com.xjs.consts.ApiWarnHandleConst.YES;
import static com.xjs.consts.RedisConst.WEBSOCKET;

/**
 * @author xiejs
 * @desc api预警控制器
 * @create 2021-12-31
 */
@RestController
@RequestMapping("apiwarning")
@Api(tags = "业务模块-预警管理")
public class ApiWarningController extends BaseController {

    @Autowired
    private ApiWarningService apiWarningService;
    @Autowired
    private RedisService redisService;

    /**
     * 远程保存 apiRecord
     *
     * @param apiRecord api记录
     * @return apiRecord
     */
    @PostMapping
    @ApiOperation("远程保存预警信息")
    public R<ApiRecord> saveApiRecordForRPC(@RequestBody ApiRecord apiRecord) {
        return apiWarningService.saveApiRecord(apiRecord) ? R.ok() : R.fail();
    }

    /**
     * 远程修改
     *
     * @param apiRecord api记录
     * @return ApiRecord
     */
    @PutMapping
    @ApiOperation("远程修改预警信息")
    public R<ApiRecord> updateApiRecordForRPC(@RequestBody ApiRecord apiRecord) {
        return apiWarningService.updateApiRecordByUrl(apiRecord) ? R.ok() : R.fail();
    }

    /**
     * 远程查询api记录信息
     *
     * @param apiRecord
     * @return R<List < ApiRecord>>
     */
    @GetMapping
    @ApiOperation("远程查询预警信息")
    public R<List<ApiRecord>> selectApiRecordListForRPC(ApiRecord apiRecord) {
        List<ApiRecord> apiRecords = apiWarningService.selectApiRecordListByUrl(apiRecord);
        return R.ok(apiRecords);
    }

    /**
     * 处理预警单个预警
     * @param id 预警id
     * @return R
     */
    @PutMapping("handle/{id}")
    @RequiresPermissions("warning:warning:handle")
    @ApiOperation("处理预警单个预警")
    @Log(title = "处理单个预警")
    public R<Object> handleWarning(@PathVariable("id") Long id) {
        ApiWarning apiWarning = new ApiWarning();
        apiWarning.setId(id);
        apiWarning.setHandle(YES);
        return apiWarningService.updateById(apiWarning)?R.ok():R.fail();
    }

    /**
     * 远程保存api预警信息并websocket推送
     *
     * @param apiWarning 预警实体类
     * @return R
     */
    @PostMapping("saveApiwarningForRPC")
    @Transactional
    @ApiOperation("远程保存api预警信息并websocket推送")
    public R<ApiWarning> saveApiWarningForRPC(@RequestBody ApiWarning apiWarning) {
        boolean save = apiWarningService.save(apiWarning);

        this.websocketPush(apiWarning);

        return save ? R.ok() : R.fail();
    }

    /**
     *  websocket推送
     */
    private void websocketPush(ApiWarning apiWarning) {
        long count = apiWarningService.count(new QueryWrapper<ApiWarning>().eq("handle",NO));
        Set<String> cacheSet = redisService.getCacheSet(WEBSOCKET);
        JSONObject jsonData =new JSONObject();
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(apiWarning);
        //把id设置成字符串防止前端精度丢失
        jsonObject.put("id", apiWarning.getId().toString());
        jsonData.put("count", count);
        jsonData.put("data", jsonObject.toJSONString());
        jsonData.put("socketType", "apiWarning");
        for (String userId : cacheSet) {
            try {
                WebSocketServer.sendInfo(jsonData.toString(),userId);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }


    /**
     * 查询api预警列表
     */
    @RequiresPermissions("warning:warning:list")
    @GetMapping("/apiwarnlist")
    @ApiOperation("查询api预警列表")
    public TableDataInfo list(ApiWarning apiWarning) {
        startPage();
        List<ApiWarning> list = apiWarningService.list(new QueryWrapper<ApiWarning>()
                .orderByDesc("create_time")
                .like(Objects.nonNull(apiWarning.getApiName()),"api_name", apiWarning.getApiName()));
        return getDataTable(list);
    }

    /**
     * 导出api预警列表
     */
    @RequiresPermissions("warning:warning:export")
    @Log(title = "api预警", businessType = BusinessType.EXPORT)
    @PostMapping("/apiwarnexport")
    @ApiOperation("导出api预警列表")
    public void export(HttpServletResponse response, ApiWarning apiWarning) {
        List<ApiWarning> list = apiWarningService.list(new QueryWrapper<ApiWarning>()
                .like(Objects.nonNull(apiWarning.getApiName()),"api_name", apiWarning.getApiName()));
        ExcelUtil<ApiWarning> util = new ExcelUtil<ApiWarning>(ApiWarning.class);
        util.exportExcel(response, list, "api预警数据");
    }


    //-------------------------代码生成------------------------------------


    /**
     * 查询API预警信息列表
     */
    @RequiresPermissions("warning:apiwarning:list")
    @GetMapping("/list")
    @ApiOperation("查询API预警信息列表")
    public TableDataInfo list(ApiRecord apiRecord) {
        startPage();
        List<ApiRecord> list = apiWarningService.selectApiRecordList(apiRecord);
        return getDataTable(list);
    }

    /**
     * 导出API预警信息列表
     */
    @RequiresPermissions("warning:apiwarning:export")
    @Log(title = "API预警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出API预警信息列表")
    public void export(HttpServletResponse response, ApiRecord apiRecord) {
        List<ApiRecord> list = apiWarningService.selectApiRecordList(apiRecord);
        ExcelUtil<ApiRecord> util = new ExcelUtil<ApiRecord>(ApiRecord.class);
        util.exportExcel(response, list, "API预警数据");
    }

    /**
     * 获取API预警详细信息信息
     */
    @RequiresPermissions("warning:apiwarning:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取API预警详细信息信息")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(apiWarningService.selectApiRecordById(id));
    }

    /**
     * 修改API预警信息
     */
    @RequiresPermissions("warning:apiwarning:edit")
    @Log(title = "API预警", businessType = BusinessType.UPDATE)
    @PutMapping("edit")
    @ApiOperation("修改API预警信息")
    public AjaxResult edit(@RequestBody ApiRecord apiRecord) {
        return toAjax(apiWarningService.updateApiRecord(apiRecord));
    }

}
