package com.mingxiang.bookstore.entity;

import java.sql.Timestamp;

public class SaleOrder {

    private int theCustomerId;
    private Timestamp theDate;
    private String theContext;

    public SaleOrder(int theCustomerId, Timestamp theDate, String theContext) {
        this.theCustomerId = theCustomerId;
        this.theDate = theDate;
        this.theContext = theContext;
    }

    public int getTheCustomerId() {
        return theCustomerId;
    }

    public void setTheCustomerId(int theCustomerId) {
        this.theCustomerId = theCustomerId;
    }

    public Timestamp getTheDate() {
        return theDate;
    }

    public void setTheDate(Timestamp theDate) {
        this.theDate = theDate;
    }

    public String getTheContext() {
        return theContext;
    }

    public void setTheContext(String theContext) {
        this.theContext = theContext;
    }
}
