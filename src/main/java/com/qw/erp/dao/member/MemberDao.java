package com.qw.erp.dao.member;

import com.qw.erp.eneity.ErpCardInfoDo;
import java.util.List;

public interface MemberDao {

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
    public ErpCardInfoDo queryUnCardInfo(String kachId,String mobile);

    /**
     * 根据会员卡Id获取该会员信息和卡信息
     * @param cardId
     * @return
     */
    ErpCardInfoDo findByCardId(String cardId);

    /**
     * 根据会员卡Id列表获取该会员信息和卡信息
     * @param cardIds
     * @return
     */
    List<ErpCardInfoDo> findByCardIds(List<String> cardIds);
    /**
     * 更新会员信息
     * @param cardInfoDo
     * @return
     */

    void updateCardInfoById(ErpCardInfoDo cardInfoDo);
    /**
     * 生成日志
     * @param tableName
     * @param updatelog
     * @param pkvalue
     * @return
     */
    int insertLog(String tableName, String updatelog, String pkvalue);

    /**
     * 查询卡信息
     * @param beginDate             开始时间
     * @param endDate               结束时间
     * @return
     */
    List<ErpCardInfoDo> querySyncCardInfo(String beginDate,String endDate);

    String queryPuanHqBjfSeq();

    /**
     * 积分履历
     * @param id
     * @param cardId
     * @param score
     * @return
     */
    int insertPuanHyBjf(String id, String cardId, double score);

    /**
     * 积分履历
     * @return
     */
    int insertGpcsInsiderIntegralinfo(String id, double score);
    int updateScore(String cardId, double score);
    int updatePuanHyBjf(String id);
}
