package com.ruoyi.system.controller;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.util.StringUtil;
import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.domain.UserRole;
import com.ruoyi.system.domain.vo.PersonalCareerVo;
import com.ruoyi.system.domain.vo.UserInfoResponse;
import com.ruoyi.system.service.ICompetitionMembersScoreService;
import com.ruoyi.system.service.IDataDictionaryService;
import com.ruoyi.system.service.IUserRoleService;
import com.ruoyi.system.utils.AgeUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.WxUser;
import com.ruoyi.system.service.IWxUserService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 微信用户Controller
 * 
 * @author 吴一博
 * @date 2022-08-30
 */
@RestController
@RequestMapping("/wxUser")
public class WxUserController extends BaseController
{
    @Autowired
    private IWxUserService wxUserService;
    @Autowired
    private IDataDictionaryService dataDictionaryService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private ICompetitionMembersScoreService competitionMembersScoreService;
    /**
     * 查询微信用户列表
     */
    @RequiresPermissions("system:wxUser:list")
    @GetMapping("/list")
    public TableDataInfo list(WxUser wxUser)
    {
        startPage();
        List<WxUser> list = wxUserService.selectWxUserList(wxUser);
        return getDataTable(list);
    }

    /**
     * 导出微信用户列表
     */
    @RequiresPermissions("system:wxUser:export")
    @Log(title = "微信用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxUser wxUser)
    {
        List<WxUser> list = wxUserService.selectWxUserList(wxUser);
        ExcelUtil<WxUser> util = new ExcelUtil<WxUser>(WxUser.class);
        util.exportExcel(response, list, "微信用户数据");
    }

    /**
     * 获取微信用户详细信息
     */
    @RequiresPermissions("system:wxUser:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxUserService.selectWxUserById(id));
    }

    /**
     * 新增微信用户
     */
    @RequiresPermissions("system:wxUser:add")
    @Log(title = "微信用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxUser wxUser)
    {
        return toAjax(wxUserService.insertWxUser(wxUser));
    }

    /**
     * 修改微信用户
     */
    @RequiresPermissions("system:wxUser:edit")
    @Log(title = "微信用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxUser wxUser)
    {
        return toAjax(wxUserService.updateWxUser(wxUser));
    }

    /**
     * 删除微信用户
     */
    @RequiresPermissions("system:wxUser:remove")
    @Log(title = "微信用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxUserService.deleteWxUserByIds(ids));
    }

    @ApiOperation(ApiTerminal.wxMiniProgram+"根据用户id修改个人数据")
    @PostMapping(value = "/edit")
    @ResponseBody
    public AjaxResult editInfo(@RequestBody WxUser wxUser)
    {
        return AjaxResult.success(wxUserService.updateWxUser(wxUser));
    }

    @ApiOperation(ApiTerminal.wxMiniProgram+"根据用户手机号查询个人数据")
    @PostMapping(value = "/getUserInfoBy")
    @ResponseBody
    public AjaxResult getUserInfoBy(@RequestBody WxUser wxUser)
    {
        return AjaxResult.success(wxUserService.getUserInfoBy(wxUser));
    }


    @ApiOperation(ApiTerminal.wxMiniProgram+"根据用户id查询个人中心详情")
    @PostMapping("/detail/{userId}")
    @ResponseBody
    public AjaxResult detail(@PathVariable("userId") Long userId){
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        //查询用户基本信息
        WxUser userInfo = wxUserService.selectWxUserById(userId);
        if(userInfo==null){
            throw new InvalidParameterException("根据传入的userId【"+userId+"】未查询到用户信息");
        }
        //赋值
        BeanUtils.copyProperties(userInfo,userInfoResponse);

        if(ObjectUtils.isNotEmpty(userInfo.getBirthday())){
            //根据日期计算年龄
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String birthTimeString = format.format(userInfo.getBirthday());
            userInfoResponse.setAge(AgeUtils.getAgeFromBirthTime(birthTimeString));
        }

        //球队位置组装
        if(userInfo.getTeamPosition()!=null){
            Map<String, String> teamPositionMap = dataDictionaryService.getChildByParentKey("teamPosition");
            String[] teamPositionArgs = userInfo.getTeamPosition().split(",");
            if(teamPositionArgs!=null&&teamPositionArgs.length>0){
                List<String> teamPositionName = new ArrayList<>();
                for(String teamPosition : teamPositionArgs){
                    teamPositionName.add(teamPositionMap.get(teamPosition));
                }
                userInfoResponse.setTeamPositionName(teamPositionName);
            }
        }

        //标签数据组装
        if(userInfo.getTag()!=null){
            Map<String, String>  tagMap = dataDictionaryService.getChildByParentKey("tag");
            String[] tagArgs = userInfo.getTag().split(",");
            if(tagArgs!=null&&tagArgs.length>0){
                List<String> tagName = new ArrayList<>();
                for(String tag : tagArgs){
                    tagName.add(tagMap.get(tag));
                }
                userInfoResponse.setTagName(tagName);
            }
        }

        //查询此用户的角色有哪些
        UserRole userRole=new UserRole();
        userRole.setUserId(userId);
        List<UserRole> userRoles=userRoleService.selectUserRoleList(userRole);
        if(!StringUtils.isEmpty(userRoles)&&userRoles.size()>0){
            userInfoResponse.setRoleCodes(userRoles.stream().map(UserRole::getRoleCode).collect(Collectors.toList()));
        }

        //个人生涯
        PersonalCareerVo personalCareerVo = competitionMembersScoreService.getUserScoreByUserId(userId);
        if(personalCareerVo==null){
            personalCareerVo = new PersonalCareerVo();
        }
        userInfoResponse.setPersonalCareerVo(personalCareerVo);
        return AjaxResult.success(userInfoResponse);
    }
}
