package com.qw.erp.controller;

import com.qw.erp.controller.Vo.ErpCardListVo;
import com.qw.erp.controller.Vo.ErpMemberScoreListVo;
import com.qw.erp.controller.Vo.ErpMemberScoreVo;
import com.qw.erp.eneity.ErpCardInfoDo;
import com.qw.erp.service.member.MemberService;
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
import java.util.*;

@Controller
@RequestMapping("erp/other/member")
public class ErpMemberController {
    private static final Logger logger = LogManager.getLogger(ErpMemberController.class.getName());
    @Autowired
    private BaseController baseController;
    @Autowired
    private MemberService memberService;

    //如果手机号在ERP中没有，则生成一个会员卡返回
    //如果手机号存在，则直接返回相应的会员卡信息，可能存在多个会员卡信息
    @ResponseBody
    @RequestMapping(value = "/generateCard",  method = RequestMethod.GET)
    public ApiResponse generateCard(HttpSession session, HttpServletRequest request){
        Map<String, Object> map = baseController.getParams(true);
        String mobile = MapUtils.getString(map,"mobile");

        logger.debug("-->begin params="+map.toString());
        if(StringUtils.isNullOrEmpty(mobile)){
            throw new QwException(2022010,"参数异常");
        }

        List<ErpCardInfoDo> erpCardInfoDos = memberService.findByMobile(mobile);
        //如果有卡直接返回数据
        if(erpCardInfoDos.size() == 0){
            //找到一个未用的卡返回 且置为已用 手机号也要存进去
            ErpCardInfoDo cardInfo = memberService.queryUnCardInfo(mobile);
            if(null != cardInfo){
                if(erpCardInfoDos == null){
                    erpCardInfoDos = new ArrayList<ErpCardInfoDo>();
                }
                erpCardInfoDos.add(cardInfo);
                logger.info("-->电话号码------------------------------>"+mobile+"绑定卡id------------------------------>"+cardInfo.getCardId());
            }
            else{
                //邮件通知相关人员进行处理
                logger.info("没有可用的卡拉------------------------------>");
                logger.info("没有可用的卡拉------------------------------>");
                logger.info("没有可用的卡拉------------------------------>");
                throw new QwException(2022012,"没有可用的卡拉");
            }
        }

        logger.debug("-->end result=" + erpCardInfoDos.toString());
        return ApiResponse.OK(new ErpCardListVo(erpCardInfoDos));
    }

    @ResponseBody
    @RequestMapping(value = "/updateMemberInfo", method = RequestMethod.GET)
    public ApiResponse updateMemberInfo(HttpSession session, HttpServletRequest request){
        Map<String, Object> map = baseController.getParams(true);
        logger.debug("-->begin params="+map.toString());

        String name = MapUtils.getString(map,"erpName");
        String birthday = MapUtils.getString(map,"erpBirthday");
        String gender = MapUtils.getString(map,"erpGender");
        //默认置为空非男非女
        if (gender == null) gender="";
        String cardId = MapUtils.getString(map,"erpCardId");
        String orgId = MapUtils.getString(map,"erpOrgId");
        String erpCardType = MapUtils.getString(map,"erpCardType");
        String erpSaler = MapUtils.getString(map,"erpSaler");

        if(StringUtils.isNullOrEmpty(cardId) ){
            throw new QwException(2022010,"参数异常");
        }
        ErpCardInfoDo cardInfo = new ErpCardInfoDo();
        cardInfo.setName(name);
        cardInfo.setBirthday(birthday);
        cardInfo.setGender(gender);
        cardInfo.setOrgId(orgId);
        cardInfo.setErpCardType(erpCardType);
        cardInfo.setSaler(erpSaler);
        cardInfo.setCardId(cardId);

        // TODO: 2018-01-02 数据库插入异常的抛出
        if(!memberService.updateMemberInfo(cardId, cardInfo)){
            logger.debug("-->end result= 更新失败...");
            throw new QwException(2022011,"更新失败...");
        }
        logger.debug("-->end result= 更新成功");
        return ApiResponse.OK();
    }

