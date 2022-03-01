package com.xjs.srb.core.controller.admin;


import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.exception.BusinessException;
import com.xjs.srb.core.entity.IntegralGrade;
import com.xjs.srb.core.service.IIntegralGradeService;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import com.xjs.web.MyBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author xiejs
 * @since 2022-02-28
 */
@RestController
@RequestMapping("/admin/core/integralGrade")
@Api(tags = "尚融宝-积分管理")
public class IntegralGradeController extends MyBaseController<IntegralGrade> {

    @Autowired
    private IIntegralGradeService integralGradeService;

    @GetMapping("/list")
    @ApiOperation("获取积分等级列表")
    @RequiresPermissions("srb:integralGrade:list")
    public AjaxResult listAll() {
        return AjaxResult.success(integralGradeService.list());
    }

    @DeleteMapping("/remove/{id}")
    @ApiOperation("根据id删除积分等级")
    @RequiresPermissions("srb:integralGrade:remove")
    @Log(title = "融-积分管理", businessType = BusinessType.DELETE)
    public AjaxResult removeId(@PathVariable Long id) {
        return toAjax(integralGradeService.removeById(id));
    }

    @GetMapping("get/{id}")
    @ApiOperation("根据id获取积分等级")
    @RequiresPermissions("srb:integralGrade:query")
    public AjaxResult getById(@PathVariable Long id) {
        return AjaxResult.success(integralGradeService.getById(id));
    }

    @PutMapping("update")
    @ApiOperation("根据id更新积分等级")
    @RequiresPermissions("srb:integralGrade:update")
    @Log(title = "融-积分管理", businessType = BusinessType.UPDATE)
    public AjaxResult updateById(@Validated(UpdateGroup.class) @RequestBody IntegralGrade integralGrade) {
        this.compareSize(integralGrade);
        return toAjax(integralGradeService.updateById(integralGrade));
    }

    @PostMapping("save")
    @ApiOperation("保存积分等级")
    @RequiresPermissions("srb:integralGrade:save")
    @Log(title = "融-积分管理", businessType = BusinessType.INSERT)
    public AjaxResult save(@Validated(AddGroup.class) @RequestBody IntegralGrade integralGrade) {
        this.compareSize(integralGrade);
        return toAjax(integralGradeService.save(integralGrade));
    }


    /**
     * 比较大小
     * @param integralGrade 积分实体
     */
    private void compareSize(IntegralGrade integralGrade) {
        if (integralGrade.getIntegralStart() > integralGrade.getIntegralEnd()) {
            throw new BusinessException("开始区间大于结束区间！！！");
        }
    }

}

