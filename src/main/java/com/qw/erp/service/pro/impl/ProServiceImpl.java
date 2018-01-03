package com.qw.erp.service.pro.impl;

import com.qw.erp.dao.member.MemberDao;
import com.qw.erp.dao.pro.ProDao;
import com.qw.erp.eneity.ErpBranchProInfoDo;
import com.qw.erp.service.pro.ProService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
@Service("proService")
public class ProServiceImpl implements ProService {
    private static final Logger logger = LogManager.getLogger(ProServiceImpl.class.getName());

    @Autowired
    private ProDao proDao;

    @Override
    public List<ErpBranchProInfoDo> queryProductInfoByBranch(String orgId, String beginDate,String endDate){
        return proDao.queryProductInfoByBranch(orgId,beginDate,endDate);
    }

}
