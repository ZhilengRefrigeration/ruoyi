package com.ruoyi.potenza.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.potenza.domain.TbBorrower;
import com.ruoyi.potenza.domain.vo.IdVo;
import com.ruoyi.potenza.service.TbBorrowerService;
import com.ruoyi.potenza.mapper.TbBorrowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 86155
* @description 针对表【tb_borrower(贷款表)】的数据库操作Service实现
* @createDate 2023-01-13 15:56:37
*/
@Service
public class TbBorrowerServiceImpl extends ServiceImpl<TbBorrowerMapper, TbBorrower>
    implements TbBorrowerService{

    @Autowired
    private TbBorrowerMapper tbBorrowerMapper;

    @Override
    public List<?> pageList(TbBorrower tbBorrower) {
       List<TbBorrower> list= tbBorrowerMapper.pageList();
        return list;
    }

    @Override
    public AjaxResult borrowerDele(IdVo idVo) {
        tbBorrowerMapper.deleteById(idVo.getId());
        return AjaxResult.success("删除成功");
    }



    /**
     * 查询贷款
     *
     * @param borrowerId 贷款主键
     * @return 贷款
     */
    @Override
    public TbBorrower selectTbBorrowerByBorrowerId(Long borrowerId)
    {
        return tbBorrowerMapper.selectTbBorrowerByBorrowerId(borrowerId);
    }

    /**
     * 查询贷款列表
     *
     * @param tbBorrower 贷款
     * @return 贷款
     */
    @Override
    public List<TbBorrower> selectTbBorrowerList(TbBorrower tbBorrower)
    {
        return tbBorrowerMapper.selectTbBorrowerList(tbBorrower);
    }

    /**
     * 新增贷款
     *
     * @param tbBorrower 贷款
     * @return 结果
     */
    @Override
    public int insertTbBorrower(TbBorrower tbBorrower)
    {
        tbBorrower.setCreateTime(DateUtils.getNowDate());
        return tbBorrowerMapper.insertTbBorrower(tbBorrower);
    }

    /**
     * 修改贷款
     *
     * @param tbBorrower 贷款
     * @return 结果
     */
    @Override
    public int updateTbBorrower(TbBorrower tbBorrower)
    {
        tbBorrower.setUpdateTime(DateUtils.getNowDate());
        return tbBorrowerMapper.updateTbBorrower(tbBorrower);
    }




}




