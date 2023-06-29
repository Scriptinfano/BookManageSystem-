package com.mingxiang.bookstore.entity;

public class Customer {


    private String name;
    private String phone;
    private String context;

    public Customer(String name, String phone, String context) {

        this.name = name;
        this.phone = phone;
        this.context = context;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
