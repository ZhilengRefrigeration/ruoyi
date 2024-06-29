package com.ruoyi.system.controller;

import java.util.Date;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.api.model.WxLoginUser;
import com.ruoyi.system.domain.BuildingInfoDetail;
import com.ruoyi.system.domain.vo.BuildingInfoRequest;
import com.ruoyi.system.domain.vo.BuildingInfoResponse;
import com.ruoyi.system.service.IBuildingInfoDetailService;
import com.ruoyi.system.utils.LoginUserUtil;
import io.seata.core.model.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.WxBuildingInfo;
import com.ruoyi.system.service.IWxBuildingInfoService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 球场管理Controller
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
@RestController
@RequestMapping("/wxBuildingInfo")
public class WxBuildingInfoController extends BaseController
{
    @Autowired
    private IWxBuildingInfoService wxBuildingInfoService;
    @Autowired
    private IBuildingInfoDetailService buildingInfoDetailService;

    /**
     * 查询球场管理列表
     */
//    @RequiresPermissions("system:WxBuilding:list")
    @GetMapping("/list")
    public TableDataInfo list(WxBuildingInfo wxBuildingInfo)
    {
        startPage();
        List<WxBuildingInfo> list = wxBuildingInfoService.selectWxBuildingInfoList(wxBuildingInfo);
        return getDataTable(list);
    }

    /**
     * 导出球场管理列表
     */
    @RequiresPermissions("system:WxBuilding:export")
    @Log(title = "球场管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxBuildingInfo wxBuildingInfo)
    {
        List<WxBuildingInfo> list = wxBuildingInfoService.selectWxBuildingInfoList(wxBuildingInfo);
        ExcelUtil<WxBuildingInfo> util = new ExcelUtil<WxBuildingInfo>(WxBuildingInfo.class);
        util.exportExcel(response, list, "球场管理数据");
    }

    /**
     * 获取球场管理详细信息
     */
    @RequiresPermissions("system:WxBuilding:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxBuildingInfoService.selectWxBuildingInfoById(id));
    }

    /**
     * 新增球场管理
     */
    @RequiresPermissions("system:WxBuilding:add")
    @Log(title = "球场管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxBuildingInfo wxBuildingInfo)
    {
        return toAjax(wxBuildingInfoService.insertWxBuildingInfo(wxBuildingInfo));
    }

    /**
     * 修改球场管理
     */
    @RequiresPermissions("system:WxBuilding:edit")
    @Log(title = "球场管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxBuildingInfo wxBuildingInfo)
    {
        return toAjax(wxBuildingInfoService.updateWxBuildingInfo(wxBuildingInfo));
    }

    /**
     * 删除球场管理
     */
    @RequiresPermissions("system:WxBuilding:remove")
    @Log(title = "球场管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxBuildingInfoService.deleteWxBuildingInfoByIds(ids));
    }

    @ApiOperation(ApiTerminal.wxMiniProgram+"保存")
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult save(@RequestBody BuildingInfoRequest entity) throws Exception {
        LoginUser user = SecurityUtils.getLoginUser();
        WxBuildingInfo buildingInfo=new WxBuildingInfo();
        BeanUtils.copyProperties(entity,buildingInfo);
        buildingInfo.setCreatedTime(new Date());
        buildingInfo.setCreatedBy(String.valueOf(user.getUserid()));
        if(!ObjectUtils.isEmpty(buildingInfo.getId())){
            wxBuildingInfoService.updateWxBuildingInfo(buildingInfo);
        }else{
            //设置球场创建人
            buildingInfo.setCreatedId(user.getUserid());
            wxBuildingInfoService.insertWxBuildingInfo(buildingInfo);
        }
        BuildingInfoDetail detail=new BuildingInfoDetail();
        BuildingInfoDetail details=new BuildingInfoDetail();
        details.setBuildingId(buildingInfo.getId());
        List<BuildingInfoDetail> detailList=buildingInfoDetailService.selectBuildingInfoDetailList(details);
        BeanUtils.copyProperties(entity,detail);
        if(detailList.size()>0){
            detail.setId(detailList.get(0).getId());
            detail.setBuildingId(buildingInfo.getId());
            buildingInfoDetailService.updateBuildingInfoDetail(detail);
        }else{
            detail.setId(null);
            if(ObjectUtils.isEmpty(entity.getOpenId())){
                detail.setCreatedBy("admin");
                detail.setCreatedTime(new Date());
            }
            detail.setBuildingId(buildingInfo.getId());
            buildingInfoDetailService.insertBuildingInfoDetail(detail);
        }
        return AjaxResult.success(Boolean.TRUE);
    }

    @ApiOperation(ApiTerminal.wxMiniProgram+"获取附近球场列表")
    @PostMapping("/findNearbyBuilding")
    @ResponseBody
    public TableDataInfo findNearbyBuilding(@RequestBody WxBuildingInfo entity) throws Exception {
        List<WxBuildingInfo> list = wxBuildingInfoService.findNearbyBuilding(entity);
        return getDataTable(list);
    }

    @PostMapping("/getBuildingByCity")
    @ApiOperation(ApiTerminal.wxMiniProgram+"获取球场列表")
    @ResponseBody
    public TableDataInfo getBuildingByCity(@RequestBody WxBuildingInfo entity){
        startPage();
        List<WxBuildingInfo> list = wxBuildingInfoService.getBuildingByCity(entity);
        return getDataTable(list);

    }

    @PostMapping("/getAuditList")
    @ResponseBody
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"获取审核球场列表")
    public TableDataInfo getAuditPage(@RequestBody BuildingInfoRequest entity){
        startPage();
        WxBuildingInfo buildingInfo=new WxBuildingInfo();
        BeanUtils.copyProperties(entity,buildingInfo);
        if(!ObjectUtils.isEmpty(entity.getOpenId())){
            buildingInfo.setCreatedBy(entity.getOpenId());
        }
        List<WxBuildingInfo> list =wxBuildingInfoService.getAuditPage(buildingInfo);
        return getDataTable(list);
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"根据ID查询场地明细信息")
    @GetMapping("/findAllInfoById/{id}")
    @ResponseBody
    public AjaxResult findAllInfoById(@PathVariable("id") Long id) throws Exception {
        BuildingInfoResponse entity = wxBuildingInfoService.findAllInfoById(id);
        return AjaxResult.success(entity);
    }
    @PostMapping("/getAllBuildingByCondition")
    @ResponseBody
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"分页获取球场列表")
    public TableDataInfo getAllBuildingByCondition(@RequestBody BuildingInfoRequest entity){
        startPage();
        List<BuildingInfoResponse> list =wxBuildingInfoService.getAllBuildingByCondition(entity);
        for(BuildingInfoResponse response:list){
            if(1==response.getStatus()){
                response.setStatusName("待审核");
            }else if(2==response.getStatus()){
                response.setStatusName("已审核");
            }else if(3==response.getStatus()){
                response.setStatusName("已拒绝");
            }
        }
        return getDataTable(list);
    }
}
