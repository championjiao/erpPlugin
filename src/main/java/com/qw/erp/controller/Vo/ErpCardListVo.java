package com.qw.erp.controller.Vo;

import com.qw.erp.eneity.ErpCardInfoDo;
import com.qw.util.ApiBody;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class ErpCardListVo extends ApiBody {
    private List<ErpCardInfoDo> resultList = new ArrayList<ErpCardInfoDo>();

    public ErpCardListVo(List<ErpCardInfoDo> cardList) {
        if (null != cardList && cardList.size() > 0) {
            resultList.addAll(cardList);
        }
    }

    public List<ErpCardInfoDo> getResultList() {
        return resultList;
    }

    public void setResultList(List<ErpCardInfoDo> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
