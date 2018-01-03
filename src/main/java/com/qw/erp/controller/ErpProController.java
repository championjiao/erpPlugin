package com.qw.erp.controller;

import com.qw.erp.controller.Vo.ErpBranchProListVo;
import com.qw.erp.eneity.ErpBranchProInfoDo;
import com.qw.erp.service.pro.ProService;
import com.qw.exception.QwException;
import com.qw.util.ApiResponse;
import com.qw.util.BaseController;
import com.qw.util.DateFormatUtil;
import com.qw.util.StringUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("erp/other/order")
public class ErpProController {
    private static final Logger logger = LogManager.getLogger(ErpMemberController.class.getName());
    @Autowired
    private BaseController baseController;

    @Autowired
    private ProService proService;

    //批量同步商品
    @ResponseBody
    @RequestMapping(value = "/batchSyncProByBranch", method = RequestMethod.GET)
    public ApiResponse batchSyncProByBranch(HttpSession session, HttpServletRequest request){

        Map<String, Object> map = baseController.getParams(true);
        String orgId = MapUtils.getString(map,"orgId");//门店id
        long beginDate = MapUtils.getLongValue(map,"beginDate");//门店id
        long endDate = MapUtils.getLongValue(map,"endDate");//门店id
        logger.info("-->begin params="+map.toString());
        if(StringUtils.isNullOrEmpty(orgId)){
            throw new QwException(2022010,"参数异常");
        }
        List<ErpBranchProInfoDo> productInfoDos = proService.queryProductInfoByBranch(orgId, DateFormatUtil.format(new Date(beginDate),DateFormatUtil.YYYYMMDDHHMMSS),
                DateFormatUtil.format(new Date(endDate),DateFormatUtil.YYYYMMDDHHMMSS));
        return ApiResponse.OK(new ErpBranchProListVo(productInfoDos));
    }
}
