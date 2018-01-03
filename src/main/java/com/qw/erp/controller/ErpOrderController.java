package com.qw.erp.controller;

import com.qw.erp.controller.Vo.ErpOrderListVo;
import com.qw.erp.eneity.ErpOrderDo;
import com.qw.erp.service.order.ErpOrderService;
import com.qw.util.ApiResponse;
import com.qw.util.BaseController;
import com.qw.util.DateFormatUtil;
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
public class ErpOrderController {
    private static final Logger logger = LogManager.getLogger(ErpMemberController.class.getName());
    @Autowired
    private BaseController baseController;
    @Autowired
    private ErpOrderService erpOrderService;

    @ResponseBody
    @RequestMapping(value = "/queryErpOrder4Latest", method = RequestMethod.GET)
    public ApiResponse queryErpOrder4Latest(HttpSession session, HttpServletRequest request){
        Map<String, Object> map = baseController.getParams(true);
        long beginDate = MapUtils.getLong(map,"beginDate");
        long endDate = MapUtils.getLong(map,"endDate");
        logger.info("-->begin params="+map.toString());
        List<ErpOrderDo> erpOrderDos = erpOrderService.queryErpOrder4AfterDate(DateFormatUtil.format(new Date(beginDate),DateFormatUtil.YYYYMMDDHHMMSS), DateFormatUtil.format(new Date(endDate),DateFormatUtil.YYYYMMDDHHMMSS));
        logger.info("-->end params="+erpOrderDos.toString());
        return ApiResponse.OK(new ErpOrderListVo(erpOrderDos));
    }
}
