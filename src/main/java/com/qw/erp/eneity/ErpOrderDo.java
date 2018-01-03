package com.qw.erp.eneity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
public class ErpOrderDo implements Serializable {
    private static final long serialVersionUID = -864435779884002229L;
    private String ordNo;                   // 交易id
    private String orgId;                   // 交易药房id
    private String orgName;                 // 交易药房
    private String erpCardId;               // 优惠卡内码
    private String erpCardCode;             // 优惠卡号
    private String erpCardName;             // 优惠卡名称
    private String userName;                // 用户名称
    private String mobile;                  // 手机号
    private String dateTime;                // 销售日期
    private double amountFanal;             // 实收金额
    private double amountPro;               // 应收金额
    private int score;                   // 产生的积分
    private double amountDiscount;          //优惠金额
    private List<ErpOrderDetailDo> orderDetails = new ArrayList<ErpOrderDetailDo>(); //商品信息

    public String getOrdNo() {
        return ordNo;
    }

    public void setOrdNo(String ordNo) {
        this.ordNo = ordNo;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getErpCardId() {
        return erpCardId;
    }

    public void setErpCardId(String erpCardId) {
        this.erpCardId = erpCardId;
    }

    public String getErpCardCode() {
        return erpCardCode;
    }

    public void setErpCardCode(String erpCardCode) {
        this.erpCardCode = erpCardCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getErpCardName() {
        return erpCardName;
    }

    public void setErpCardName(String erpCardName) {
        this.erpCardName = erpCardName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getAmountFanal() {
        return amountFanal;
    }

    public void setAmountFanal(double amountFanal) {
        this.amountFanal = amountFanal;
    }

    public double getAmountPro() {
        return amountPro;
    }

    public void setAmountPro(double amountPro) {
        this.amountPro = amountPro;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(double amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    public List<ErpOrderDetailDo> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<ErpOrderDetailDo> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "BillInfoDo{" +
                "ordNo='" + ordNo + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", erpCardId='" + erpCardId + '\'' +
                ", erpCardCode='" + erpCardCode + '\'' +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", amountFanal='" + amountFanal + '\'' +
                ", amountPro='" + amountPro + '\'' +
                ", score='" + score + '\'' +
                ", amountDiscount='" + amountDiscount + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
