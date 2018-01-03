package com.qw.erp.service.member;

import com.qw.erp.eneity.ErpCardInfoDo;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
public interface MemberService {

    /**
     * 根据手机号获取该会员信息和卡信息
     * @param mobile
     * @return
     */
    List<ErpCardInfoDo> findByMobile(String mobile);

    /**
     * 获取一条未用的卡的信息
     * @return
     */
    public ErpCardInfoDo queryUnCardInfo(String mobile);

    /**
     * 更新会员信息
     * @param cardId
     * @param cardInfo
     * @return
     */
    boolean updateMemberInfo(String cardId, ErpCardInfoDo cardInfo);


    /**
     * 查询卡信息
     * @param beginDate             开始时间
     * @param endDate               结束时间
     * @return
     */
    List<ErpCardInfoDo> querySyncCardInfo(String beginDate,String endDate);

    /**
     * 查询卡信息
     * @param cardIds               要查询的会员卡ID列表
     * @return
     */
    List<ErpCardInfoDo> findByCardIds(List<String> cardIds);

    /**
     * 更新会员积分
     * @param cardId             会员id
     * @param score              会员积分
     * @return
     */
    int updateScore(String cardId, double score);


//    /**
//     * 根据会员卡号获取该会员信息和卡信息
//     * @param cardCode
//     * @return
//     */
//    ErpCardInfoDo findByCardCode(String cardCode);
//    /**
//     * 根据会员卡Id获取该会员信息和卡信息
//     * @param cardId
//     * @return
//     */
//    ErpCardInfoDo findByCardId(String cardId);
//
//    /**
//     * 更新会员卡信息
//     * @return
//     */
//    int updateCardInfoById(ErpCardInfoDo cardInfoDo);
//
//    /**
//     * 生成日志
//     * @param tableName
//     * @param updatelog
//     * @param pkvalue
//     * @return
//     */
//    int insertLog(String tableName, String updatelog, String pkvalue);
//
//
//
//    /**
//     * 查询数据库当前时间
//     */
//    public String getCurrentTime();
//
//    public void updateBindDate(String cardId);

}
