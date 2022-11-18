package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;
import com.ruoyi.system.service.WxApplesCodeService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionMapper;
import com.ruoyi.system.domain.Competition;
import com.ruoyi.system.service.ICompetitionService;

import javax.annotation.Resource;

/**
 * 比赛信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class CompetitionServiceImpl implements ICompetitionService 
{
    @Resource
    private CompetitionMapper competitionMapper;
    @Resource
    private WxApplesCodeService wxApplesCodeService;
    @Resource
    private RedisService redisService;
    /**
     * 查询比赛信息
     * 
     * @param id 比赛信息主键
     * @return 比赛信息
     */
    @Override
    public Competition selectCompetitionById(Long id)
    {
        return competitionMapper.selectCompetitionById(id);
    }

    /**
     * 查询比赛信息列表
     * 
     * @param competition 比赛信息
     * @return 比赛信息
     */
    @Override
    public List<Competition> selectCompetitionList(Competition competition)
    {
        return competitionMapper.selectCompetitionList(competition);
    }

    /**
     * 新增比赛信息
     * 
     * @param competition 比赛信息
     * @return 结果
     */
    @Override
    public int insertCompetition(Competition competition)
    {
        return competitionMapper.insertCompetition(competition);
    }

    /**
     * 修改比赛信息
     * 
     * @param competition 比赛信息
     * @return 结果
     */
    @Override
    public int updateCompetition(Competition competition)
    {
        return competitionMapper.updateCompetition(competition);
    }

    /**
     * 批量删除比赛信息
     * 
     * @param ids 需要删除的比赛信息主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionByIds(Long[] ids)
    {
        return competitionMapper.deleteCompetitionByIds(ids);
    }

    /**
     * 删除比赛信息信息
     * 
     * @param id 比赛信息主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionById(Long id)
    {
        return competitionMapper.deleteCompetitionById(id);
    }

    @Override
    public WxAppletsCodeVo genCompetitionCommonAqrSpread(WxAppletsCodeVo wxAppletsCodeVo) {
        Object key = redisService.getCacheObject(CacheConstants.COMPETITION_SPREAD_AQR_CODE + wxAppletsCodeVo.getScene());
        if(ObjectUtils.isEmpty(key)){
            wxAppletsCodeVo = wxApplesCodeService.genWxApplesAqrCode(wxAppletsCodeVo);
            redisService.setCacheObject(CacheConstants.COMPETITION_SPREAD_AQR_CODE + wxAppletsCodeVo.getScene(),wxAppletsCodeVo.getCodeImgUrl(),30L, TimeUnit.DAYS);
        }else {
            wxAppletsCodeVo.setCodeImgUrl((String) key);
        }
        return wxAppletsCodeVo;
    }
}
