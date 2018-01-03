package com.qw.erp.eneity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/17.
 */
public class ErpOrderDetailDo implements Serializable {
    private static final long serialVersionUID = -6439504168309690198L;
    private String ordNo;              // 订单id
    private String proCode;           // 商品编码
    private String proName;           // 商品名称
    private int proQty;                 // 数量
    private double price;             // 单价
    private String amount;            //总价

    public String getOrdNo() {
        return ordNo;
    }

    public void setOrdNo(String ordNo) {
        this.ordNo = ordNo;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getProQty() {
        return proQty;
    }

    public void setProQty(int proQty) {
        this.proQty = proQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ErpProInfoDo{" +
                "ordNo='" + ordNo + '\'' +
                ", proCode='" + proCode + '\'' +
                ", proName='" + proName + '\'' +
                ", proQty='" + proQty + '\'' +
                ", price='" + price + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
