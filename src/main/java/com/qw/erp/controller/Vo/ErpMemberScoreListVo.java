package com.qw.erp.controller.Vo;

import com.qw.util.ApiBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: ErpMemberScoreListVo
 * @description:
 * @author: zhumeiqing
 * @create: 2017/3/9
 **/
public class ErpMemberScoreListVo extends ApiBody {
    private List<ErpMemberScoreVo> cardList = new ArrayList<ErpMemberScoreVo>();

    public ErpMemberScoreListVo(){}

    public List<ErpMemberScoreVo> getCardList() {
        return cardList;
    }

    public void setCardList(List<ErpMemberScoreVo> cardList) {
        this.cardList = cardList;
    }
}
