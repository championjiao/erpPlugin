package com.qw.erp.dao.order.impl;

import com.qw.erp.dao.order.ErpOrderDao;
import com.qw.erp.eneity.ErpOrderDo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("erpOrderDao")
public class ErpOrderDaoImpl implements ErpOrderDao {

    //test
    //test
    //test
    @Value("orderMapper_${erpType}") private String erpType;
    @Resource
    public SqlSessionTemplate sqlSession;

    @Override
    public List<ErpOrderDo> queryErpOrder4AfterDate(String beginDate, String endDate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("beginDate", beginDate);
        map.put("endDate", endDate);
        List<ErpOrderDo> list = sqlSession.selectList(erpType+".queryErpOrder4AfterDate", map);
        if (null == list) {
            list = Collections.EMPTY_LIST;
        }
        return list;
    }
}
