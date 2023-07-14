package com.ruoyi.system.job;
import com.ruoyi.system.domain.Competition;
import com.ruoyi.system.domain.CompetitionTeamVsTeam;
import com.ruoyi.system.domain.enums.CompetitionStatusEnum;
import com.ruoyi.system.domain.enums.WxAppletsTemplateIdsEnum;
import com.ruoyi.system.domain.vo.CompetitionTeamVsTeamVo;
import com.ruoyi.system.domain.vo.TemplateDataVo;
import com.ruoyi.system.domain.vo.WxMssVo;
import com.ruoyi.system.service.ICompetitionService;
import com.ruoyi.system.service.ICompetitionTeamVsTeamService;
import com.ruoyi.system.service.WxAppletsService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CompetitionJobHandler {
    @Resource
    private ICompetitionService competitionService;

    @Resource
    private ICompetitionTeamVsTeamService competitionTeamVsTeamService;

    @Resource
    private WxAppletsService wxAppletsService;

    /** 赛会状态修改
     * 根据比赛时间修改比赛完成-定时任务
     * @param param
     * @return
     * @throws Exception
     */
    @XxlJob("competitionFinishJob")
    public ReturnT<String> execute(String param) throws Exception {
        competitionService.updateCompetitionFinish();
        return ReturnT.SUCCESS;
    }

    /**
     * 根据比赛时间修改比赛-队长完成-定时任务
     * @param param
     * @return
     * @throws Exception
     */
    @XxlJob("competitionVsFinishJob")
    public ReturnT<String> competitionVsFinishJob(String param) throws Exception {
        CompetitionTeamVsTeam competitionTeamVsTeam = new CompetitionTeamVsTeam();
        competitionTeamVsTeamService.competitionVsTeamStatusUpdate(competitionTeamVsTeam);
        return ReturnT.SUCCESS;
    }
    /**
     * 根据 球队VS球队 推送给队长-定时任务
     * @param param
     * @return
     * @throws Exception
     */
    @XxlJob("pushScheduleTeamVsTeamJob")
    public ReturnT<String> pushScheduleTeamVsTeamJob(String param) throws Exception {
        CompetitionTeamVsTeam competitionTeamVsTeam=new CompetitionTeamVsTeam();
        competitionTeamVsTeam.setIsDeleted(0);
        List<CompetitionTeamVsTeamVo> list = competitionTeamVsTeamService.getTodaySchedule(competitionTeamVsTeam);
        //循环推送消息
        for (CompetitionTeamVsTeamVo vo : list) {
            //主队
            if(!StringUtils.isEmpty(vo)&&!StringUtils.isEmpty(vo.getMainTeamOpenId())) {
               // Competition competition = competitionService.getById(vo.getCompetitionId());
                WxMssVo wxMssVo = new WxMssVo();
                wxMssVo.setTemplate_id(WxAppletsTemplateIdsEnum.COMPETITION_COURSE_REMIND.getCode());
                wxMssVo.setTouser(vo.getMainTeamOpenId());
                Map<String, TemplateDataVo> map = new HashMap<>();
                map.put("thing1", new TemplateDataVo(vo.getMainTeamName()+" VS "+vo.getGuestTeamName()));
                map.put("thing2", new TemplateDataVo(vo.getCompetitionDate() +" " +vo.getTheTime()));
                map.put("thing3", new TemplateDataVo(vo.getVsType()));
                map.put("thing4", new TemplateDataVo("你的球队今天有比赛哦，快去为自己的球队冲榜"));
                wxMssVo.setData(map);
                wxAppletsService.pushOneUser(wxMssVo);
            }
            //客队
            if(!StringUtils.isEmpty(vo)&&!StringUtils.isEmpty(vo.getGuestTeamOpenId())) {
                // Competition competition = competitionService.getById(vo.getCompetitionId());
                WxMssVo wxMssVo = new WxMssVo();
                wxMssVo.setTemplate_id(WxAppletsTemplateIdsEnum.COMPETITION_COURSE_REMIND.getCode());
                wxMssVo.setTouser(vo.getGuestTeamOpenId());
                Map<String, TemplateDataVo> map = new HashMap<>();
                map.put("thing1", new TemplateDataVo(vo.getMainTeamName()+" VS "+vo.getGuestTeamName()));
                map.put("thing2", new TemplateDataVo(vo.getCompetitionDate() +" " +vo.getTheTime()));
                map.put("thing3", new TemplateDataVo(vo.getVsType()));
                map.put("thing4", new TemplateDataVo("你的球队今天有比赛哦，快去为自己的球队冲榜"));
                wxMssVo.setData(map);
                wxAppletsService.pushOneUser(wxMssVo);
            }
        }
        return ReturnT.SUCCESS;
    }
}
