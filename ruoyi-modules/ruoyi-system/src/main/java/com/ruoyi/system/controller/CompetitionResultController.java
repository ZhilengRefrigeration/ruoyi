package com.ruoyi.system.controller;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.domain.CompetitionMembersScore;
import com.ruoyi.system.domain.vo.CompetitionVsRecordVo;
import com.ruoyi.system.domain.vo.PersonalHonorResponse;
import com.ruoyi.system.service.ICompetitionMembersScoreService;
import com.ruoyi.system.utils.UtilTool;
import io.seata.core.model.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.CompetitionResult;
import com.ruoyi.system.service.ICompetitionResultService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 赛会中-赛程结果记录Controller
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@RestController
@RequestMapping("/competitionResult")
public class CompetitionResultController extends BaseController
{
    @Autowired
    private ICompetitionResultService competitionResultService;
    @Autowired
    private ICompetitionMembersScoreService competitionMembersScoreService;

    /**
     * 查询赛会中-赛程结果记录列表
     */
    @RequiresPermissions("system:competitionResult:list")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionResult competitionResult)
    {
        startPage();
        List<CompetitionResult> list = competitionResultService.selectCompetitionResultList(competitionResult);
        return getDataTable(list);
    }

    /**
     * 导出赛会中-赛程结果记录列表
     */
    @RequiresPermissions("system:competitionResult:export")
    @Log(title = "赛会中赛程结果记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompetitionResult competitionResult)
    {
        List<CompetitionResult> list = competitionResultService.selectCompetitionResultList(competitionResult);
        ExcelUtil<CompetitionResult> util = new ExcelUtil<CompetitionResult>(CompetitionResult.class);
        util.exportExcel(response, list, "赛会中赛程结果记录数据");
    }

    /**
     * 获取赛会中-赛程结果记录详细信息
     */
    @RequiresPermissions("system:competitionResult:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionResultService.selectCompetitionResultById(id));
    }

    /**
     * 新增赛会中-赛程结果记录
     */
    @RequiresPermissions("system:competitionResult:add")
    @Log(title = "赛会中赛程结果记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionResult competitionResult)
    {
        return toAjax(competitionResultService.insertCompetitionResult(competitionResult));
    }

    /**
     * 修改赛会中-赛程结果记录
     */
    @RequiresPermissions("system:competitionResult:edit")
    @Log(title = "赛会中赛程结果记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionResult competitionResult)
    {
        return toAjax(competitionResultService.updateCompetitionResult(competitionResult));
    }
    @RequiresPermissions("system:competitionResult:batchEdit")
    @Log(title = "赛会中批量保存赛程结果记录", businessType = BusinessType.UPDATE)
    @PutMapping("/batchEdit")
    public AjaxResult batchEdit(@RequestBody List<CompetitionResult> list)
    {
        return toAjax(competitionResultService.batchUpdateCompetitionResult(list));
    }
    @RequiresPermissions("system:competitionResult:editData")
    @Log(title = "赛会中保存赛程结果记录2", businessType = BusinessType.UPDATE)
    @PutMapping("/editData")
    public AjaxResult editData(@RequestBody CompetitionVsRecordVo obj)
    {
        return toAjax(competitionResultService.editData(obj));
    }

    /**
     * 删除赛会中-赛程结果记录
     */
    @RequiresPermissions("system:competitionResult:remove")
    @Log(title = "赛会中赛程结果记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionResultService.deleteCompetitionResultByIds(ids));
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"球员数据-新增、编辑")
    @PostMapping("/insertOrUpdateMemberScore")
    @ResponseBody
    public AjaxResult insertOrUpdateMemberScore(@RequestBody CompetitionMembersScore request) throws Exception {
        if(UtilTool.isNull(request)){
            throw new ServiceException("参数不能为空1");
        }

        //新增
        if(request.getId()==null){
            request.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserid()));
            request.setCreatedTime(new Date());
            competitionMembersScoreService.insertCompetitionMembersScore(request);
        } else {//编辑、
            if(StringUtils.isEmpty(request.getId())){
                throw new ServiceException("id为空");
            }
            request.setLastUpdatedTime(new Date());
            request.setModifiedBy(String.valueOf(SecurityUtils.getLoginUser().getUserid()));
            competitionMembersScoreService.updateCompetitionMembersScore(request);
        }
        return AjaxResult.success();
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"赛会个人荣誉榜")
    @GetMapping("/getHonorList/{competitionId}")
    @ResponseBody
    public TableDataInfo getHonorList(@PathVariable("competitionId") Long competitionId) throws Exception {
        if(UtilTool.isNull(competitionId)){
            throw new InvalidParameterException("赛会id不能为");
        }
        List<PersonalHonorResponse> honorResponseList = new ArrayList<>();

        //查询赛会得分数据
        List<CompetitionMembersScore> membersScoreList = competitionMembersScoreService.getHonorList(competitionId,null);
        if(UtilTool.isNotNull(membersScoreList)){
            honorResponseList = new ArrayList<>();

            CompetitionMembersScore membersScore = null;
            //3分王
            membersScore = membersScoreList.stream().max(Comparator.comparing(CompetitionMembersScore::getThreePoints)).get();
            setHonorData(honorResponseList,membersScore,"3分王");
            //篮板王
            membersScore = membersScoreList.stream().max(Comparator.comparing(CompetitionMembersScore::getBackboard)).get();
            setHonorData(honorResponseList,membersScore,"篮板王");
            //效率王
//                membersScore = membersScoreList.stream().max(Comparator.comparing(CompetitionMembersScore::getThreePoints)).get();
//                setHonorData(membersScore,"效率王");
            //助攻王
            membersScore = membersScoreList.stream().max(Comparator.comparing(CompetitionMembersScore::getAssists)).get();
            setHonorData(honorResponseList,membersScore,"助攻王");
            //得分王
            membersScore = membersScoreList.stream().max(Comparator.comparing(CompetitionMembersScore::getTotalScore)).get();
            setHonorData(honorResponseList,membersScore,"得分王");
            //盖帽王
            membersScore = membersScoreList.stream().max(Comparator.comparing(CompetitionMembersScore::getBlock)).get();
            setHonorData(honorResponseList,membersScore,"盖帽王");
            //抢断王
            membersScore = membersScoreList.stream().max(Comparator.comparing(CompetitionMembersScore::getSnatch)).get();
            setHonorData(honorResponseList,membersScore,"抢断王");

        }
        return getDataTable(honorResponseList);
    }

    private void setHonorData(List<PersonalHonorResponse> honorResponseList,CompetitionMembersScore membersScore,String honorName) {
        if(UtilTool.isNotNull(membersScore)){
            PersonalHonorResponse honorResponse = new PersonalHonorResponse();
            honorResponse.setHonorName(honorName);
            honorResponse.setHonoraryPersonnel(membersScore.getRealName());
            honorResponse.setTeamName(membersScore.getTeamName());
            honorResponseList.add(honorResponse);
        }
    }
}
