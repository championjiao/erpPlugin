package com.qw.erp.controller.Vo;

import com.qw.erp.eneity.ErpOrderDo;
import com.qw.util.ApiBody;

import java.util.ArrayList;
import java.util.List;


public class ErpOrderListVo extends ApiBody {
    private List<ErpOrderDo> orderList = new ArrayList<ErpOrderDo>();

    public ErpOrderListVo(List<ErpOrderDo> list) {
        if (null != list && list.size() > 0){
            orderList.addAll(list);
        }
    }

    public List<ErpOrderDo> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<ErpOrderDo> orderList) {
        this.orderList = orderList;
    }
}
