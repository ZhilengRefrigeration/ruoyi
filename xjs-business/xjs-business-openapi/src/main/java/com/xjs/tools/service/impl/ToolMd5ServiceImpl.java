package com.xjs.tools.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.tools.domain.ToolMd5;
import com.xjs.tools.mapper.ToolMd5Mapper;
import com.xjs.tools.service.ToolMd5Service;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * md5工具service实现
 * @author xiejs
 * @since 2022-05-10
 */
@Service
public class ToolMd5ServiceImpl extends ServiceImpl<ToolMd5Mapper, ToolMd5> implements ToolMd5Service {

    @Override
    public ToolMd5 encryption(String str) {
        String md5Hex32 = DigestUtil.md5Hex(str);
        String md5Hex16 = DigestUtil.md5Hex16(str);

        ToolMd5 toolMd5 = new ToolMd5();
        toolMd5.setSource(str);
        toolMd5.setTarget16(md5Hex16);
        toolMd5.setTarget32(md5Hex32);

        try {
            LambdaQueryWrapper<ToolMd5> wrapper = new LambdaQueryWrapper<ToolMd5>()
                    .eq(ToolMd5::getTarget16, toolMd5.getTarget16())
                    .eq(ToolMd5::getTarget32, toolMd5.getTarget32());

            ToolMd5 one = super.getOne(wrapper, false);
            if (Objects.isNull(one)) {
                super.save(toolMd5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toolMd5;
    }

    @Override
    public String decrypt(String str) {
        str = str.toLowerCase();

        LambdaQueryWrapper<ToolMd5> wrapper = new LambdaQueryWrapper<ToolMd5>()
                .eq(ToolMd5::getTarget32, str)
                .or()
                .eq(ToolMd5::getTarget16, str);

        ToolMd5 one = super.getOne(wrapper, false);

        if (one != null) {
            return one.getSource();
        }

        return "";
    }
}
