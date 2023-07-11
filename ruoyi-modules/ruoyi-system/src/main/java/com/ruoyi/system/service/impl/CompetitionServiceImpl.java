package com.ruoyi.system.service.impl;

import java.io.File;
import java.math.BigDecimal;
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
import com.ruoyi.common.core.exception.CheckedException;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.exception.UtilException;
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
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
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
@Log4j2
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
    @Resource
    private SmsService smsService;

    @Value("${image.location.linux}")
    private String linuxLocation;
    @Value("${image.location.windows}")
    private String winLocation;
    @Value("${image.domainName}")
    private String domainName;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CompetitionExcleVo importExcleData(Long competitionId,Map<String, PictureData> maplist, Sheet sheet) {
        CompetitionExcleVo excleVo = new CompetitionExcleVo();
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //System.out.println("获取到精确到日的时间格式为"+time);
        String[] str = time.split("-");//根据‘-’进行拆分字符串 拆分出来的日期有，年，日，月，根据年日月创建文件夹
        String datePath = "/" + str[0] + "/" + str[1] + "/" + str[2] + "/";
        //读图片--结束
        // printImg(maplist);
        //获得数据的总行数
        int totalRowNum = sheet.getPhysicalNumberOfRows();
        //todo 获取赛事编码
        Row row1 = sheet.getRow(1);
        Cell cell1 = row1.getCell(5);
        if (cell1 == null) {
            throw new CheckedException("赛事编码不能为空");
        }
        cell1.setCellType(CellType.STRING);
        String competitionCode = cell1.getStringCellValue().toString();
        if (StringUtils.isEmpty(competitionCode)) {
            throw new CheckedException("赛事编码不能为空");
        }
        System.out.println("赛事编码" + competitionCode);
        Row row5 = sheet.getRow(5);
        Cell cell5_1 = row5.getCell(0);
        if (cell5_1 == null) {
            throw new CheckedException("球队logo不能为空");
        }
        cell5_1.setCellType(CellType.STRING);
        //todo 获取赛事信息
        Competition competition = competitionMapper.selectCompetitionById(competitionId);
        //校验比赛编码是否是本
        BeanUtil.copyProperties(competition, excleVo);
        if (UtilTool.isNull(competition)) {
            throw new CheckedException("赛会信息不存在;");
        }
        if (!competition.getCompetitionCode().equals(competitionCode)) {
            throw new CheckedException("导入的文件中的赛事编码错误");
        }
        Cell cell5_2 = row5.getCell(1);
        if (cell5_2 == null) {
            throw new CheckedException("球队名称不能为空");
        }
        cell5_2.setCellType(CellType.STRING);
        String teamName = cell5_2.getStringCellValue().trim();
        if (StringUtils.isEmpty(teamName)) {
            throw new CheckedException("球队名称不能为空");
        }
        //球队队长
        Cell cell5_2_1 = row5.getCell(4);
        String captain = "";
        if (cell5_2_1 != null) {
            captain = cell5_2_1.getStringCellValue();
        }
        Cell cell5_3 = row5.getCell(5);
        if (cell5_3 == null) {
            throw new CheckedException("球队领队不能为空");
        }
        cell5_3.setCellType(CellType.STRING);
        String teamLeader = cell5_3.getStringCellValue();
        if (StringUtils.isEmpty(teamLeader)) {
            throw new CheckedException("球队领队不能为空");
        }
        Cell cell5_4 = row5.getCell(6);
        if (cell5_4 == null) {
            throw new CheckedException("球队领队手机号码不能为空");
        }
        cell5_4.setCellType(CellType.STRING);
        String teamLeaderTel = cell5_4.getStringCellValue().toString();
        if (StringUtils.isEmpty(teamLeaderTel)) {
            throw new CheckedException("球队队长手机号码不能为空");
        }
        Cell cell5_5 = row5.getCell(7);
        if (cell5_5 == null) {
            throw new CheckedException("球队人数不能为空");
        }
        cell5_5.setCellType(CellType.STRING);
        String teamUserNum = cell5_5.getStringCellValue();
        if (StringUtils.isEmpty(teamUserNum)) {
            throw new CheckedException("球队人数不能为空");
        }
        //todo 获取到数据后，开始保存数据
        LoginUser user = SecurityUtils.getLoginUser();
        String userId = null;
        if(ObjectUtil.isNotNull(user)&&ObjectUtil.isNotNull(user.getUserid())){
            userId = String.valueOf(user.getUserid());
        }
        CompetitionOfTeamVo team = new CompetitionOfTeamVo();
        team.setTeamName(teamName);
        team.setRemark("导入方式报名");
        team.setContactsTel(teamLeaderTel);
        team.setContacts(teamLeader);
        team.setCaptain(captain);
        team.setCreatedTime(new Date());
        team.setCreatedBy(userId);
        team.setCreatedTime(new Date());
        team.setSerialNumber(Integer.parseInt(teamUserNum));
        team.setCompetitionId(competition.getId());
        //保存图片
        PictureData pictureData = maplist.get("5_0");
        if (pictureData == null) {
            throw new UtilException("球队logo不能为空");
        }
        byte[] data = pictureData.getData();
        //得到保存的file
        String osPath = linuxLocation;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            osPath = winLocation;
        }
        String newFileName = UUID.randomUUID() + "_5.jpg";
        String uploadpath = UtilTool.getFileUploadPath(osPath);
        File file2 = UtilTool.bytesToFile(data, uploadpath, newFileName);
        team.setTeamLogo(domainName + datePath + newFileName);
        team.setStatus(0);
        System.out.println(JSON.toJSONString(team));
        //todo 保存球队数据;
        CompetitionOfTeam competitionOfTeam = competitionOfTeamMapper.selectOneByTeamName(teamName);
        if (UtilTool.isNotNull(competitionOfTeam)) {
            team.setId(competitionOfTeam.getId());
            competitionOfTeamMapper.updateCompetitionOfTeam(team);
        }else {
            competitionOfTeamMapper.insertCompetitionOfTeam(team);
        }
        excleVo.setOfTeam(team);
        //todo 清空球员数据
        competitionMembersMapper.deleteByMembers(competition.getId(), team.getId());
        //要获得属性
        List<CompetitionMembers> membersVos = new ArrayList<>();
        for (int i = 8; i < totalRowNum; i++) {
            CompetitionMembers membersVo = new CompetitionMembers();
            membersVo.setCompetitionId(competition.getId());
            //membersVo.setCompetitionTeamId(team.getId());
            membersVo.setCompetitionOfTeamId(team.getId());
            membersVo.setCompetitionNature(1);
            membersVo.setCreatedBy(userId);
            membersVo.setCreatedTime(new Date());
            //获得第i行对象
            Row row = sheet.getRow(i);
            //真实姓名
            Cell cell = row.getCell(1);
            if (cell != null) {
                cell.setCellType(CellType.STRING);
            }
            membersVo.setRealName(cell.getStringCellValue());
            if (StringUtils.isEmpty(membersVo.getRealName())) {
                break;
            }
            //球衣号码
            cell = row.getCell(2);
            if (cell != null) {
                cell.setCellType(CellType.STRING);
                membersVo.setJerseyNumber(cell.getStringCellValue());
            }
            //位置
            cell = row.getCell(3);
            if (cell != null) {
                cell.setCellType(CellType.STRING);
                membersVo.setTeamPosition(cell.getStringCellValue());
            }
            //身高
            cell = row.getCell(4);
            if (cell != null) {
                cell.setCellType(CellType.STRING);
                membersVo.setHeight(new BigDecimal(cell.getStringCellValue()));
            }
            //体重
            cell = row.getCell(5);
            if (cell != null) {
                cell.setCellType(CellType.STRING);
                membersVo.setWeight(new BigDecimal(cell.getStringCellValue()));
            }
            //证件号码
            cell = row.getCell(6);
            if (cell != null) {
                membersVo.setIdType("身份证");
                membersVo.setIdCardNo(cell.getStringCellValue());
            }
            //电话
            cell = row.getCell(7);
            if (cell != null) {
                membersVo.setContactsAreaCode("86");
                membersVo.setContactsTel(String.valueOf(((XSSFCell) cell).getRawValue()));
            }
            //电话
            cell = row.getCell(8);
            if (cell != null) {
                membersVo.setIdCardNo(String.valueOf(((XSSFCell) cell).getRawValue()));
            }
            //membersVo.setAvatar(domainName+datePath+newFileName);
            //保存图片
            PictureData pictureData2 = maplist.get(i + "_0");
            log.info(membersVo.getRealName()+" 开始导入，图片位置"+i + "_0");
            if (pictureData2 == null) {
                throw new CheckedException(membersVo.getRealName()+" 的头像插入方式错误,请先选中单元格然后插入图片");
            }
            byte[] data2 = pictureData2.getData();
            String newFileName2 = UUID.randomUUID() + "_0.jpg";
            String uploadpath2 = UtilTool.getFileUploadPath(osPath);
            File file3 = UtilTool.bytesToFile(data2, uploadpath2, newFileName2);
            membersVo.setPersonalPhoto(domainName + datePath + newFileName2);
            membersVo.setCreatedTime(new Date());
            //默认球员是已确认状态
            membersVo.setStatus(1);
            System.out.println(JSON.toJSONString((membersVo)));
            membersVo.setCreatedBy(userId);
            membersVos.add(membersVo);
            competitionMembersMapper.insertCompetitionMembers(membersVo);
        }

        excleVo.setTeamMemberList(membersVos);
        //todo 发个短信通知管理员
        Sms sms = new Sms();
        StringBuffer msg = new StringBuffer(Constants.SMS_PAOPAO_SIGN);
        msg.append("赛会[");
        msg.append(competition.getCompetitionName());
        msg.append("]");
        msg.append("已有球队[");
        msg.append(teamName);
        msg.append("]申请出战,请尽快审批处理！");
        sms.setMs(msg.toString());
        sms.setMobile(competition.getContactsTel());
        sms.setMb(competition.getContactsTel());
        SmsResponse smsResponse = smsService.sendSms(sms);
        if (smsResponse.getStatus() == 0) {
            //保存到缓存
            // redisUtil.set(Constant.ESTABLISH_COMPETITION_SMS_CAPTCHA+sms.getMb(), randomNums,Constant.SMS_PAOPAO_EXPIRES);
        }
        return excleVo;
    }
}
