package com.qw.erp.service.member.impl;

import com.qw.erp.dao.member.MemberDao;
import com.qw.erp.eneity.ErpCardInfoDo;
import com.qw.erp.service.member.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    private static final Logger logger = LogManager.getLogger(MemberServiceImpl.class.getName());

    //卡类型  生成卡的时候用哪种卡类型生成
    @Value("${kachId}") private String kachId;

    @Autowired
    private MemberDao memberDao;

    @Override
    public List<ErpCardInfoDo> findByMobile(String mobile) {
        return memberDao.findByMobile(mobile);
    }

    @Override
    public ErpCardInfoDo queryUnCardInfo(String mobile) {
        return memberDao.queryUnCardInfo(kachId,mobile);
    }

    @Override
    public boolean updateMemberInfo(String cardId, ErpCardInfoDo cardInfo){
        ErpCardInfoDo cardInfoDo = this.findByCardId(cardId);
        if(null == cardInfoDo){
            return false;
        }
        String tableName ="GPCS_INSIDER";
        String updatelogFormat = "修改会员ID=%s:会员姓名由%s改为%s,性别由%s改为%s,手机由%s改为%s";
        String updatelog = String.format(updatelogFormat,cardInfoDo.getCardId(),cardInfoDo.getName(), cardInfo.getName(),cardInfoDo.getGender(),cardInfo.getGender(),cardInfoDo.getMobile(),cardInfo.getMobile());
        cardInfoDo.setMobile(cardInfo.getMobile());
        cardInfoDo.setName(cardInfo.getName());
        cardInfoDo.setBirthday(cardInfo.getBirthday());
        cardInfoDo.setGender(cardInfo.getGender());
        cardInfoDo.setOrgId(cardInfo.getOrgId());
        this.updateCardInfoById(cardInfoDo);
        this.insertLog(tableName,updatelog,cardInfoDo.getCardId());
        return true;
    }

    public ErpCardInfoDo findByCardId(String cardId) {
        return memberDao.findByCardId(cardId);
    }

    @Override
    public List<ErpCardInfoDo> findByCardIds(List<String> cardIds) {
        return memberDao.findByCardIds(cardIds);
    }


    public void updateCardInfoById(ErpCardInfoDo cardInfoDo) {
        memberDao.updateCardInfoById(cardInfoDo);
    }


    public int insertLog(String tableName, String updatelog, String pkvalue) {
        return memberDao.insertLog(tableName,updatelog,pkvalue);
    }

    @Override
    public int updateScore(String cardId, double score) {
        ErpCardInfoDo cardInfoDo = this.findByCardId(cardId);
        if(null == cardInfoDo){
            return 0;
        }
        String id = memberDao.queryPuanHqBjfSeq();
        memberDao.insertPuanHyBjf(id,cardId,score);
        memberDao.insertGpcsInsiderIntegralinfo(id,score);
        memberDao.updateScore(cardId,score);
        memberDao.updatePuanHyBjf(id);
        return 1;
    }

    @Override
    public List<ErpCardInfoDo> querySyncCardInfo(String beginDate,String endDate) {
        return memberDao.querySyncCardInfo(beginDate,endDate);
    }

}
