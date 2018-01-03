package com.qw.erp.controller.Vo;

/**
 * @className: ErpMemberScoreVo
 * @description:
 * @author: zhumeiqing
 * @create: 2017/3/9
 **/
public class ErpMemberScoreVo {
    private String erpCardId;
    private double erpScore;

    @Override
    public String toString() {
        return "ErpMemberScoreVo{" +
                "erpCardId='" + erpCardId + '\'' +
                ", erpScore=" + erpScore +
                '}';
    }

    public String getErpCardId() {
        return erpCardId;
    }

    public void setErpCardId(String erpCardId) {
        this.erpCardId = erpCardId;
    }

    public double getErpScore() {
        return erpScore;
    }

    public void setErpScore(double erpScore) {
        this.erpScore = erpScore;
    }
}
