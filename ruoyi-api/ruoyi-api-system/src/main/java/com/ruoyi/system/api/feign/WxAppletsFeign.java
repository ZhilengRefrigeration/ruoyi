package com.ruoyi.system.api.feign;
import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * <B>系统名称：future篮球后台系统系统</B><BR>
 * <B>模块名称：BASKETBALL-FEIGN</B><BR>
 * <B>中文类名：微信小程序专用 FEIGN</B><BR>
 * <B>概要说明：微信小程序专用 FEIGN</B><BR>
 * <B>@version：v1.0</B><BR>
 * <B>版本		修改人		备注</B><BR>
 * @author : wyb
 * @date   : 2019年10月19日
 */
@FeignClient(value = "basketball-service", path = "/wxApplets")
public interface WxAppletsFeign {
    /**
     * 获取access_token
     * appid和appsecret到小程序后台获取，当然也可以让小程序开发人员给你传过来
     * */
    @GetMapping("/getAccessToken")
    String getAccessToken();

    @PostMapping("/getWxacodeunlimit")
    @ApiOperation("微信小程序-获取小程序码")
    public WxAppletsCodeVo getWxacodeunlimit(@RequestBody WxAppletsCodeVo wxAppletsCodeVo, @RequestParam("accessToken") String accessToken);

}