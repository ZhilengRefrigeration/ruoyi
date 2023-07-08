package com.ruoyi.system.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.enums.*;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.*;
import com.ruoyi.system.utils.UtilTool;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    private CompetitionOfTeamMapper competitionOfTeamMapper;
    @Resource
    private CompetitionMembersMapper competitionMembersMapper;
    @Resource
    private WxBasketballTeamMapper wxBasketballTeamMapper;
    @Resource
    private CompetitionTeamVsTeamMapper competitionTeamVsTeamMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private WxUserMapper wxUserMapper;
    @Resource
    private WxAppletsService wxAppletsService;
    @Resource
    private IWxBuildingInfoService wxBuildingInfoService;
    @Resource
    private ITeamMembersService teamMembersService;
    @Resource
    private IMessageService messageService;
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

    @Override
    public List<Competition> getCompetitionByCondition(CompetitionVo competition) {
        return competitionMapper.getCompetitionByCondition(competition);
    }

    @Override
    public CompetitionExcleVo getTeamEnrollExcleImpData(Long competitionId, Long userId) {
        CompetitionExcleVo excleVo = new CompetitionExcleVo();
        Competition competition = competitionMapper.selectCompetitionById(competitionId);
        if(ObjectUtil.isNull(competition)){
            return excleVo;
        }
        //todo 获取球队数据
        CompetitionOfTeam team = competitionOfTeamMapper.selectOneByUserId(competitionId,String.valueOf(userId));
        if(ObjectUtil.isNull(team)){
            return excleVo;
        }
        BeanUtil.copyProperties(competition,excleVo);
        excleVo.setOfTeam(team);
        //todo 查询球队队员数据
        CompetitionMembers condtion = new CompetitionMembers();
        condtion.setCompetitionId(competitionId);
        condtion.setCompetitionOfTeamId(team.getId());
        List<CompetitionMembers> members = competitionMembersMapper.selectCompetitionMembersList(condtion);
        excleVo.setTeamMemberList(members);
        return excleVo;
    }

    @Override
    public CompetitionResponse getCompetitionById(Long id) {
        //查询比赛信息
        CompetitionResponse competitionResponse=new CompetitionResponse();
        Competition competition = competitionMapper.selectCompetitionById(id);
        if(UtilTool.isNull(competition)){
            throw new ServiceException("根据id未查询到约战信息");
        }
        BeanUtils.copyProperties(competition,competitionResponse);
        //查询比赛参与人员列表数据
        List<CompetitionMembersVo> membersList = competitionMembersMapper.getCompetitionMembersByCompetitionId(competition.getId());
        if(competition.getCompetitionNature().intValue()==0) {
            Map<Long, List<CompetitionMembersVo>> groupByTeamIdMap = membersList.stream().collect(Collectors.groupingBy(CompetitionMembers::getCompetitionTeamId));       //查询主队下的队员信息
            for (Map.Entry<Long, List<CompetitionMembersVo>> entry : groupByTeamIdMap.entrySet()) {
                if (!StringUtils.isEmpty(competition.getMainTeamId()) && entry.getKey().equals(competition.getMainTeamId())) {
                    competitionResponse.setMainTeamMemberList(entry.getValue());
                } else if (!StringUtils.isEmpty(competition.getGuestTeamId()) && entry.getKey().equals(competition.getGuestTeamId())) {
                    competitionResponse.setGuestTeamMemberList(entry.getValue());
                }
            }
        }
        //查询球队的合照
        List<Long> teamIds =new ArrayList<>();
        if(!StringUtils.isEmpty(competition.getMainTeamId())) {
            teamIds.add(competition.getMainTeamId());
        }
        if(!StringUtils.isEmpty(competition.getGuestTeamId())) {
            teamIds.add(competition.getGuestTeamId());
        }
        if(teamIds.size()>0) {
            List<WxBasketballTeam> basketballTeamList = wxBasketballTeamMapper.selectBatchIds(teamIds);
            CompetitionTeamVsTeam competitionTeamVsTeam = new CompetitionTeamVsTeam();
            for (WxBasketballTeam team : basketballTeamList) {
                //设置主队数据
                if (!StringUtils.isEmpty(competition.getMainTeamId()) && team.getId().equals(competition.getMainTeamId())) {
                    competitionResponse.setMainTeamPhoto(team.getDefaultPicture());
                    competitionResponse.setMainTeamDes(team.getTeamDes());
                    competitionTeamVsTeam.setMainTeamId(team.getId());
                    List<CompetitionTeamVsTeamVo> mainCompetitionTeamVsTeamList = competitionTeamVsTeamMapper.getLatelySchedule(competitionTeamVsTeam);
                    competitionResponse.setMainCompetitionTeamVsTeamList(mainCompetitionTeamVsTeamList);
                } else if (!StringUtils.isEmpty(competition.getGuestTeamId()) && team.getId().equals(competition.getGuestTeamId())) {
                    //组装客队数据
                    competitionResponse.setGuestTeamPhoto(team.getDefaultPicture());
                    competitionResponse.setGuestTeamDes(team.getTeamDes());
                    competitionTeamVsTeam.setGuestTeamId(team.getId());
                    List<CompetitionTeamVsTeamVo> guestCompetitionTeamVsTeamList = competitionTeamVsTeamMapper.getLatelySchedule(competitionTeamVsTeam);
                    competitionResponse.setGuestCompetitionTeamVsTeamList(guestCompetitionTeamVsTeamList);
                }
            }
        }
        return competitionResponse;
    }

    @Override
    public Boolean establishCompetition(CompetitionVo entity) {
        //使用赛会名称加密缓存防止重复提交
        Object key = redisService.getCacheObject(Constants.COMPETITION_CREATE_KEY + DigestUtil.md5(entity.getCompetitionName().trim()));
        if(ObjectUtil.isNotNull(key)){
            throw new ServiceException("当前创建赛会数据已提交到系统，毋须重复提交！");
        }
        LoginUser user = SecurityUtils.getLoginUser();
        entity.setCreatedBy(String.valueOf(user.getUserid()));
        entity.setCreatedTime(new Date());
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        entity.setCompetitionCode(sdf.format(new Date())+ RandomStringUtils.randomNumeric(6));
        //判断是否已经存在
        List<Competition> existList = competitionMapper.selectCompetitionList(        Competition.builder().competitionName(entity.getCompetitionName()).competitionNature(entity.getCompetitionNature()).build());
        if(existList.size()>0) {
            throw new ServiceException("当前赛会已存在，毋须重复提交！");
        }
        //小程序-推送赛会通知给系统管理员。管理员好及时去审核赛会
        List<UserRole> userRoles = userRoleMapper.selectUserRoleList(UserRole.builder().roleCode(UserRoles.ADMIN.code()).build());
        List<Long> userIds = userRoles.stream().map(UserRole::getUserId).collect(Collectors.toList());
        List<WxUser> managerUsers = wxUserMapper.listByIds(userIds);
        if(!StringUtils.isEmpty(managerUsers)&&!StringUtils.isEmpty(managerUsers.size()>0)){
            for (WxUser managerUser:managerUsers){
                if(!StringUtils.isEmpty(managerUser.getOpenid())) {
                    WxMssVo wxMssVo = new WxMssVo();
                    wxMssVo.setTemplate_id(WxAppletsTemplateIdsEnum.COMPETITION_SIGN_UP.getCode());
                    wxMssVo.setTouser(managerUser.getOpenid());
                    Map<String, TemplateDataVo> map = new HashMap<>();
                    map.put("thing3", new TemplateDataVo(entity.getCompetitionName()));
                    map.put("name1", new TemplateDataVo(entity.getContacts()));
                    map.put("phone_number2", new TemplateDataVo(entity.getContactsTel()));
                    map.put("thing7", new TemplateDataVo("有新的赛会被创建,请尽快审核！"));
                    wxMssVo.setData(map);
                    wxAppletsService.pushOneUser(wxMssVo);
                }
            }
        }
        if(competitionMapper.insertCompetition(entity)>0){
            redisService.setCacheObject(Constants.COMPETITION_CREATE_KEY + DigestUtil.md5(entity.getCompetitionName().trim()),entity.getCompetitionName(),20L,TimeUnit.SECONDS);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean challengeConfirm(Competition entity) {
        //使用赛会名称加密缓存防止重复提交
        Object key = redisService.getCacheObject(Constants.COMPETITION_CREATE_KEY + DigestUtil.md5(entity.getCompetitionName().trim()));
        if(UtilTool.isNotNull(key)){
            throw new ServiceException("当前创建赛会数据已提交到系统，毋须重复提交！");
        }
        LoginUser user = SecurityUtils.getLoginUser();
        entity.setCreatedBy(String.valueOf(user.getUserid()));
        entity.setCreatedTime(new Date());
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        entity.setCompetitionCode(sdf.format(new Date())+ RandomStringUtils.randomNumeric(6));
        //判断是否已经存在
        List<Competition> dbList = competitionMapper.selectCompetitionList(Competition.builder().competitionNature(entity.getCompetitionNature())
                .competitionName(entity.getCompetitionName()).build());
        if(dbList.size()>0) {
            throw new ServiceException("当前赛会已存在，毋须重复提交！");
        }
        //小程序-推送赛会通知给系统管理员。管理员好及时去审核赛会
        List<UserRole> userRoles = userRoleMapper.selectUserRoleList(UserRole.builder().roleCode(UserRoles.ADMIN.code()).build());
        List<Long> userIds = userRoles.stream().map(UserRole::getUserId).collect(Collectors.toList());
        List<WxUser> managerUsers = wxUserMapper.listByIds(userIds);
        if(!StringUtils.isEmpty(managerUsers)&&!StringUtils.isEmpty(managerUsers.size()>0)){
            for (WxUser managerUser:managerUsers){
                if(!StringUtils.isEmpty(managerUser.getOpenid())) {
                    WxMssVo wxMssVo = new WxMssVo();
                    wxMssVo.setTemplate_id(WxAppletsTemplateIdsEnum.COMPETITION_SIGN_UP.getCode());
                    wxMssVo.setTouser(managerUser.getOpenid());
                    Map<String, TemplateDataVo> map = new HashMap<>();
                    map.put("thing3", new TemplateDataVo(entity.getCompetitionName()));
                    map.put("name1", new TemplateDataVo(entity.getContacts()));
                    map.put("phone_number2", new TemplateDataVo(entity.getContactsTel()));
                    map.put("thing7", new TemplateDataVo("有新的赛会被创建,请尽快审核！"));
                    wxMssVo.setData(map);
                    wxAppletsService.pushOneUser(wxMssVo);
                }
            }
        }
        if(ObjectUtil.isNull(entity.getId())) {
            if (competitionMapper.insertCompetition(entity) > 0) {
                redisService.setCacheObject(Constants.COMPETITION_CREATE_KEY + DigestUtil.md5(entity.getCompetitionName().trim()), entity.getCompetitionName(), 20L, TimeUnit.SECONDS);
            }
        }else {
            competitionMapper.updateCompetition(entity);
        }
        return Boolean.TRUE;
    }

    @Override
    public List<Competition> getMyJoinCompetition(CompetitionVo competition) {
        LoginUser user = SecurityUtils.getLoginUser();
        //查询登录用户的系统角色
        List<UserRole> userRoles = userRoleMapper.selectUserRoleList(UserRole.builder().roleCode(UserRoles.ADMIN.code()).build());
        if(!StringUtils.isEmpty(userRoles) && userRoles.size()>0) {
            List<Long> userIds = userRoles.stream().map(UserRole::getUserId).collect(Collectors.toList());
            //如果是管理员就直接可以查看所有的赛事
            if (userIds.contains(user.getUserid())) {
                competition.setUserId(null);
                competition.setFounder(null);
            }
        }
        List<Competition> list=competitionMapper.getMyJoinCompetition(competition);
        for (Competition comp:list){
            if(competition.getCompetitionNature()==0){
                WxBasketballTeam mainTeam = wxBasketballTeamMapper.selectWxBasketballTeamById(comp.getMainTeamId());
                if(!org.springframework.util.ObjectUtils.isEmpty(mainTeam)) {
                    comp.setMainTeamLogo(mainTeam.getTeamLogo());
                }
                if(comp.getGuestTeamId()!=null) {
                    WxBasketballTeam guestTeam = wxBasketballTeamMapper.selectWxBasketballTeamById(comp.getGuestTeamId());
                    if(!org.springframework.util.ObjectUtils.isEmpty(guestTeam)) {
                        comp.setGuestTeamLogo(guestTeam.getTeamLogo());
                    }
                }
            }
        }
        return list;
    }
    @Transactional
    @Override
    public Boolean add(Competition entity) {
        entity.setCompetitionNature(0);
        Long id=entity.getId();
        LoginUser user = SecurityUtils.getLoginUser();
        //判断是否存在
        List<Competition> competitions = competitionMapper.selectCompetitionList(Competition.builder()
                .competitionNature(entity.getCompetitionNature())
                .mainTeamId(entity.getMainTeamId())
                .buildingId(entity.getBuildingId())
                .cityCode(entity.getCityCode()).build());
        if(competitions.size()>0){
            throw new ServiceException("当前约战数据已存在，毋须重复提交！");
        }
        //获取场馆数据
        if(!StringUtils.isEmpty(entity.getBuildingId())){
            WxBuildingInfo buildingInfo = wxBuildingInfoService.selectWxBuildingInfoById(entity.getBuildingId());
            entity.setBuildingName(buildingInfo.getBuildingName());
            entity.setCityCode(buildingInfo.getCityCode());
            entity.setCityName(buildingInfo.getCityName());
            entity.setCompetitionAddress(buildingInfo.getAddress());
            entity.setLatitude(buildingInfo.getLatitude());
            entity.setLongitude(buildingInfo.getLongitude());
        }
        entity.setCreatedBy(String.valueOf(user.getUserid()));
        entity.setCreatedTime(new Date());
        entity.setFounder(user.getUserid());
        entity.setStatus(CompetitionStatusEnum.TREATY_WAR.getValue());
        if(ObjectUtil.isNull(entity.getId())){
            competitionMapper.insertCompetition(entity);
        }else {
            competitionMapper.updateCompetition(entity);
        }
        if(!StringUtils.isEmpty(entity.getGuestTeamId())) {
            TeamMembers teamMembers = teamMembersService.getOneByTeamIdAndRoleCode(entity.getGuestTeamId(), RoleEnum.TEAM_MANAGER.getCode());
            //如果指定了约战的对象就需要给约战的球队的队长发一个消息
            Message message = new Message();
            //审核人是球队队长
            message.setCreatedBy(String.valueOf(user.getUserid()));
            message.setAuditor(teamMembers.getUserId());
            message.setMessageType("0");
            message.setFlowType(FowTypeEnum.MAKE_AN_APPOINTMENT.getCode());
            message.setSourceId(entity.getId());
            message.setFlowEntity(JSON.toJSONString(entity));
            message.setCreatedTime(new Date());
            message.setMessageTitle("您收到一条【"+entity.getMainTeamName()+"】"+FowTypeEnum.MAKE_AN_APPOINTMENT.getDesc()+"，请及时处理");
            messageService.insertMessage(message);
            //小程序-查询用户数据看看是否可以推送服务消息根据unionId是否存在
            WxUser guestUser = wxUserMapper.selectWxUserById(teamMembers.getUserId());
            if(!StringUtils.isEmpty(guestUser)&&!StringUtils.isEmpty(guestUser.getOpenid())){
                WxMssVo wxMssVo=new WxMssVo();
                wxMssVo.setTemplate_id(WxAppletsTemplateIdsEnum.TREATY_WAR.getCode());
                wxMssVo.setTouser(guestUser.getOpenid());
                Map<String, TemplateDataVo> map = new HashMap<>();
                map.put("thing1",new TemplateDataVo(entity.getMainTeamName()));
                map.put("thing2",new TemplateDataVo(entity.getGuestTeamName()));
                map.put("thing3",new TemplateDataVo("您有新的约战信息，请在小程序中进行处理"));
                wxMssVo.setData(map);
                wxAppletsService.pushOneUser(wxMssVo);
            }
        }
        if(StringUtils.isEmpty(id)) {
            //保存创建人到比赛参与人员表
            CompetitionMembers competitionMembers = new CompetitionMembers();
            competitionMembers.setCompetitionId(entity.getId());
            competitionMembers.setCreatedTime(new Date());
            competitionMembers.setCompetitionTeamId(entity.getMainTeamId());
            competitionMembers.setRoleCode(RoleEnum.TEAM_MANAGER.getCode());
            competitionMembers.setCreatedBy(String.valueOf(user.getUserid()));
            competitionMembers.setUserId(user.getUserid());
            competitionMembers.setUserType(1);
            competitionMembers.setStatus(1);
            competitionMembersMapper.insertCompetitionMembers(competitionMembers);
        }
        return true;
    }
    @Override
    public Boolean edit(Competition entity) {
        LoginUser user = SecurityUtils.getLoginUser();
        entity.setLastUpdatedTime(new Date());
        entity.setModifiedBy(String.valueOf(user.getUserid()));
        competitionMapper.updateCompetition(entity);
        return true;
    }
}
