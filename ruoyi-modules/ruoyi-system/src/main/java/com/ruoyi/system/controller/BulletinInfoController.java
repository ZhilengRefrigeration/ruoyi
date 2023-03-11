package com.ruoyi.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.BulletinInfo;
import com.ruoyi.system.domain.BulletinRecive;
import com.ruoyi.system.service.IBulletinInfoService;
import com.ruoyi.system.service.IBulletinReciveService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 公告栏Controller
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
@RestController
@RequestMapping("/bulletin")
public class BulletinInfoController extends BaseController
{
    @Autowired
    private IBulletinInfoService bulletinInfoService;
    
    @Autowired
    private IBulletinReciveService bulletinReciveService;

    /**
     * 查询公告栏列表
     */
    @RequiresPermissions("system:bulletin:list")
    @GetMapping("/list")
    public TableDataInfo list(BulletinInfo bulletinInfo)
    {
        startPage();
        List<BulletinInfo> list = bulletinInfoService.selectBulletinInfoList(bulletinInfo);
        return getDataTable(list);
    }

    /**
     * 获取公告栏详细信息
     */
    @RequiresPermissions("system:bulletin:query")
    @GetMapping(value = "/{bulletinId}")
    public AjaxResult getInfo(@PathVariable("bulletinId") String bulletinId)
    {
        return success(bulletinInfoService.selectBulletinInfoByBulletinId(bulletinId));
    }
    
    /**
     * 发送公告栏
     */
    @RequiresPermissions("system:bulletin:add")
    @Log(title = "公告栏", businessType = BusinessType.OTHER)
    @PostMapping("/batchSend/{bulletinIds}")
    public AjaxResult batchSend(@PathVariable String[] bulletinIds)
    {
        return toAjax(bulletinInfoService.sendBulletinInfo(bulletinIds));
    }

    /**
     * 新增或发送 公告栏
     */
    @RequiresPermissions("system:bulletin:add")
    @Log(title = "公告栏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody BulletinInfo bulletinInfo)
    {
        return toAjax(bulletinInfoService.insertBulletinInfo(bulletinInfo));
    }
    
   

    /**
     * 修改公告栏
     */
    @RequiresPermissions("system:bulletin:edit")
    @Log(title = "公告栏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BulletinInfo bulletinInfo)
    {
        return toAjax(bulletinInfoService.updateBulletinInfo(bulletinInfo));
    }
    
    /**
     *  物理删除公告栏
     */
    @RequiresPermissions("system:bulletin:remove")
    @Log(title = "公告栏", businessType = BusinessType.DELETE)
	@DeleteMapping("/delete/{bulletinIds}")
    public AjaxResult removePysical(@PathVariable String[] bulletinIds)
    {
        return toAjax(bulletinInfoService.deleteBulletinInfoByBulletinIds(bulletinIds,"D"));
    }

    /**
     *  逻辑删除公告栏
     */
    @RequiresPermissions("system:bulletin:remove")
    @Log(title = "公告栏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bulletinIds}")
    public AjaxResult remove(@PathVariable String[] bulletinIds)
    {
        return toAjax(bulletinInfoService.deleteBulletinInfoByBulletinIds(bulletinIds,"B"));
    }
    
    /**
     * 批量阅读成功
     */
    @RequiresPermissions("system:bulletin:edit")
    @PutMapping("/recive/batchRead/{reciveIds}")
    public AjaxResult batchRead(@PathVariable String[] reciveIds)
    {
    	 return toAjax(bulletinReciveService.batchRead(reciveIds));
    }
    
    /**
     * 查询公告接收者列表
     */
    @RequiresPermissions("system:bulletin:list")
    @GetMapping("/recive/list")
    public TableDataInfo reciveList(BulletinRecive bulletinRecive)
    {
        startPage();
        List<BulletinInfo> list = bulletinReciveService.selectBulletinReciveList(bulletinRecive);
        return getDataTable(list);
    }
    
    /**
     * 逻辑删除公告接收者
     */
    @RequiresPermissions("system:bulletin:remove")
    @Log(title = "公告接收者", businessType = BusinessType.DELETE)
	@DeleteMapping("/recive/{reciveIds}")
    public AjaxResult reciveRemove(@PathVariable String[] reciveIds)
    {
        return toAjax(bulletinReciveService.deleteBulletinReciveByReciveIds(reciveIds));
    }
    
    /**
     * 物理删除公告接收者
     */
    @RequiresPermissions("system:bulletin:remove")
    @Log(title = "公告接收者", businessType = BusinessType.DELETE)
	@DeleteMapping("/recive/delete/{reciveIds}")
    public AjaxResult recivePhysicalRemove(@PathVariable String[] reciveIds)
    {
        return toAjax(bulletinReciveService.deletePhysicalBulletinReciveByReciveIds(reciveIds));
    }
    
    /**
     * 获取公告接收者详细信息
     */
    @RequiresPermissions("system:bulletin:query")
    @GetMapping(value = "/recive/{reciveId}")
    public AjaxResult getReciveInfo(@PathVariable("reciveId") String reciveId)
    {
        return success(bulletinReciveService.selectBulletinReciveByReciveId(reciveId));
    }
}
