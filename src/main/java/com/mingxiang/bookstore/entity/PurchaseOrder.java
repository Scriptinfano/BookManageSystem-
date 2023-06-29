package com.mingxiang.bookstore.entity;

import java.sql.Timestamp;

public class PurchaseOrder {
    private int merchantId;
    private Timestamp stamp;
    private String context;

    public PurchaseOrder(int merchantId, Timestamp stamp, String context) {
        this.merchantId = merchantId;
        this.stamp = stamp;
        this.context = context;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public Timestamp getStamp() {
        return stamp;
    }

    public void setStamp(Timestamp stamp) {
        this.stamp = stamp;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
