package com.qw.erp.dao.member.impl;

import com.qw.erp.dao.member.MemberDao;
import com.qw.erp.eneity.ErpCardInfoDo;
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
@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {

    @Value("memberMapper_${erpType}") private String erpType;
    @Value("${userName}") private String userName;
    @Value("${userId}") private String userId;
    @Value("${placepointid}") private String placepointid;
    @Resource
    public SqlSessionTemplate sqlSession;


    @Override
    public List<ErpCardInfoDo> findByMobile(String mobile) {
        List<ErpCardInfoDo> contactDocs = sqlSession.selectList(erpType+".queryCardInfoDoByMobile", mobile);
        if(null == contactDocs ||contactDocs.isEmpty()){
            contactDocs = Collections.EMPTY_LIST;
        }
        return contactDocs;
    }



    @Override
    synchronized public ErpCardInfoDo queryUnCardInfo(String kachId,String mobile) {
        List<ErpCardInfoDo> contactDocs = sqlSession.selectList(erpType+".queryUnCardInfo", kachId);
        if(null == contactDocs || contactDocs.isEmpty()){
            return null;
        }
        ErpCardInfoDo cardInfo = contactDocs.get(0);
        updateUnStatusByCardId(cardInfo.getCardId(),mobile);
        return cardInfo;
    }

    //更新卡信息
    public int updateUnStatusByCardId(String cardId,String mobile) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cardId",cardId);
        map.put("mobile",mobile);
        return sqlSession.update(erpType+".updateUnStatusByCardId", map);
    }

    @Override
    public ErpCardInfoDo findByCardId(String cardId) {
        List<ErpCardInfoDo> contactDocs = sqlSession.selectList(erpType+".queryCardInfoDoByCardId", cardId);
        if(null == contactDocs ||contactDocs.isEmpty()){
            return null;
        }
        return contactDocs.get(0);
    }

    @Override
    public List<ErpCardInfoDo> findByCardIds(List<String> cardIds) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cardIds",cardIds);
        List<ErpCardInfoDo> contactDocs = sqlSession.selectList(erpType+".queryCardInfoDoByCardIds", map);
        if(null == contactDocs ||contactDocs.isEmpty()){
            contactDocs = Collections.EMPTY_LIST;
        }
        return contactDocs;
    }

    @Override
    public void updateCardInfoById(ErpCardInfoDo cardInfoDo) {
        sqlSession.update(erpType+".updateCardInfoById", cardInfoDo);
    }

    @Override
    public int insertLog(String tableName,String updatelog,String pkvalue) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("tableName",tableName);
        map.put("userId",userId);
        map.put("userName",userName);
        map.put("updatelog",updatelog);
        map.put("pkvalue",pkvalue);
        return sqlSession.update(erpType+".insertLog", map);
    }

    @Override
    public List<ErpCardInfoDo> querySyncCardInfo(String beginDate,String endDate) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("beginDate",beginDate);
        map.put("endDate",endDate);
        List<ErpCardInfoDo> contactDocs = sqlSession.selectList(erpType+".querySyncCardInfo",map);
        if(null == contactDocs ||contactDocs.isEmpty()){
            contactDocs = Collections.EMPTY_LIST;
        }
        return contactDocs;
    }

    @Override
    public String queryPuanHqBjfSeq() {
        return sqlSession.selectOne(erpType+".queryPuanHqBjfSeq");
    }

    @Override
    public int insertPuanHyBjf(String id,String cardId,double score) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("cardId",cardId);
        map.put("score",score);
        map.put("rsaid","");
        map.put("llr",userId);
        map.put("bjfyy","线上积分同步");
        map.put("zt",1);
        map.put("placepointid",placepointid);
        return sqlSession.insert(erpType+".insertPuanHyBjf", map);
    }

    @Override
    public int insertGpcsInsiderIntegralinfo(String id,double score) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        if(score>=0){
            map.put("debitintegal",score);
            map.put("lenderintegal",0);
        }else{
            map.put("debitintegal",0);
            map.put("lenderintegal",score);
        }

        return sqlSession.insert(erpType+".insertGpcsInsiderIntegralinfo", map);
    }

    @Override
    public int updateScore(String cardId,double score) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("cardId",cardId);
        if(score>=0){
            map.put("integral",score);
            map.put("addintegral",score);
        }else{
            map.put("integral",score);
            map.put("addintegral",0);
        }

        return sqlSession.insert(erpType+".updateScore", map);
    }

    @Override
    public int updatePuanHyBjf(String id) {
        return sqlSession.update(erpType+".updatePuanHyBjf", id);
    }
}
