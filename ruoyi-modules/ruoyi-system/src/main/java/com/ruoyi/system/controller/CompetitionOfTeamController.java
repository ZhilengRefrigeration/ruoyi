package com.ruoyi.system.controller;

import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.domain.Competition;
import com.ruoyi.system.domain.CompetitionTeamGroup;
import com.ruoyi.system.domain.Sms;
import com.ruoyi.system.domain.vo.CompetitionOfTeamGroupVo;
import com.ruoyi.system.domain.vo.CompetitionOfTeamVo;
import com.ruoyi.system.domain.vo.SmsResponse;
import com.ruoyi.system.service.ICompetitionService;
import com.ruoyi.system.service.ICompetitionTeamGroupService;
import com.ruoyi.system.service.SmsService;
import com.ruoyi.system.utils.LoginUserUtil;
import com.ruoyi.system.utils.UtilTool;
import io.seata.core.model.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.CompetitionOfTeam;
import com.ruoyi.system.service.ICompetitionOfTeamService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 赛会中-参赛队伍Controller
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@RestController
@RequestMapping("/competitionOfTeam")
public class CompetitionOfTeamController extends BaseController
{
    @Autowired
    private ICompetitionOfTeamService competitionOfTeamService;
    @Autowired
    private ICompetitionService competitionService;
    @Autowired
    private ICompetitionTeamGroupService competitionTeamGroupService;
    @Autowired
    private SmsService smsService;
    @Value("${image.location.linux}")
    private String linuxLocation;
    @Value("${image.location.windows}")
    private String winLocation;
    @Value("${image.domainName}")
    private String domainName;
    /**
     * 查询赛会中-参赛队伍列表
     */
//    @RequiresPermissions("system:competitionOfTeam:list")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionOfTeamVo competitionOfTeam)
    {
        startPage();
        List<CompetitionOfTeamVo> list = competitionOfTeamService.selectCompetitionOfTeamList(competitionOfTeam);
        return getDataTable(list);
    }

    /**
     * 导出赛会中-参赛队伍列表
     */
    @RequiresPermissions("system:competitionOfTeam:export")
    @Log(title = "赛会中-参赛队伍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompetitionOfTeam competitionOfTeam)
    {
        List<CompetitionOfTeamVo> list = competitionOfTeamService.selectCompetitionOfTeamList(competitionOfTeam);
        ExcelUtil<CompetitionOfTeamVo> util = new ExcelUtil<CompetitionOfTeamVo>(CompetitionOfTeamVo.class);
        util.exportExcel(response, list, "赛会中-参赛队伍数据");
    }

    /**
     * 获取赛会中-参赛队伍详细信息
     */
//    @RequiresPermissions("system:competitionOfTeam:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionOfTeamService.selectCompetitionOfTeamById(id));
    }

