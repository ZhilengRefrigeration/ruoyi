package com.xjs.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.controller.BaseController;
import com.xjs.domain.ApiRecord;
import com.xjs.service.ApiWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiejs
 * @desc  api预警控制器
 * @create 2021-12-31
 */
@RestController
@RequestMapping("apiwarning")
public class ApiWarningController extends BaseController {

    @Autowired
    private ApiWarningService apiWarningService;

    /**
     * 保存 apiRecord
     * @param apiRecord api记录
     * @return apiRecord
     */
    @PostMapping
    public R<ApiRecord> saveApiRecord(@RequestBody ApiRecord apiRecord) {
        return apiWarningService.saveApiRecord(apiRecord) ? R.ok() : R.fail();
    }

    @PutMapping
    public R<ApiRecord> updateApiRecord(@RequestBody ApiRecord apiRecord) {
        return apiWarningService.updateApiRecord(apiRecord)? R.ok() : R.fail();
    }

}
