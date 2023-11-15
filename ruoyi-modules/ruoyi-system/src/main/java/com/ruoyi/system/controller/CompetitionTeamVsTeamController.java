package com.ruoyi.system.controller;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.exception.CheckedException;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.domain.CompetitionResult;
import com.ruoyi.system.domain.CompetitionTeamVsTeam;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.service.ICompetitionTeamVsTeamService;
import com.ruoyi.system.utils.UtilTool;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 赛会中-球队VS球队关系Controller
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@RestController
@RequestMapping("/competitionTeamVsTeam")
public class CompetitionTeamVsTeamController extends BaseController
{
    @Autowired
    private ICompetitionTeamVsTeamService competitionTeamVsTeamService;

    /**
     * 查询赛会中-球队VS球队关系列表
     */
    @RequiresPermissions("system:competitionTeamVsTeam:list")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        startPage();
        List<CompetitionTeamVsTeamVo> list = competitionTeamVsTeamService.selectCompetitionTeamVsTeamList(competitionTeamVsTeam);
        return getDataTable(list);
    }

    /**
     * 导出赛会中-球队VS球队关系列表
     */
    @RequiresPermissions("system:competitionTeamVsTeam:export")
    @Log(title = "赛会中球队VS球队关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        List<CompetitionTeamVsTeamVo> list = competitionTeamVsTeamService.selectCompetitionTeamVsTeamList(competitionTeamVsTeam);
        ExcelUtil<CompetitionTeamVsTeamVo> util = new ExcelUtil<CompetitionTeamVsTeamVo>(CompetitionTeamVsTeamVo.class);
        util.exportExcel(response, list, "赛会中-球队VS球队关系数据");
    }

    /**
     * 获取赛会中-球队VS球队关系详细信息
     */
    @RequiresPermissions("system:competitionTeamVsTeam:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionTeamVsTeamService.selectCompetitionTeamVsTeamById(id));
    }

    /**
     * 新增赛会中-球队VS球队关系
     */
    @RequiresPermissions("system:competitionTeamVsTeam:add")
    @Log(title = "赛会中球队VS球队关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        return toAjax(competitionTeamVsTeamService.insertCompetitionTeamVsTeam(competitionTeamVsTeam));
    }

    /**
     * 修改赛会中-球队VS球队关系
     */
    @RequiresPermissions("system:competitionTeamVsTeam:edit")
    @Log(title = "赛会中球队VS球队关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionTeamVsTeam competitionTeamVsTeam)
    {
        return toAjax(competitionTeamVsTeamService.updateCompetitionTeamVsTeam(competitionTeamVsTeam));
    }

    /**
     * 删除赛会中-球队VS球队关系
     */
    @RequiresPermissions("system:competitionTeamVsTeam:remove")
    @Log(title = "赛会中球队VS球队关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionTeamVsTeamService.deleteCompetitionTeamVsTeamByIds(ids));
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"根据IDS批量删除")
    @DeleteMapping("/batchDeleteByIds")
    @ResponseBody
    public AjaxResult batchDeleteByIds(@RequestBody Ids ids) throws Exception {
        if(ObjectUtil.isNull(ids)){
            throw new InvalidParameterException("参数异常，非法操作！");
        }
        if(StringUtils.isEmpty(ids.getIdList())||ids.getIdList().size()==0){
            throw new InvalidParameterException("ids不能为空！");
        }
        return AjaxResult.success(competitionTeamVsTeamService.deleteBatchByIds(ids));
    }
    @Log(title = "赛会中球队VS球队比赛结果数据", businessType = BusinessType.OTHER)
    @ApiOperation("根据ID获取当前比赛赛程的所有统分结果")
    @GetMapping("/competitionUnifiedRecord/{id}")
    public AjaxResult getCompetitionUnifiedRecordById(@PathVariable("id") Long id) {
        return AjaxResult.success(competitionTeamVsTeamService.getCompetitionUnifiedRecordById(id));
    }

    @Log(title = "赛会中球队VS球队比赛结果数据2", businessType = BusinessType.OTHER)
    @ApiOperation(ApiTerminal.wxMiniProgram+"根据ID获取当前比赛赛程的所有统分结果2")
    @GetMapping("/getCompetitionVsRecordById/{id}")
    public AjaxResult getCompetitionVsRecordById(@PathVariable("id") Long id) {
        return AjaxResult.success(competitionTeamVsTeamService.getCompetitionVsRecordById(id));
    }

    @PostMapping("/getCompetitionSchedule")
    @ResponseBody
    @ApiOperation(ApiTerminal.wxMiniProgram+"分页获取赛事-赛程列表")
    public TableDataInfo getCompetitionSchedule(@RequestBody CompetitionTeamVsTeam entity) throws ParseException {
        startPage();
        //关键字word包含：球队名称、地点名称、球馆名称，支持模糊搜索；
        List<CompetitionTeamVsTeamRequest> listMap = new ArrayList<>();
        entity.setIsDeleted(0);
        Map<String, List<CompetitionTeamVsTeamVo>> map = competitionTeamVsTeamService.getCompetitionSchedule(entity);
        for(Map.Entry<String, List<CompetitionTeamVsTeamVo>> entry:map.entrySet()){
            CompetitionTeamVsTeamRequest request =new CompetitionTeamVsTeamRequest();
            request.setCompetitionDate(entry.getKey());
            request.setWeekDay(UtilTool.dateToWeek(entry.getKey()));
            request.setTeamVsTeamList(entry.getValue());
            request.setCompetitionId(entity.getCompetitionId());
            request.setIsDisabled(UtilTool.compareToDate(entry.getKey(),new Date()));
            listMap.add(request);
        }
        return getDataTable(listMap);
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"根据赛会ID获取所有球队的积分列表")
    @GetMapping("/competitionTeamIntegralList/{id}")
    @ResponseBody
    public TableDataInfo competitionTeamIntegralList(@PathVariable("id") Long id) throws Exception {
        CompetitionTeamIntegralVo vo = new CompetitionTeamIntegralVo();
        vo.setCompetitionId(id);
        List<CompetitionTeamIntegralVo> list = competitionTeamVsTeamService.getCompetitionTeamIntegralListById(vo);
        return getDataTable(list);
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"根据赛会ID获取所有球队的积分排位")
    @PostMapping("/competitionTeamIntegralRanking")
    @ResponseBody
    public TableDataInfo competitionTeamIntegralRanking(@RequestBody CompetitionTeamIntegralVo vo) throws Exception {
        List<competitionTeamIntegralRankingVo> rankingVoList = new ArrayList<>();
        List<CompetitionTeamIntegralVo> list = competitionTeamVsTeamService.getCompetitionTeamIntegralListById(vo);
        Map<String, List<CompetitionTeamIntegralVo>> map = new HashMap<>();
        if(list.size()>0) {
            List<CompetitionTeamIntegralVo> notGroup = list.stream().filter(a -> ObjectUtil.isNull(a.getCompetitionGroup())).collect(Collectors.toList());
            List<CompetitionTeamIntegralVo> yesGroup = list.stream().filter(a -> ObjectUtil.isNotNull(a.getCompetitionGroup())).collect(Collectors.toList());
            if(yesGroup.size()>0) {
                map = yesGroup.stream().collect(
                        Collectors.groupingBy(CompetitionTeamIntegralVo::getCompetitionGroup, HashMap::new,
                                Collectors.collectingAndThen(Collectors.toList(),
                                        //正序
                                        //list1 -> list1.stream().sorted(Comparator.comparing(CompetitionTeamIntegralVo::getIntegral))
                                                //倒序
                                                list1 -> list1.stream().sorted(Comparator.comparing(CompetitionTeamIntegralVo::getIntegral).reversed()
                                                                .thenComparing(CompetitionTeamIntegralVo::getNetWinPoint,Comparator.reverseOrder()))
                                                .collect(Collectors.toList())
                                )));
                for (String key:map.keySet()){
                    competitionTeamIntegralRankingVo rankingVo = new competitionTeamIntegralRankingVo();
                    rankingVo.setCompetitionGroup(key);
                    rankingVo.setIntegralList(map.get(key));
                    rankingVoList.add(rankingVo);
                }
            }
            if(notGroup.size()>0){
                competitionTeamIntegralRankingVo rankingVo = new competitionTeamIntegralRankingVo();
                rankingVo.setCompetitionGroup("未分");
                rankingVo.setIntegralList(notGroup.stream().sorted(Comparator.comparing(CompetitionTeamIntegralVo::getIntegral).reversed()
                .thenComparing(CompetitionTeamIntegralVo::getNetWinPoint,Comparator.reverseOrder())).collect(Collectors.toList()));
                rankingVoList.add(rankingVo);
            }
        }
        return getDataTable(rankingVoList);
    }

    @PostMapping("/competitionScheduleSubmit")
    @ResponseBody
    @ApiOperation(value = "赛事-比赛赛程提交")
    public AjaxResult competitionScheduleSubmit(@RequestBody List<CompetitionTeamVsTeamRequest> vsTeamRequestList){
        return AjaxResult.success(competitionTeamVsTeamService.competitionScheduleSubmit(vsTeamRequestList));
    }
    /**
     * 赛事队伍积分提交
     * @param request
     * @return
     */
    @PostMapping("/competitionScoreSubmit")
    @ResponseBody
    @ApiOperation(value = "赛事队伍积分提交")
    public AjaxResult competitionScoreSubmit(@RequestBody List<CompetitionResult> request){
        if(UtilTool.isNull(request)){
            throw new CheckedException("参数不能为空");
        }
        return AjaxResult.success(competitionTeamVsTeamService.competitionScoreSubmit(request));
    }
}
