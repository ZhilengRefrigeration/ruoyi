package com.xjs.mall.member.controller;

import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.xjs.mall.member.entity.MemberLevelEntity;
import com.xjs.mall.member.service.MemberLevelService;
import com.xjs.utils.PageUtils;
import com.xjs.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 会员等级
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since 2022-03-15 10:30:19
 */
@RestController
@RequestMapping("member/memberlevel")
@Api(tags = "商城-会员-会员等级")
public class MemberLevelController {
    @Autowired
    private MemberLevelService memberLevelService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberLevelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("信息")
    public R info(@PathVariable("id") Long id) {
        MemberLevelEntity memberLevel = memberLevelService.getById(id);

        return R.ok().put("memberLevel", memberLevel);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存")
    @Log(title = "会员等级", businessType = BusinessType.INSERT)
    public R save(@RequestBody MemberLevelEntity memberLevel) {
        memberLevelService.save(memberLevel);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation("修改")
    @Log(title = "会员等级", businessType = BusinessType.UPDATE)
    public R update(@RequestBody MemberLevelEntity memberLevel) {
        memberLevelService.updateById(memberLevel);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除")
    @Log(title = "会员等级", businessType = BusinessType.DELETE)
    public R delete(@RequestBody Long[] ids) {
        memberLevelService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