    //更新单个会员的积分
    @ResponseBody
    @RequestMapping(value = "/syncScore", method = RequestMethod.GET)
    public ApiResponse syncScore(HttpSession session, HttpServletRequest request){
        Map<String, Object> map = baseController.getParams(true);
        String cardId = MapUtils.getString(map,"cardId");
        double score = MapUtils.getDoubleValue(map,"score");
        logger.info("-->单个积分同步  cardId===>" + cardId + "          score===>" + score);
        memberService.updateScore(cardId, score);
        return ApiResponse.OK() ;
    }

    //批量更新会员积分 一般一次50个
    @ResponseBody
    @RequestMapping(value = "/batchSyncScore",method = RequestMethod.GET)
    public ApiResponse batchSyncCard(HttpSession session, HttpServletRequest request){
        Map<String, Object> map = baseController.getParams(true);
        String paramStr = MapUtils.getString(map,"paramStr");
        logger.info("-->begin params="+map.toString());
        if(StringUtils.isNullOrEmpty(paramStr) ){
            throw new QwException(2022010,"参数异常");
        }
        List<String> list = Arrays.asList(paramStr.split(","));
        for (String str :list){
            String cardId = str.split(":")[0];
            String score = str.split(":")[1];
            double d_score = Double.parseDouble(score);
            logger.info("-->批量积分同步  cardId===>" + cardId + "===score===>" + score);
            memberService.updateScore(cardId, d_score);
        }

        logger.info("-->end params="+map.toString());
        return ApiResponse.OK() ;
    }

    //查询会员积分 根据会员id查询
    @ResponseBody
    @RequestMapping(value = "/batchSearchCard", method = RequestMethod.GET)
    public ApiResponse batchSearchCard(HttpSession session, HttpServletRequest request){
        Map<String, Object> map = baseController.getParams(true);
        String cardIds = MapUtils.getString(map,"cardIds");
        logger.info("-->begin params="+map.toString());
        if(StringUtils.isNullOrEmpty(cardIds)){
            throw new QwException(2022010,"参数异常");
        }

        List<ErpCardInfoDo> cardCodeDos = memberService.findByCardIds(Arrays.asList(cardIds.split(",")));
        ErpMemberScoreListVo memberCardList = new ErpMemberScoreListVo();
        for (ErpCardInfoDo cardCodeDo : cardCodeDos) {
            ErpMemberScoreVo erpMemberScore = new ErpMemberScoreVo();
            erpMemberScore.setErpCardId(cardCodeDo.getCardId());
            erpMemberScore.setErpScore(cardCodeDo.getScore());
            memberCardList.getCardList().add(erpMemberScore);
        }
        logger.info("-->end params="+map.toString());
        return ApiResponse.OK(memberCardList);
    }

    //查询会员积分 根据日期查询
    @ResponseBody
    @RequestMapping(value = "/querySyncCardInfo", method = RequestMethod.GET)
    public ApiResponse querySyncCardInfo(HttpSession session, HttpServletRequest request){
        Map<String, Object> map = baseController.getParams(true);
        logger.info("-->begin params="+map.toString());
        long beginDate = MapUtils.getLong(map,"beginDate");
        long endDate = MapUtils.getLong(map,"endDate");
        List<ErpCardInfoDo> cardCodeDos = memberService.querySyncCardInfo(DateFormatUtil.format(new Date(beginDate),DateFormatUtil.YYYYMMDDHHMMSS),
                DateFormatUtil.format(new Date(endDate),DateFormatUtil.YYYYMMDDHHMMSS));
        ErpMemberScoreListVo memberCardList = new ErpMemberScoreListVo();
        for (ErpCardInfoDo cardCodeDo : cardCodeDos) {
            ErpMemberScoreVo erpMemberScore = new ErpMemberScoreVo();
            erpMemberScore.setErpCardId(String.valueOf(cardCodeDo.getCardId()));
            erpMemberScore.setErpScore(cardCodeDo.getScore());
            memberCardList.getCardList().add(erpMemberScore);
            logger.info("-->" + erpMemberScore.toString());
        }

        logger.info("-->end params="+map.toString());
        return ApiResponse.OK(memberCardList) ;
    }
}
