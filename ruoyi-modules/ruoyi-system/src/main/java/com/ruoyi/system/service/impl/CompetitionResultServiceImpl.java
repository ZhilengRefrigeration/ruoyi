package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.CompetitionResult;
import com.ruoyi.system.domain.CompetitionTeamVsTeam;
import com.ruoyi.system.domain.enums.VsResultEnums;
import com.ruoyi.system.domain.vo.CompetitionResultVo;
import com.ruoyi.system.domain.vo.CompetitionVsRecordVo;
import com.ruoyi.system.mapper.CompetitionResultMapper;
import com.ruoyi.system.mapper.CompetitionTeamVsTeamMapper;
import com.ruoyi.system.service.ICompetitionResultService;
import com.ruoyi.system.utils.UtilTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 赛会中-赛程结果记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class CompetitionResultServiceImpl extends ServiceImpl<CompetitionResultMapper, CompetitionResult> implements ICompetitionResultService
{
    @Resource
    private CompetitionResultMapper competitionResultMapper;
    @Resource
    private CompetitionTeamVsTeamMapper competitionTeamVsTeamMapper;

    /**
     * 查询赛会中-赛程结果记录
     * 
     * @param id 赛会中-赛程结果记录主键
     * @return 赛会中-赛程结果记录
     */
    @Override
    public CompetitionResult selectCompetitionResultById(Long id)
    {
        return competitionResultMapper.selectCompetitionResultById(id);
    }

    /**
     * 查询赛会中-赛程结果记录列表
     * 
     * @param competitionResult 赛会中-赛程结果记录
     * @return 赛会中-赛程结果记录
     */
    @Override
    public List<CompetitionResult> selectCompetitionResultList(CompetitionResult competitionResult)
    {
        return competitionResultMapper.selectCompetitionResultList(competitionResult);
    }

    /**
     * 新增赛会中-赛程结果记录
     * 
     * @param competitionResult 赛会中-赛程结果记录
     * @return 结果
     */
    @Override
    public int insertCompetitionResult(CompetitionResult competitionResult)
    {
        return competitionResultMapper.insertCompetitionResult(competitionResult);
    }

    /**
     * 修改赛会中-赛程结果记录
     * 
     * @param competitionResult 赛会中-赛程结果记录
     * @return 结果
     */
    @Override
    public int updateCompetitionResult(CompetitionResult competitionResult)
    {
        return competitionResultMapper.updateCompetitionResult(competitionResult);
    }

    /**
     * 批量删除赛会中-赛程结果记录
     * 
     * @param ids 需要删除的赛会中-赛程结果记录主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionResultByIds(Long[] ids)
    {
        return competitionResultMapper.deleteCompetitionResultByIds(ids);
    }

    /**
     * 删除赛会中-赛程结果记录信息
     * 
     * @param id 赛会中-赛程结果记录主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionResultById(Long id)
    {
        return competitionResultMapper.deleteCompetitionResultById(id);
    }

    @Override
    public int batchUpdateCompetitionResult(List<CompetitionResult> list) {
        for (CompetitionResult result:list){
            if(result.getId()==null){
                competitionResultMapper.insertCompetitionResult(result);
            }else {
                competitionResultMapper.updateCompetitionResult(result);
            }
        }
        return 1;
    }

    @Transactional
    @Override
    public int editData(CompetitionVsRecordVo obj) {
        //编辑比赛总分数据
        competitionTeamVsTeamMapper.updateCompetitionTeamVsTeam(obj.getTeamVsTeamVo());
        if(obj.getMainTeam().getId()==null) {
            CompetitionResultVo team = obj.getMainTeam();
            team.setCompetitionId(obj.getTeamVsTeamVo().getCompetitionId());
            team.setCompetitionVsId(obj.getTeamVsTeamVo().getId());
            team.setCompetitionOfTeamId(obj.getTeamVsTeamVo().getMainTeamId());
            team.setTeamName(obj.getTeamVsTeamVo().getMainTeamName());
            competitionResultMapper.insertCompetitionResult(team);
        }else {
            competitionResultMapper.updateCompetitionResult(obj.getMainTeam());
        }
        if(obj.getGuestTeam().getId()==null) {
            CompetitionResultVo team = obj.getGuestTeam();
            team.setCompetitionId(obj.getTeamVsTeamVo().getCompetitionId());
            team.setCompetitionVsId(obj.getTeamVsTeamVo().getId());
            team.setCompetitionOfTeamId(obj.getTeamVsTeamVo().getGuestTeamId());
            team.setTeamName(obj.getTeamVsTeamVo().getGuestTeamName());
            competitionResultMapper.insertCompetitionResult(team);
        }else {
            competitionResultMapper.updateCompetitionResult(obj.getGuestTeam());
        }
        return 1;
    }

    @Override
    public List<CompetitionResultVo> findByCompetitionVsId(Long competitionId, Long id) {
        return competitionResultMapper.findByCompetitionVsId(competitionId,id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(CompetitionResult entity) {
        LoginUser user = new LoginUser();//SecurityUtils.getLoginUser();
//      entity.fillOperationInfo(user.getUserId().toString());
       // entity.setCreatedBy(String.valueOf(user.getUserid()));
        entity.setCreatedTime(new Date());
        competitionResultMapper.insertCompetitionResult(entity);
        //this.calculateScore(entity,user);
        return true;
    }

    /**
     * 计算更新分数和积分
     * @param entity
     */
    private void calculateScore(CompetitionResult entity,LoginUser user) {

        //得分
        int mainScore = 0;
        int gustScore = 0;

        //主队
        Boolean flag = true;

        //得分
        Long mainId = null;
        Long gustId = null;

        /**
         * 更新积分和总分
         */
        CompetitionTeamVsTeam teamVsTeam = competitionTeamVsTeamMapper.selectCompetitionTeamVsTeamById(entity.getCompetitionVsId());
        //查询比赛数据
        CompetitionResult result = new CompetitionResult();
        result.setCompetitionVsId(teamVsTeam.getId());
        List<CompetitionResult> competitionResultList = competitionResultMapper.selectCompetitionResultList(result);

        if(UtilTool.isNotNull(competitionResultList)){
            for(CompetitionResult competitionResult : competitionResultList ){
                //小节分数可能为空
                int oneNodeScore = competitionResult.getOneNodeScore()==null?0:competitionResult.getOneNodeScore();
                int twoNodeScore = competitionResult.getTwoNodeScore()==null?0:competitionResult.getTwoNodeScore();
                int threeNodeScore = competitionResult.getThreeNodeScore()==null?0:competitionResult.getThreeNodeScore();
                int fourNodeScore = competitionResult.getFourNodeScore()==null?0:competitionResult.getFourNodeScore();
                int fiveNodeScore = competitionResult.getFiveNodeScore()==null?0:competitionResult.getFiveNodeScore();
                int sixNodeScore = competitionResult.getSixNodeScore()==null?0:competitionResult.getSixNodeScore();

                //合计总分
                int score = oneNodeScore+twoNodeScore+threeNodeScore+fourNodeScore+fiveNodeScore+sixNodeScore;
                //主队
                if(teamVsTeam.getMainTeamId().equals(competitionResult.getCompetitionOfTeamId())){
                    mainId = competitionResult.getId();
                    mainScore = score;
                    teamVsTeam.setMainTeamScore(score);
                } else {//客队
                    gustId = competitionResult.getId();
                    gustScore = score;
                    teamVsTeam.setGuestTeamScore(score);
                    //客队标识
                    if(entity.getCompetitionOfTeamId()==competitionResult.getCompetitionOfTeamId()){
                        flag = false;
                    }
                }

                //设置更新人
                //teamVsTeam.setModifiedBy(String.valueOf(user.getUserid()));
                teamVsTeam.setLastUpdatedTime(new Date());
                competitionTeamVsTeamMapper.updateCompetitionTeamVsTeam(teamVsTeam);
            }
        }

        //积分统计 默认1分
        entity.setIntegral(1);
        CompetitionResult competitionResult = new CompetitionResult();
        if(flag){
            if(mainScore>gustScore){
                entity.setIntegral(2);
                entity.setVsResult(VsResultEnums.win.code());
                if(gustId!=null){
                    competitionResult.setId(gustId);
                    competitionResult.setIntegral(1);
                    competitionResult.setVsResult(VsResultEnums.fail.code());
                    competitionResultMapper.updateCompetitionResult(competitionResult);
                }
            } else {
                if(gustId!=null){
                    competitionResult.setId(gustId);
                    competitionResult.setIntegral(2);
                    competitionResult.setVsResult(VsResultEnums.win.code());
                    competitionResultMapper.updateCompetitionResult(competitionResult);
                }
            }

        } else {
            if(gustScore>mainScore){
                entity.setIntegral(2);
                entity.setVsResult(VsResultEnums.win.code());
                if(mainId!=null){
                    competitionResult.setId(mainId);
                    competitionResult.setIntegral(1);
                    competitionResult.setVsResult(VsResultEnums.fail.code());
                    competitionResultMapper.updateCompetitionResult(competitionResult);
                }
            } else {
                if(mainId!=null){
                    competitionResult.setId(mainId);
                    competitionResult.setIntegral(2);
                    competitionResult.setVsResult(VsResultEnums.win.code());
                    competitionResultMapper.updateCompetitionResult(competitionResult);
                }
            }
        }
        //entity.setModifiedBy(String.valueOf(user.getUserid()));
        entity.setLastUpdatedTime(new Date());
        competitionResultMapper.updateCompetitionResult(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean edit(CompetitionResult entity) {
        LoginUser user = new LoginUser();
       // entity.setModifiedBy(String.valueOf(user.getUserid()));
        entity.setLastUpdatedTime(new Date());
        competitionResultMapper.updateCompetitionResult(entity);
        //this.calculateScore(entity,user);
        return true;
    }
}
