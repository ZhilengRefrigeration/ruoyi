package com.ruoyi.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.domain.CompetitionOfTeam;
import com.ruoyi.system.domain.vo.CompetitionOfTeamVo;
import com.ruoyi.system.domain.vo.CompetitionTeamGroupVo;
import com.ruoyi.system.domain.vo.TeamGroupRequest;
import com.ruoyi.system.service.ICompetitionOfTeamService;
import com.ruoyi.system.utils.LoginUserUtil;
import io.seata.core.model.Result;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.CompetitionTeamGroup;
import com.ruoyi.system.service.ICompetitionTeamGroupService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 赛会中-分组Controller
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@RestController
@RequestMapping("/competitionTeamGroup")
public class CompetitionTeamGroupController extends BaseController
{
    @Autowired
    private ICompetitionTeamGroupService competitionTeamGroupService;
    @Autowired
    private ICompetitionOfTeamService competitionOfTeamService;

    /**
     * 查询赛会中-分组列表
     */
    @RequiresPermissions("system:competitionTeamGroup:list")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionTeamGroup competitionTeamGroup)
    {
        startPage();
        List<CompetitionTeamGroup> list = competitionTeamGroupService.selectCompetitionTeamGroupList(competitionTeamGroup);
        return getDataTable(list);
    }

    /**
     * 导出赛会中-分组列表
     */
    @RequiresPermissions("system:competitionTeamGroup:export")
    @Log(title = "赛会中-分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompetitionTeamGroup competitionTeamGroup)
    {
        List<CompetitionTeamGroup> list = competitionTeamGroupService.selectCompetitionTeamGroupList(competitionTeamGroup);
        ExcelUtil<CompetitionTeamGroup> util = new ExcelUtil<CompetitionTeamGroup>(CompetitionTeamGroup.class);
        util.exportExcel(response, list, "赛会中-分组数据");
    }

    /**
     * 获取赛会中-分组详细信息
     */
    @RequiresPermissions("system:competitionTeamGroup:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionTeamGroupService.selectCompetitionTeamGroupById(id));
    }

    /**
     * 新增赛会中-分组
     */
    @RequiresPermissions("system:competitionTeamGroup:add")
    @Log(title = "赛会中-分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionTeamGroup competitionTeamGroup)
    {
        return toAjax(competitionTeamGroupService.insertCompetitionTeamGroup(competitionTeamGroup));
    }

    /**
     * 修改赛会中-分组
     */
    @RequiresPermissions("system:competitionTeamGroup:edit")
    @Log(title = "赛会中-分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionTeamGroup competitionTeamGroup)
    {
        return toAjax(competitionTeamGroupService.updateCompetitionTeamGroup(competitionTeamGroup));
    }
    //@RequiresPermissions("system:competitionTeamGroup:arrangeTeamGroupSchedule")
    @Log(title = "赛会中-一键编排分组内的球队的单组循环赛赛程", businessType = BusinessType.OTHER)
    @PostMapping("/arrangeTeamGroupSchedule")
    public AjaxResult arrangeTeamGroupSchedule(@RequestBody CompetitionTeamGroup competitionTeamGroup)
    {
        return toAjax(competitionTeamGroupService.arrangeTeamGroupSchedule(competitionTeamGroup));
    }
    /**
     * 删除赛会中-分组
     */
    @RequiresPermissions("system:competitionTeamGroup:remove")
    @Log(title = "赛会中-分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionTeamGroupService.deleteCompetitionTeamGroupByIds(ids));
    }



    @PostMapping("/getTeamGroupByCondition")
    @ResponseBody
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"分页获取比赛分组列表")
    public TableDataInfo getTeamGroupByCondition(@RequestBody CompetitionTeamGroup entity){
        startPage();
        entity.setIsDeleted(0);
        List<CompetitionTeamGroup> list =competitionTeamGroupService.getTeamGroupByCondition(entity);
        return getDataTable(list);
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"新增")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addCompetitionTeamGroup(@RequestBody CompetitionTeamGroup entity)  {
        return AjaxResult.success(competitionTeamGroupService.add(entity));
    }
    @PostMapping("/getJoinCompetitionGroupTeam")
    @ResponseBody
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"获取赛事中参与的球队的分组数据")
    public AjaxResult getJoinCompetitionGroupTeam(@RequestBody CompetitionTeamGroup entity){
        if(StringUtils.isEmpty(entity)){
            throw new ServiceException("参数异常，非法操作！");
        }
        if(StringUtils.isEmpty(entity.getCompetitionId())){
            throw new ServiceException("CompetitionId为空！");
        }

        entity.setIsDeleted(0);
        List<CompetitionTeamGroup> groupList =competitionTeamGroupService.getTeamGroupByCondition(entity);
        CompetitionTeamGroupVo competitionTeamGroupVo = new CompetitionTeamGroupVo();
        CompetitionOfTeam ofTeam =new CompetitionOfTeam();
        ofTeam.setCompetitionId(entity.getCompetitionId());
        ofTeam.setStatus(1);
        List<CompetitionOfTeamVo> list = competitionOfTeamService.getJoinCompetitionGroupTeam(ofTeam);
        List<CompetitionOfTeamVo> isNotGroupList = list.stream().filter(a -> StringUtils.isEmpty(a.getCompetitionGroup())).collect(Collectors.toList());
        List<CompetitionOfTeamVo> list1 = list.stream().filter(a -> !StringUtils.isEmpty(a.getCompetitionGroup())).collect(Collectors.toList());
        //1.根据字符串类型日期分组，并按照日期升序排序，返回TreeMap<String,List>，map的key为字符串日期，value为list
        TreeMap<String,List<CompetitionOfTeamVo>> dataGroupMap =  list1.stream().collect(Collectors.groupingBy(a->a.getCompetitionGroup(), TreeMap::new,Collectors.toList()));
        List<CompetitionTeamGroupVo.IsGroupBean> listMap = new ArrayList<>();

//        for(Map.Entry<String, List<CompetitionOfTeamVo>> entry:dataGroupMap.entrySet()){
//            CompetitionTeamGroupVo.IsGroupBean resMap =new CompetitionTeamGroupVo.IsGroupBean();
//
//
//            resMap.setCompetitionGroup(entry.getKey());
//            resMap.setCompetitionOfTeamList(entry.getValue());
//            listMap.add(resMap);
//        }

        if(groupList.size()>0){
            for (CompetitionTeamGroup teamGroup:groupList) {
                CompetitionTeamGroupVo.IsGroupBean resMap =new CompetitionTeamGroupVo.IsGroupBean();
                resMap.setCompetitionGroup(teamGroup.getCompetitionGroup());
                resMap.setCompetitionOfTeamList(dataGroupMap.get(teamGroup.getCompetitionGroup()));
                listMap.add(resMap);
            }
        }

        competitionTeamGroupVo.setIsGroup(listMap);
        competitionTeamGroupVo.setIsNotGroup(isNotGroupList);
        return AjaxResult.success(competitionTeamGroupVo);
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"批量设置球队分组数据")
    @PostMapping("/batchEditTeamGroup")
    @ResponseBody
    public AjaxResult batchEditTeamGroup(@RequestBody TeamGroupRequest entity) throws Exception {
        if(StringUtils.isEmpty(entity)){
            throw new ServiceException("参数异常，非法操作！");
        }
        if(StringUtils.isEmpty(entity.getGroup())){
            throw new ServiceException("Group为空！");
        }if(StringUtils.isEmpty(entity.getOfTeamIds())||entity.getOfTeamIds().size()==0){
            throw new ServiceException("ids为空！");
        }
        return AjaxResult.success(competitionTeamGroupService.batchEditTeamGroup(entity));
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"随机设置球队分组数据")
    @PostMapping("/randomTeamGroup")
    @ResponseBody
    public AjaxResult randomTeamGroup(@RequestBody List<TeamGroupRequest> list, @RequestParam("competitionId") Long competitionId) throws Exception {
        if(StringUtils.isEmpty(list)||list.size()==0){
            throw new ServiceException("参数异常，非法操作！");
        }
        if(StringUtils.isEmpty(competitionId)){
            throw new ServiceException("competitionId为空，非法操作！");
        }
        for (TeamGroupRequest teamGroupRequest: list) {
            if(StringUtils.isEmpty(teamGroupRequest.getGroup())){
                throw new ServiceException("group为空！");
            }if(StringUtils.isEmpty(teamGroupRequest.getOfTeamIds())||teamGroupRequest.getOfTeamIds().size()==0){
                throw new ServiceException("ofTeamIds为空！");
            }
        }

        return AjaxResult.success(competitionTeamGroupService.randomTeamGroup(list,competitionId));
    }
}
