package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.Customer;
import com.ruoyi.system.domain.vo.CustomerOrderVo;
import com.ruoyi.system.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.CustomerOrder;
import com.ruoyi.system.service.ICustomerOrderService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 客户-订车Controller
 * 
 * @author ruoyi
 * @date 2023-08-01
 */
@RestController
@RequestMapping("/customerOrder")
public class CustomerOrderController extends BaseController
{
    @Autowired
    private ICustomerOrderService customerOrderService;
    @Autowired
    private ICustomerService customerService;
    /**
     * 查询客户-订车列表
     */
    @RequiresPermissions("system:customerOrder:list")
    @GetMapping("/list")
    public TableDataInfo list(CustomerOrder customerOrder)
    {
        startPage();
        List<CustomerOrder> list = customerOrderService.selectCustomerOrderList(customerOrder);
        return getDataTable(list);
    }

    @GetMapping("/getCustomerOrderPage")
    public TableDataInfo getCustomerOrderPage(CustomerOrderVo customerOrder)
    {
        startPage();
        List<CustomerOrderVo> list = customerOrderService.getCustomerOrderPage(customerOrder);
        return getDataTable(list);
    }

    /**
     * 导出客户-订车列表
     */
    @RequiresPermissions("system:customerOrder:export")
    @Log(title = "客户-订车", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CustomerOrder customerOrder)
    {
        List<CustomerOrder> list = customerOrderService.selectCustomerOrderList(customerOrder);
        ExcelUtil<CustomerOrder> util = new ExcelUtil<CustomerOrder>(CustomerOrder.class);
        util.exportExcel(response, list, "客户-订车数据");
    }

    /**
     * 获取客户-订车详细信息
     */
    @RequiresPermissions("system:customerOrder:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerOrderService.selectCustomerOrderById(id));
    }

    /**
     * 新增客户-订车
     */
    @Log(title = "客户-订车", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerOrder customerOrder)
    {
        Customer customer = new Customer();
        customer.setId(customerOrder.getCustomerId());
        customer.setStatus("order");
        customerService.updateCustomer(customer);
        return toAjax(customerOrderService.insertCustomerOrder(customerOrder));
    }

    /**
     * 修改客户-订车
     */
    @RequiresPermissions("system:customerOrder:edit")
    @Log(title = "客户-订车", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerOrder customerOrder)
    {
        return toAjax(customerOrderService.updateCustomerOrder(customerOrder));
    }

    /**
     * 删除客户-订车
     */
    @RequiresPermissions("system:customerOrder:remove")
    @Log(title = "客户-订车", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerOrderService.deleteCustomerOrderByIds(ids));
    }
}
