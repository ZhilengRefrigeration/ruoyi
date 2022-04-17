package com.xjs.activiti.mapper;

import com.xjs.activiti.domain.vo.ActReDeploymentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * act部署Mapper
 *
 * @author xiejs
 * @since 2022-04-17 01:47:24
 */
public interface ActReDeploymentMapper {

    /**
     * 根据id s批量查询部署信息
     * @param ids 部署ids
     * @return list
     */
    List<ActReDeploymentVO> selectActReDeploymentByIds(@Param("ids") Set<String> ids);

}
