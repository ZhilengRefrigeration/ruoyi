package com.xjs.activiti.service.impl;


import com.xjs.activiti.domain.ActWorkflowFormData;
import com.xjs.activiti.domain.dto.HistoryDataDTO;
import com.xjs.activiti.domain.dto.HistoryFormDataDTO;
import com.xjs.activiti.service.IActWorkflowFormDataService;
import com.xjs.activiti.service.IFormHistoryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 历史数据表单服务接口实现
 *
 * @author xiejs
 * @since 2022-04-17 01:50:13
 */
@Service
public class FormHistoryDataServiceImpl implements IFormHistoryDataService {
    @Autowired
    private IActWorkflowFormDataService actWorkflowFormDataService;


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<HistoryDataDTO> historyDataShow(String businessKey) {
        List<HistoryDataDTO> returnHistoryFromDataDTOS = new ArrayList<>();
        List<ActWorkflowFormData> actWorkflowFormData = actWorkflowFormDataService.selectActWorkflowFormDataByBusinessKey(businessKey);
        Map<String, List<ActWorkflowFormData>> collect = actWorkflowFormData.stream().collect(Collectors.groupingBy(ActWorkflowFormData::getTaskNodeName));
        collect.entrySet().forEach(
                entry -> {
                    HistoryDataDTO returnHistoryFromDataDTO = new HistoryDataDTO();
                    returnHistoryFromDataDTO.setTaskNodeName(entry.getValue().get(0).getTaskNodeName());
                    returnHistoryFromDataDTO.setCreateName(entry.getValue().get(0).getCreateName());
                    returnHistoryFromDataDTO.setCreatedDate(sdf.format(entry.getValue().get(0).getCreateTime()));
                    returnHistoryFromDataDTO.setFormHistoryDataDTO(entry.getValue().stream().map(awfd -> new HistoryFormDataDTO(awfd.getControlName(), awfd.getControlValue())).collect(Collectors.toList()));
                    returnHistoryFromDataDTOS.add(returnHistoryFromDataDTO);
                }
        );
        List<HistoryDataDTO> collect1 = returnHistoryFromDataDTOS.stream().sorted((x, y) -> x.getCreatedDate().compareTo(y.getCreatedDate())).collect(Collectors.toList());

        return collect1;
    }


}
