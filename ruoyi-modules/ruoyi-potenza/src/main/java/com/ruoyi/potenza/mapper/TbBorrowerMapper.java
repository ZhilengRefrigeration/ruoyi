package com.ruoyi.potenza.mapper;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.potenza.domain.TbBorrower;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 86155
* @description 针对表【tb_borrower(贷款表)】的数据库操作Mapper
* @createDate 2023-01-13 15:56:37
* @Entity com.ruoyi.potenza.domain.TbBorrower
*/
public interface TbBorrowerMapper extends BaseMapper<TbBorrower> {

    List<TbBorrower> pageList();



    /**
     * 查询贷款
     *
     * @param borrowerId 贷款主键
     * @return 贷款
     */
    public TbBorrower selectTbBorrowerByBorrowerId(Long borrowerId);

    /**
     * 查询贷款列表
     *
     * @param tbBorrower 贷款
     * @return 贷款集合
     */
    public List<TbBorrower> selectTbBorrowerList(TbBorrower tbBorrower);

    /**
     * 新增贷款
     *
     * @param tbBorrower 贷款
     * @return 结果
     */
    public int insertTbBorrower(TbBorrower tbBorrower);

    /**
     * 修改贷款
     *
     * @param tbBorrower 贷款
     * @return 结果
     */
    public int updateTbBorrower(TbBorrower tbBorrower);




}




