package com.qw.erp.dao.order;


import com.qw.erp.eneity.ErpOrderDo;

import java.util.List;

/**
 * @className: ErpOrderDao
 * @description: /*
 * 　　┏┓　　　┏┓+ +
 * 　┏┛┻━━━┛┻┓ + +
 * 　┃　　　　　　　┃
 * 　┃　　　━　　　┃ ++ + + +
 * 　┃████━████  ┃+
 * 　┃　　　　　　　┃ +
 * 　┃　　　┻　　　┃
 * 　┃　　　　　　　┃ + +
 * 　┗━┓　　　┏━┛
 * 　　　┃　　　┃
 * 　　　┃　　　┃ + + + +
 * 　　　┃　　　┃
 * 　　　┃　　　┃ +  神兽保佑
 * 　　　┃　　　┃    代码无bug
 * 　　　┃　　　┃　　+
 * 　　　┃　 　　┗━━━┓ + +
 * 　　　┃ 　　　　　　　┣┓
 * 　　　┃ 　　　　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　┃┫┫　┃┫┫
 * 　　　　┗┻┛　┗┻┛+ + + +
 * @author: Administrator
 * @create: 2017/10/12
 **/
public interface ErpOrderDao {

    /**
     * 查询当前时段内的订单信息
     * @param beginDate                 查询的开始日期: yyyy-MM-dd
     * @param endDate                   查询的结束日期: yyyy-MM-dd
     * @return                          返回订单数据
     */
    public List<ErpOrderDo> queryErpOrder4AfterDate(String beginDate, String endDate);
}
