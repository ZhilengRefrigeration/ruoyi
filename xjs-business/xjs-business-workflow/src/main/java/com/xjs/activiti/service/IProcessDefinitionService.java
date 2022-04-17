package com.xjs.activiti.service;

import com.github.pagehelper.Page;
import com.ruoyi.common.core.web.page.PageDomain;
import com.xjs.activiti.domain.dto.DefinitionIdDTO;
import com.xjs.activiti.domain.dto.ProcessDefinitionDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 流程定义服务接口
 *
 * @author xiejs
 * @since 2022-04-17 01:50:42
 */
public interface IProcessDefinitionService {
    /**
     * 获取流程定义集合
     *
     * @param processDefinition
     * @return Page 分页信息
     */
    Page<ProcessDefinitionDTO> selectProcessDefinitionList(ProcessDefinitionDTO processDefinition, PageDomain pageDomain);

    /**
     * 按实例 ID 获取定义
     *
     * @param instanceId 实例id
     * @return 定义实体
     */
    DefinitionIdDTO getDefinitionsByInstanceId(String instanceId);

    /**
     * 删除流程定义
     *
     * @param id
     * @return
     */
    int deleteProcessDefinitionById(String id);

    /**
     * 上传并部署流程定义
     *
     * @param file
     * @return
     * @throws IOException
     */
    void uploadStreamAndDeployment(MultipartFile file) throws IOException;

    /**
     * 启动挂起流程流程定义
     *
     * @param id           流程定义id
     * @param suspendState 流程状态
     * @return
     */
    void suspendOrActiveApply(String id, Integer suspendState);

    /**
     * 上传流程流程定义
     *
     * @param multipartFile
     * @return
     */
    String upload(MultipartFile multipartFile) throws IOException;

    /**
     * 通过stringBPMN添加流程定义
     *
     * @param stringBPMN
     * @return
     */
    void addDeploymentByString(String stringBPMN);

    /**
     * 获取流程定义XML
     *
     * @param response
     * @param deploymentId
     * @param resourceName
     */
    void getProcessDefineXML(HttpServletResponse response, String deploymentId, String resourceName) throws IOException;
}
