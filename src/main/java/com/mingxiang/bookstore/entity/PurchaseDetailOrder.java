package com.mingxiang.bookstore.entity;

import java.sql.Timestamp;

public class PurchaseDetailOrder {
    private int thePurchaseId;
    private int theBookId;
    private int theOrderNum;
    private float thePrice;

    public PurchaseDetailOrder(int thePurchaseId, int theBookId, int theOrderNum, float thePrice) {
        this.thePurchaseId = thePurchaseId;
        this.theBookId = theBookId;
        this.theOrderNum = theOrderNum;
        this.thePrice = thePrice;
    }


    public int getThePurchaseId() {
        return thePurchaseId;
    }

    public void setThePurchaseId(int thePurchaseId) {
        this.thePurchaseId = thePurchaseId;
    }

    public int getTheBookId() {
        return theBookId;
    }

    public void setTheBookId(int theBookId) {
        this.theBookId = theBookId;
    }

    public int getTheOrderNum() {
        return theOrderNum;
    }

    public void setTheOrderNum(int theOrderNum) {
        this.theOrderNum = theOrderNum;
    }

    public float getThePrice() {
        return thePrice;
    }

    public void setThePrice(float thePrice) {
        this.thePrice = thePrice;
    }
}
