package com.qw.erp.dao.pro.impl;

import com.qw.erp.dao.pro.ProDao;
import com.qw.erp.eneity.ErpBranchProInfoDo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/17.
 */
@Repository("proDao")
public class ProDaoImpl implements ProDao {

    @Value("pro_${erpType}") private String erpType;
    @Resource
    public SqlSessionTemplate sqlSession;

    @Override
    public List<ErpBranchProInfoDo> queryProductInfoByBranch(String orgId, String beginDate, String endDate){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("orgId",orgId);
        map.put("beginDate",beginDate);
        map.put("endDate",endDate);
        // TODO: 2018-01-02 这里没有完成
        List<ErpBranchProInfoDo> resultList = sqlSession.selectList(erpType+".queryProductInfoByBranch", map);
        if(null == resultList ||resultList.isEmpty()){
            resultList = Collections.EMPTY_LIST;
        }
        return resultList;
    }
}
