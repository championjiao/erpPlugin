package com.qw.erp.service.order.impl;

import com.qw.erp.dao.order.ErpOrderDao;
import com.qw.erp.eneity.ErpOrderDo;
import com.qw.erp.service.order.ErpOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: ErpOrderServiceImpl
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
@Service("erpOrderService")
public class ErpOrderServiceImpl implements ErpOrderService {

    @Autowired
    private ErpOrderDao erpOrderDao;

    @Override
    public List<ErpOrderDo> queryErpOrder4AfterDate(String beginDate, String endDate) {
        return erpOrderDao.queryErpOrder4AfterDate(beginDate,endDate);
    }
}
