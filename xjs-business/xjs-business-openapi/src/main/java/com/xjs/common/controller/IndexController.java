package com.xjs.common.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.xjs.business.webmagic.RemoteWebmagicCopyWritingNetworkFeign;
import com.xjs.business.webmagic.domain.CopyWritingNetworkDTO;
import com.xjs.topsearch.domain.ApiTopsearchWeibo;
import com.xjs.topsearch.service.ApiTopsearchWeiboService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 主页控制器
 * @author xiejs
 * @since 2022-06-14
 */
@RestController
@RequestMapping("index")
@Api(tags = "业务模块-主页")
@Log4j2
public class IndexController {

    @Autowired
    private ApiTopsearchWeiboService apiTopsearchWeiboService;
    @Resource
    private RemoteWebmagicCopyWritingNetworkFeign remoteWebmagicCopyWritingNetworkFeign;

    @GetMapping("showWbSearch")
    @ApiOperation("展示微博热搜")
    public AjaxResult showWbSearch() {
        List<ApiTopsearchWeibo> weiboList = apiTopsearchWeiboService.showWbSearch();
        return AjaxResult.success(weiboList);
    }

    @GetMapping("showCopyWriting")
    @ApiOperation("首页展示文案")
    public AjaxResult showCopyWriting() {
        R<List<CopyWritingNetworkDTO>> data = remoteWebmagicCopyWritingNetworkFeign.showCopyWriting();
        return AjaxResult.success(data.getData());
    }


}
