package com.xjs.activiti.controller;


import com.ruoyi.common.core.web.domain.AjaxResult;
import com.xjs.activiti.domain.dto.ActivitiHighLineDTO;
import com.xjs.activiti.service.IActivitiHistoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/activitiHistory")
@Api(tags = "工作流-历史流程")
public class ActivitiHistoryController {

    @Autowired
    private IActivitiHistoryService activitiHistoryService;

    //流程图高亮
    @GetMapping("/gethighLine")
    public AjaxResult gethighLine(@RequestParam("instanceId") String instanceId) {

        ActivitiHighLineDTO activitiHighLineDTO = activitiHistoryService.gethighLine(instanceId);
        return AjaxResult.success(activitiHighLineDTO);


    }


}
