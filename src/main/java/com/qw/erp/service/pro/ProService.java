package com.qw.erp.service.pro;

import com.qw.erp.eneity.ErpBranchProInfoDo;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
public interface ProService {

    /**
     * 查询当前库存和价格有变化的门店商品总数，三日内
     * @param orgId                          门店id
     * @param beginDate                      查询开始时间
     * @param endDate                        查询结束时间
     * @return
     */
    public List<ErpBranchProInfoDo> queryProductInfoByBranch(String orgId,String beginDate,String endDate);
}
