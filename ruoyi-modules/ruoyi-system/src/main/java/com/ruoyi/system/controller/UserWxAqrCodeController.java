package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.WxBasketballTeam;
import com.ruoyi.system.domain.WxUser;
import com.ruoyi.system.domain.vo.UserWxAqrCodeVo;
import com.ruoyi.system.service.IWxBasketballTeamService;
import com.ruoyi.system.service.IWxUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.UserWxAqrCode;
import com.ruoyi.system.service.IUserWxAqrCodeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 微信用户小程序二维码Controller
 * 
 * @author ruoyi
 * @date 2022-10-18
 */
@RestController
@RequestMapping("/code")
public class UserWxAqrCodeController extends BaseController
{
    @Autowired
    private IUserWxAqrCodeService userWxAqrCodeService;
    @Autowired
    private IWxUserService wxUserService;
    @Autowired
    private IWxBasketballTeamService wxBasketballTeamService;

    /**
     * 查询微信用户小程序二维码列表
     */
    @RequiresPermissions("system:code:list")
    @GetMapping("/list")
    public TableDataInfo list(UserWxAqrCodeVo userWxAqrCode)
    {
        startPage();
        List<UserWxAqrCodeVo> list = userWxAqrCodeService.selectUserWxAqrCodeList(userWxAqrCode);
        return getDataTable(list);
    }

    /**
     * 导出微信用户小程序二维码列表
     */
    @RequiresPermissions("system:code:export")
    @Log(title = "微信用户小程序二维码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserWxAqrCodeVo userWxAqrCode)
    {
        List<UserWxAqrCodeVo> list = userWxAqrCodeService.selectUserWxAqrCodeList(userWxAqrCode);
        ExcelUtil<UserWxAqrCodeVo> util = new ExcelUtil<UserWxAqrCodeVo>(UserWxAqrCodeVo.class);
        util.exportExcel(response, list, "微信用户小程序二维码数据");
    }

    /**
     * 获取微信用户小程序二维码详细信息
     */
    @RequiresPermissions("system:code:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userWxAqrCodeService.selectUserWxAqrCodeById(id));
    }
    @RequiresPermissions("system:code:getUserAndTeams")
    @GetMapping(value = "/getUserAndTeams")
    public AjaxResult getUserAndTeam()
    {
        AjaxResult ajax = AjaxResult.success();
        WxUser wxUser = new WxUser();
        wxUser.setIsDeleted(0);
        List<WxUser> users = wxUserService.selectWxUserList(wxUser);
        WxBasketballTeam team = new WxBasketballTeam();
        team.setIsDeleted("0");
        List<WxBasketballTeam> teams = wxBasketballTeamService.selectWxBasketballTeamList(team);
        ajax.put("users", users);
        ajax.put("teams", teams);
        return ajax;
    }

    /**
     * 新增微信用户小程序二维码
     */
    @RequiresPermissions("system:code:add")
    @Log(title = "微信用户小程序二维码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserWxAqrCode userWxAqrCode)
    {
        return toAjax(userWxAqrCodeService.insertUserWxAqrCode(userWxAqrCode));
    }

    /**
     * 修改微信用户小程序二维码
     */
    @RequiresPermissions("system:code:edit")
    @Log(title = "微信用户小程序二维码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserWxAqrCode userWxAqrCode)
    {
        return toAjax(userWxAqrCodeService.updateUserWxAqrCode(userWxAqrCode));
    }
    @RequiresPermissions("system:code:genAqrCode")
    @Log(title = "微信用户小程序二维码", businessType = BusinessType.INSERT)
    @PostMapping("/genAqrCode")
    public AjaxResult genAqrCode(@RequestParam(required = false) Integer genNumbers, @RequestBody UserWxAqrCode userWxAqrCode)
    {
        return toAjax(userWxAqrCodeService.genAqrCode(genNumbers,userWxAqrCode));
    }

    /**
     * 删除微信用户小程序二维码
     */
    @RequiresPermissions("system:code:remove")
    @Log(title = "微信用户小程序二维码", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userWxAqrCodeService.deleteUserWxAqrCodeByIds(ids));
    }
}
