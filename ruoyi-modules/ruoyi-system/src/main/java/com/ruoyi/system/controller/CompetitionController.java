package com.ruoyi.system.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.system.UserInfo;
import com.alibaba.csp.sentinel.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.exception.CheckedException;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.exception.UtilException;
import com.ruoyi.common.core.utils.uuid.IdUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.api.model.WxLoginUser;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.service.*;
import com.ruoyi.system.utils.LoginUserUtil;
import com.ruoyi.system.utils.UtilTool;
import io.seata.core.model.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.aspectj.weaver.loadtime.Aj;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 比赛信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/competition")
public class CompetitionController extends BaseController
{
    @Autowired
    private ICompetitionService competitionService;
    @Autowired
    private ICompetitionOfTeamService competitionOfTeamService;
    @Autowired
    private ICompetitionMembersService competitionMembersService;
    @Autowired
    private IWxUserService wxUserService;

    @Autowired
    private RedisService redisService;
    @Autowired
    private SmsService smsService;

    @Value("${image.location.linux}")
    private String linuxLocation;
    @Value("${image.location.windows}")
    private String winLocation;
    @Value("${image.domainName}")
    private String domainName;

    /**
     * 查询比赛信息列表
     */
    @RequiresPermissions("system:competition:list")
    @GetMapping("/list")
    public TableDataInfo list(Competition competition)
    {
        startPage();
        List<Competition> list = competitionService.selectCompetitionList(competition);
        return getDataTable(list);
    }

