package com.ruoyi.potenza.service;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.potenza.domain.TbBorrower;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.potenza.domain.vo.IdVo;

import java.util.List;

/**
* @author 86155
* @description 针对表【tb_borrower(贷款表)】的数据库操作Service
* @createDate 2023-01-13 15:56:37
*/
public interface TbBorrowerService extends IService<TbBorrower> {

    List<?> pageList(TbBorrower tbBorrower);

    AjaxResult borrowerDele(IdVo idVo);

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


    int borrowerInserts(TbBorrower tbBorrower);

    AjaxResult loans(TbBorrower tbBorrower);
}
