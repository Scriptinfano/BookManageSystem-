package com.mingxiang.bookstore.entity;

public class SaleDetailOrder {
    private int theSaleId;
    private int theBookId;
    private int theBookCount;

    public SaleDetailOrder(int theSaleId, int theBookId, int theBookCount) {
        this.theSaleId = theSaleId;
        this.theBookId = theBookId;
        this.theBookCount = theBookCount;
    }

    public int getTheSaleId() {
        return theSaleId;
    }

    public void setTheSaleId(int theSaleId) {
        this.theSaleId = theSaleId;
    }

    public int getTheBookId() {
        return theBookId;
    }

    public void setTheBookId(int theBookId) {
        this.theBookId = theBookId;
    }

    public int getTheBookCount() {
        return theBookCount;
    }

    public void setTheBookCount(int theBookCount) {
        this.theBookCount = theBookCount;
    }
}
