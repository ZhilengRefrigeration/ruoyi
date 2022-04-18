package com.xjs.zol.webmagic;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xjs.zol.pojo.ZolNotebook;
import com.xjs.zol.pojo.ZolPhone;
import com.xjs.zol.service.ZolNotebookService;
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
public class ZolPipeline implements Pipeline {

    @Autowired
    private ZolPhoneService zolPhoneService;
    @Autowired
    private ZolNotebookService zolNotebookService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<ZolPhone> zolPhoneList = resultItems.get("zolPhoneList");
        this.saveOrUpdateZolPhoneData(zolPhoneList);

        List<ZolNotebook> zolNotebookList = resultItems.get("zolNotebookList");
        this.saveOrUpdateZolNotebookData(zolNotebookList);
    }

    /**
     * 更新或保存中关村手机数据
     *
     * @param zolPhoneList 手机集合
     */
    private void saveOrUpdateZolPhoneData(List<ZolPhone> zolPhoneList) {
        if (CollUtil.isNotEmpty(zolPhoneList)) {
            //循环遍历集合，当对象的名称在数据库为空才插入数据
            for (ZolPhone zolPhone : zolPhoneList) {
                ZolPhone dbData = zolPhoneService.getOne(new LambdaQueryWrapper<ZolPhone>().eq(ZolPhone::getPhoneName, zolPhone.getPhoneName()), false);
                if (Objects.isNull(dbData)) {
                    zolPhoneService.save(zolPhone);
                } else {
                    //当前值与数据库热度值不相等的情况下更新数据库
                    if (zolPhone.getHeat().compareTo(dbData.getHeat()) != 0) {
                        zolPhoneService.update(new LambdaUpdateWrapper<ZolPhone>().eq(ZolPhone::getPhoneName, zolPhone.getPhoneName()).set(ZolPhone::getHeat, zolPhone.getHeat()));
                    }

                    //更新参考价
                    if (zolPhone.getPrice().compareTo(dbData.getPrice()) != 0) {
                        zolPhoneService.update(new LambdaUpdateWrapper<ZolPhone>().eq(ZolPhone::getPhoneName, zolPhone.getPhoneName()).set(ZolPhone::getPrice, zolPhone.getPrice()));
                    }
                }
            }
        }
    }

    /**
     * 更新或保存中关村笔记本数据
     *
     * @param zolNotebookList 笔记本集合
     */
    private void saveOrUpdateZolNotebookData(List<ZolNotebook> zolNotebookList) {
        if (CollUtil.isNotEmpty(zolNotebookList)) {
            //循环遍历集合，当对象的名称在数据库为空才插入数据
            for (ZolNotebook zolNotebook : zolNotebookList) {
                ZolNotebook dbData = zolNotebookService.getOne(new LambdaQueryWrapper<ZolNotebook>().eq(ZolNotebook::getNotebookName, zolNotebook.getNotebookName()), false);
                if (Objects.isNull(dbData)) {
                    zolNotebookService.save(zolNotebook);
                } else {
                    //当前值与数据库热度值不相等的情况下更新数据库
                    if (zolNotebook.getHeat().compareTo(dbData.getHeat()) != 0) {
                        zolNotebookService.update(new LambdaUpdateWrapper<ZolNotebook>().eq(ZolNotebook::getNotebookName, zolNotebook.getNotebookName()).set(ZolNotebook::getHeat, zolNotebook.getHeat()));
                    }

                    //更新参考价
                    if (!zolNotebook.getPrice().equals(dbData.getPrice())) {
                        zolNotebookService.update(new LambdaUpdateWrapper<ZolNotebook>().eq(ZolNotebook::getNotebookName, zolNotebook.getNotebookName()).set(ZolNotebook::getPrice, zolNotebook.getPrice()));
                    }
                }
            }
        }
    }
}