    /**
     * 导出比赛信息列表
     */
    @RequiresPermissions("system:competition:export")
    @Log(title = "比赛信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Competition competition)
    {
        List<Competition> list = competitionService.selectCompetitionList(competition);
        ExcelUtil<Competition> util = new ExcelUtil<Competition>(Competition.class);
        util.exportExcel(response, list, "比赛信息数据");
    }

    /**
     * 获取比赛信息详细信息
     */
    @RequiresPermissions("system:competition:query")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionService.selectCompetitionById(id));
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"根据ID查询比赛详情")
    @GetMapping("/detail/{id}")
    @ResponseBody
    public AjaxResult detail(@PathVariable("id") Long id){
        CompetitionResponse competition = competitionService.getCompetitionById(id);
        return AjaxResult.success(competition);
    }
    @PostMapping("/getMyJoinCompetition")
    @ResponseBody
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"分页获取我参与过的比赛列表")
    public TableDataInfo getMyJoinCompetition(@RequestBody CompetitionVo entity) {
        startPage();
        //关键字word包含：球队名称、地点名称、球馆名称，支持模糊搜索；
        entity.setIsDeleted(0);
        List<Competition> list = competitionService.getMyJoinCompetition(entity);
        return getDataTable(list);
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"创建约战")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(@RequestBody Competition entity)  {
        return AjaxResult.success(competitionService.add(entity));
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"获取自动生成的赛会唯一编码")
    @GetMapping("/genCompetitionCode")
    @ResponseBody
    public AjaxResult genCompetitionCode() {
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        System.out.println(sdf.format(new Date()) + RandomStringUtils.randomNumeric(6));
        String code = "B" + sdf.format(new Date()) + RandomStringUtils.randomNumeric(6);
        return AjaxResult.success(code);
    }
    /**
     * 新增比赛信息
     */
    @RequiresPermissions("system:competition:add")
    @Log(title = "比赛信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addCompetition(@RequestBody Competition competition)
    {
        return toAjax(competitionService.insertCompetition(competition));
    }

    @RequiresPermissions("system:competition:genCompetitionCommonAqrSpread")
    @Log(title = "生成赛会普通微信推广码", businessType = BusinessType.OTHER)
    @PostMapping("/genCompetitionCommonAqrSpread")
    public AjaxResult genCompetitionCommonAqrSpread(@RequestBody WxAppletsCodeVo wxAppletsCodeVo)
    {
        return AjaxResult.success(competitionService.genCompetitionCommonAqrSpread(wxAppletsCodeVo));
    }

    /**
     * 修改比赛信息
     */
    @RequiresPermissions("system:competition:edit")
    @Log(title = "比赛信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editCompetition(@RequestBody Competition competition)
    {
        return toAjax(competitionService.updateCompetition(competition));
    }

    /**
     * 删除比赛信息
     */
    @RequiresPermissions("system:competition:remove")
    @Log(title = "比赛信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionService.deleteCompetitionByIds(ids));
    }

    @ApiOperation(ApiTerminal.wxMiniProgram+"应战确认")
    @PostMapping("/challengeConfirm")
    @ResponseBody
    public AjaxResult challengeConfirm(@RequestBody Competition entity) throws Exception {
        if (UtilTool.isNull(entity)) {
            throw new InvalidParameterException("参数异常，非法操作！");
        } else if (entity.getId() == null) {
            throw new InvalidParameterException("约战id不能为空");
        } else if (entity.getGuestTeamId() == null) {
            throw new InvalidParameterException("应战teamId不能为空");
        }
        return AjaxResult.success(competitionService.challengeConfirm(entity));
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"创建赛会")
    @PostMapping("/establishCompetition")
    @ResponseBody
    public AjaxResult establishCompetition(@RequestBody CompetitionVo entity){
        //校验数据字段
        if (StringUtils.isEmpty(entity)) {
            throw new InvalidParameterException("参数异常，非法操作！");
        }
        if (StringUtils.isEmpty(entity.getCompetitionName())) {
            throw new InvalidParameterException("赛会名称【competitionName】不能为空！");
        } else if (StringUtils.isEmpty(entity.getCityCode())) {
            throw new InvalidParameterException("举办城市【cityCode】不能为空！");
        } else if (StringUtils.isEmpty(entity.getCompetitionType())) {
            throw new InvalidParameterException("赛制【competitionType】不能为空！");
        } else if (StringUtils.isEmpty(entity.getEnrollBeginTime())) {
            throw new InvalidParameterException("报名开始时间【EnrollBeginTime】不能为空！");
        } else if (StringUtils.isEmpty(entity.getEnrollEndTime())) {
            throw new InvalidParameterException("报名结束时间【EnrollEndTime】不能为空！");
        } else if (StringUtils.isEmpty(entity.getCompetitionBeginTime())) {
            throw new InvalidParameterException("比赛开始时间【CompetitionBeginTime】不能为空！");
        } else if (StringUtils.isEmpty(entity.getCompetitionEndTime())) {
            throw new InvalidParameterException("比赛结束时间【CompetitionEndTime】不能为空！");
        } else if (StringUtils.isEmpty(entity.getContacts())) {
            throw new InvalidParameterException("赛会联系人【Contacts】不能为空！");
        } else if (StringUtils.isEmpty(entity.getContactsAreaCode())) {
            throw new InvalidParameterException("赛会联系人电话区号【ContactsAreaCode】不能为空！");
        } else if (StringUtils.isEmpty(entity.getContactsTel())) {
            throw new InvalidParameterException("赛会联系人电话【ContactsTel】不能为空！");
        } else if (StringUtils.isEmpty(entity.getCaptcha())) {
            throw new InvalidParameterException("短信验证码【Captcha】不能为空！");
        }
        //获取短信验证码校验短信验证码是否有效
        Object captcha = redisService.getCacheObject(Constants.ESTABLISH_COMPETITION_SMS_CAPTCHA + entity.getContactsTel());
        if (!entity.getCaptcha().equals(String.valueOf(captcha))) {
            throw new ServiceException("短信验证码【"+entity.getCaptcha()+"】已过期或有误！请重新获取！");
        }
        return AjaxResult.success(competitionService.establishCompetition(entity));
    }

    @PostMapping("/getCompetitionByCondition")
    @ApiOperation(ApiTerminal.wxMiniProgram+"获取赛会数据")
    @ResponseBody
    public TableDataInfo getCompetitionByCondition(@RequestBody CompetitionVo competition){
        startPage();
        competition.setIsDeleted(0);
        List<Competition> list = competitionService.getCompetitionByCondition(competition);
        return getDataTable(list);
    }
    @PostMapping("/getAllCompetition")
    @ResponseBody
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"分页获取所有比赛列表")
    public TableDataInfo getAllCompetition(@RequestBody CompetitionVo entity) {
        entity.setIsDeleted(0);
        List<Competition> list = competitionService.getCompetitionByCondition(entity);
        return getDataTable(list);
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"编辑")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(@RequestBody Competition entity) throws Exception {
        if (StringUtils.isEmpty(entity)) {
            throw new ServiceException("参数异常，非法操作！");
        }
        if (StringUtils.isEmpty(entity.getId())) {
            throw new ServiceException("id为空！");
        }
        competitionService.edit(entity);
        return AjaxResult.success();
    }

    @PostMapping("/sendSms")
    @ApiOperation(ApiTerminal.wxMiniProgram+"创建赛会-发送手机验证码")
    @ResponseBody
    public AjaxResult sendSms(@RequestBody Sms sms) throws Exception {
        if (StringUtils.isEmpty(sms)) {
            throw new InvalidParameterException("sms is empty!");
        } else if (StringUtils.isEmpty(sms.getMb())) {
            throw new InvalidParameterException("手机号码不能为空!");
        }
        //生成6位随机数
        int randomNums = (int) ((Math.random() * 9 + 1) * 100000);
        if (StringUtils.isEmpty(sms.getMs())) {
            StringBuffer msg = new StringBuffer(Constants.SMS_PAOPAO_SIGN);
            msg.append("你的验证码为");
            msg.append(randomNums);
            msg.append(",有效时间");
            msg.append(Constants.SMS_PAOPAO_EXPIRES / 60);
            msg.append("分钟,请勿泄露给他人");
            sms.setMs(msg.toString());
        } else {
            sms.setMs(sms.getMs().replace("{}", String.valueOf(randomNums)));
        }
        SmsResponse smsResponse = smsService.sendSms(sms);
        if (smsResponse.getStatus() == 0) {
            //保存到缓存
            redisService.setCacheObject(Constants.ESTABLISH_COMPETITION_SMS_CAPTCHA + sms.getMb(), randomNums, Constants.SMS_PAOPAO_EXPIRES, TimeUnit.SECONDS);
        }
        return AjaxResult.success(smsResponse);
    }

    @PostMapping(value = "/teamEnrollExcleImport",produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"导入球队报名excel(包含图片)")
    public AjaxResult teamEnrollExcleImport(
            @RequestParam(value = "competitionId", required = true) Long competitionId,
            @RequestParam("file") MultipartFile file) throws Exception {
        CompetitionExcleVo excleVo = new CompetitionExcleVo();
        String fileName = file.getOriginalFilename();
        // 上传文件为空
        if (StringUtils.isEmpty(fileName)) {
            throw new CheckedException("没有导入文件");
        }
        // 上传文件名格式不正确
        if (fileName.lastIndexOf(".") != -1 && !".xlsx".equals(fileName.substring(fileName.lastIndexOf(".")))) {
            throw new CheckedException("文件名格式不正确, 请使用后缀名为.xlsx的文件");
        }
        String filePath = getFilePath(file);
        Sheet sheet = null;
        Workbook wb = null;
        //读图片--开始
        Map<String, PictureData> maplist = null;
        BufferedInputStream inputStream = new BufferedInputStream(file.getInputStream());
        // 判断用07还是03的方法获取图片
        if (filePath.endsWith("xls")) {
            wb = new HSSFWorkbook(inputStream);
            sheet = wb.getSheetAt(0);
            maplist = getPictures03((HSSFSheet) sheet);
        } else if (filePath.endsWith("xlsx")) {
            wb = new XSSFWorkbook(inputStream);
            sheet = wb.getSheetAt(0);
            maplist = getPictures07((XSSFSheet) sheet);
        } else {
            throw new CheckedException("Excel文件格式不支持");
        }
        excleVo = competitionService.importExcleData(competitionId,maplist,sheet);
        //使用完成关闭
        wb.close();
        if (inputStream != null) {
            inputStream.close();
        }
        return AjaxResult.success(excleVo);
    }

    /**
     * 获取03图片和位置 (xls) clerk
     *
     * @param sheet
     * @return
     * @throws IOException
     */
    public static Map<String, PictureData> getPictures03(HSSFSheet sheet) throws IOException {
        Map<String, PictureData> map = new HashMap<String, PictureData>();
        List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();

        for (HSSFShape shape : list) {
            if (shape instanceof HSSFPicture) {
                HSSFPicture picture = (HSSFPicture) shape;
                HSSFClientAnchor cAnchor = (HSSFClientAnchor) picture.getAnchor();
                HSSFPictureData pdata = picture.getPictureData();
                String key = cAnchor.getRow1() + "_" + cAnchor.getCol1(); // 行号-列号
                map.put(key, pdata);
            }
        }
        return map;
    }

    /**
     * 获取Excel2007图片
     *
     * @param sheet 当前sheet对象
     * @return Map key:图片单元格索引（0_1）String，value:图片流PictureData
     */
    public static Map<String, PictureData> getPictures07(XSSFSheet sheet) {
        int sheetNum = 0;
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
        for (POIXMLDocumentPart dr : sheet.getRelations()) {
            if (dr instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) dr;
                List<XSSFShape> shapes = drawing.getShapes();

                for (XSSFShape shape : shapes) {
                    XSSFPicture pic = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = pic.getPreferredSize();
                    CTMarker ctMarker = anchor.getFrom();

                    String picIndex = ctMarker.getRow() + "_" + ctMarker.getCol();
                    if (sheetNum == ctMarker.getCol()) {
                        sheetIndexPicMap.put(picIndex, pic.getPictureData());

                    }

                }
            }
        }
        return sheetIndexPicMap;
    }

    private String getFilePath(MultipartFile file) {
        String dirPath = FileUtil.getTmpDirPath();
        try {
            InputStream inputStream = file.getInputStream();
            File fromStream = FileUtil.writeFromStream(inputStream, dirPath + "/" + IdUtils.simpleUUID() + file.getOriginalFilename());
            return fromStream.getAbsolutePath();
        } catch (IOException e) {
            throw new CheckedException("导入文件异常");
        }
    }
    @GetMapping("/getTeamEnrollExcleImpData")
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"获取通过excle导入的报名球队以及队员数据")
    public AjaxResult getTeamEnrollExcleImpData(@RequestParam("competitionId") Long competitionId, @RequestParam("userId") Long userId) {
        CompetitionExcleVo excleVo = competitionService.getTeamEnrollExcleImpData(competitionId, userId);
        return AjaxResult.success(excleVo);
    }

}
