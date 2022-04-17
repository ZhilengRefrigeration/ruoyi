package com.xjs.zol.webmagic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xjs.zol.pojo.ZolPhone;
import com.xjs.zol.service.ZolPhoneService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Objects;

/**
 * 中关村手机页面爬虫数据处理
 *
 * @author xiejs
 * @since 2022-04-17
 */
@Component
@Log4j2
@Transactional
public class ZolPhonePipeline implements Pipeline {

    @Autowired
    private ZolPhoneService zolPhoneService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<ZolPhone> zolPhoneList = resultItems.get("zolPhoneList");
        //循环遍历集合，当对象的名称在数据库为空才插入数据
        for (ZolPhone zolPhone : zolPhoneList) {
            ZolPhone dbData = zolPhoneService.getOne(new LambdaQueryWrapper<ZolPhone>()
                    .eq(ZolPhone::getPhoneName, zolPhone.getPhoneName()), false);
            if (Objects.isNull(dbData)) {
                zolPhoneService.save(zolPhone);
            }
        }
    }
}