    /**
     * 新增赛会中-参赛队伍
     */
//    @RequiresPermissions("system:competitionOfTeam:add")
    @Log(title = "赛会中-参赛队伍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionOfTeam competitionOfTeam)
    {
        return toAjax(competitionOfTeamService.insertCompetitionOfTeam(competitionOfTeam));
    }

    /**
     * 修改赛会中-参赛队伍
     */
//    @RequiresPermissions("system:competitionOfTeam:edit")
    @Log(title = "赛会中-参赛队伍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionOfTeam competitionOfTeam)
    {
        return toAjax(competitionOfTeamService.updateCompetitionOfTeam(competitionOfTeam));
    }
//    @RequiresPermissions("system:competitionOfTeam:batchEditById")
    @Log(title = "赛会中-参赛队伍批量修改", businessType = BusinessType.UPDATE)
    @PutMapping("/batchEditById")
    public AjaxResult batchEditById(@RequestBody List<CompetitionOfTeam> list)
    {
        return toAjax(competitionOfTeamService.batchUpdateCompetitionOfTeam(list));
    }

//    @RequiresPermissions("system:competitionOfTeam:removeTeamGroup")
    @Log(title = "赛会中-参赛队伍移除分组", businessType = BusinessType.UPDATE)
    @PutMapping("/removeTeamGroup/{ids}")
    public AjaxResult removeTeamGroup(@PathVariable Long[] ids)
    {
        return toAjax(competitionOfTeamService.removeTeamGroup(ids));
    }

//    @RequiresPermissions("system:competitionOfTeam:intoTeamGroup")
    @Log(title = "赛会中-参赛队伍移入分组", businessType = BusinessType.UPDATE)
    @PostMapping("/intoTeamGroup/{competitionGroup}")
    public AjaxResult intoTeamGroup(@PathVariable String competitionGroup,@RequestBody List<Long> ids)
    {
        return toAjax(competitionOfTeamService.intoTeamGroup(competitionGroup,ids));
    }
    /**
     * 删除赛会中-参赛队伍
     */
//    @RequiresPermissions("system:competitionOfTeam:remove")
    @Log(title = "赛会中-参赛队伍", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionOfTeamService.deleteCompetitionOfTeamByIds(ids));
    }
    @PostMapping("/getMyJoinCompetitionTeam")
    @ResponseBody
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"分页获取【我】参与比赛的球队列表")
    public TableDataInfo getMyJoinCompetitionTeam(@RequestBody CompetitionOfTeamVo entity){
        startPage();
        List<CompetitionOfTeamVo> list =competitionOfTeamService.getMyJoinCompetitionTeam(entity);
        return getDataTable(list);
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"新增")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(@RequestBody CompetitionTeamGroup entity) throws Exception {
        entity.setCreatedTime(new Date());
        entity.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUserid()));
        return AjaxResult.success(competitionTeamGroupService.add(entity));
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"编辑")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editCompetitionOfTeam(@RequestBody CompetitionOfTeam entity) throws Exception {
        if(StringUtils.isEmpty(entity)){
            throw new ServiceException("参数异常，非法操作！");
        }
        if(StringUtils.isEmpty(entity.getId())){
            throw new ServiceException("id为空！");
        }
        return AjaxResult.success(competitionOfTeamService.edit(entity));
    }
    @PostMapping("/getJoinCompetitionTeam")
    @ResponseBody
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"分页获取参与比赛的球队列表")
    public TableDataInfo getJoinCompetitionTeam(@RequestBody CompetitionOfTeam entity){
        startPage();
        //关键字word包含：球队名称、地点名称、球馆名称，支持模糊搜索；
        List<CompetitionOfTeamVo> list = competitionOfTeamService.getJoinCompetitionTeam(entity);
        return getDataTable(list);
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"参赛球队审批")
    @PostMapping("/teamApprove")
    @ResponseBody
    public AjaxResult approve(@RequestBody CompetitionOfTeam entity) throws Exception {
        if(StringUtils.isEmpty(entity)){
            throw new ServiceException("参数异常，非法操作！");
        }
        if(StringUtils.isEmpty(entity.getId())){
            throw new InvalidParameterException("id为空！");
        }if(StringUtils.isEmpty(entity.getStatus())){
            throw new InvalidParameterException("status为空！");
        }
        CompetitionOfTeam old = competitionOfTeamService.selectCompetitionOfTeamById(entity.getId());
        Competition competition =  competitionService.selectCompetitionById(old.getCompetitionId());
        StringBuffer msg = new StringBuffer(Constants.SMS_PAOPAO_SIGN);
        if(old.getStatus().intValue()!=entity.getStatus()){
            String passMsg = "已通过,让我们携手共创新的荣耀";
            if(entity.getStatus()==-1){
                passMsg  = "已被驳回,原因是："+entity.getRemark();
            }
            competitionOfTeamService.edit(entity);
            //todo 发个短信通知管理员
            Sms sms = new Sms();
            msg.append("你的球队[");
            msg.append(old.getTeamName());
            msg.append("]加入赛会[");
            msg.append(competition.getCompetitionName());
            msg.append("]的申请");
            msg.append(passMsg);
            sms.setMs(msg.toString());
            sms.setMb(old.getContactsTel());
            SmsResponse smsResponse = smsService.sendSms(sms);
        }
        return AjaxResult.success(Boolean.TRUE);
    }
    @PostMapping("/getJoinCompetitionGroupTeam")
    @ResponseBody
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"获取赛事中参与的球队的分组的积分情况")
    public AjaxResult getJoinCompetitionGroupTeam(@RequestBody CompetitionOfTeamVo entity){
        Map hashMap = new HashMap();
        List<CompetitionOfTeamGroupVo> competitionOfTeamGroupVoList = null;
        List<CompetitionOfTeamVo> competitionOfTeamVos = competitionOfTeamService.findCompetitionTeamGroupList(entity);
        if(UtilTool.isNotNull(competitionOfTeamVos)){
            competitionOfTeamGroupVoList = new ArrayList<>();
            //获取没有分组的球队
            List<CompetitionOfTeamVo> groupNull = competitionOfTeamVos.stream().filter(a -> UtilTool.isNull(a.getCompetitionGroup())).collect(Collectors.toList());

            //过滤分组为空的球队
            competitionOfTeamVos = competitionOfTeamVos.stream().filter(a -> UtilTool.isNotNull(a.getCompetitionGroup())).collect(Collectors.toList());

            //分组去重并返回SET
            List<CompetitionOfTeamVo> setList = competitionOfTeamVos.stream()
                    .collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                            new TreeSet<>(Comparator.comparing(CompetitionOfTeamVo::getCompetitionGroup))), ArrayList::new));
            //排序
            setList.sort((o1, o2) -> o1.getCompetitionGroup().compareTo(o2.getCompetitionGroup())); //正序
            CompetitionOfTeamGroupVo competitionOfTeamGroupVo = null;
            for(CompetitionOfTeamVo competitionOfTeamVo : setList){
                competitionOfTeamGroupVo = new CompetitionOfTeamGroupVo();
                List<CompetitionOfTeamVo> groupList = competitionOfTeamVos.stream().filter(s -> s.getCompetitionGroup().equals(competitionOfTeamVo.getCompetitionGroup())).collect(Collectors.toList());
                groupList.sort((o1, o2) -> o2.getIntegral().compareTo(o1.getIntegral())); //正序
                competitionOfTeamGroupVo.setGroupName(competitionOfTeamVo.getCompetitionGroup());
                competitionOfTeamGroupVo.setTeamList(groupList);
                competitionOfTeamGroupVoList.add(competitionOfTeamGroupVo);
            }

            //添加未分组球队
            if(UtilTool.isNotNull(groupNull)){
                competitionOfTeamGroupVo = new CompetitionOfTeamGroupVo();
                competitionOfTeamGroupVo.setGroupName("未分");
                competitionOfTeamGroupVo.setTeamList(groupNull);
                competitionOfTeamGroupVoList.add(competitionOfTeamGroupVo);
            }

            hashMap.put("competitionOfTeamGroupVo",competitionOfTeamGroupVoList);
        }

        return AjaxResult.success(hashMap);
    }
    @GetMapping("/teamEnrollExcleExport")
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"球队报名excel模板文件导出(get)")
    public void teamEnrollExcleExport(@RequestParam(value = "competitionId", required = true) Long competitionId, HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        Competition competition = competitionService.selectCompetitionById(competitionId);
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // {} 代表普通变量 {.} 代表是list的变量 {前缀.} 前缀可以区分不同的list
        String osPath = linuxLocation;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            osPath = winLocation;
        }
        Long times = System.currentTimeMillis();
        String templateFileName = osPath + "apply.xlsx";
        String fileName = osPath+ "compositeFill" + times + ".xlsx";
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName1 = URLEncoder.encode(competition.getCompetitionName()+"球队报名登记表", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName1 + ".xlsx");
            // EasyExcel.write(response.getOutputStream(), DownloadData.class).sheet("模板").doWrite(data());
            // 这里需要设置不关闭流
            // 方案1
            //EasyExcel.write(response.getOutputStream()).withTemplate(templateFileName).sheet().doFill(competition);
            System.out.println(templateFileName);
            //todo 先把文件输出到服务器，
            EasyExcel.write(response.getOutputStream()).withTemplate(templateFileName).sheet().doFill(competition);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = MapUtils.newHashMap();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }
}
