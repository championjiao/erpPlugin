package com.qw.erp.service.order;

import com.qw.erp.eneity.ErpOrderDo;

import java.util.List;

public interface ErpOrderService {

    /**
     * 查询当前时段内的订单信息
     * @param beginDate                 查询的开始日期: yyyy-MM-dd
     * @param endDate                   查询的结束日期: yyyy-MM-dd
     * @return                          返回订单数据
     */
    public List<ErpOrderDo> queryErpOrder4AfterDate(String beginDate, String endDate);

}
