package com.mingxiang.bookstore.entity;

public class Merchant {

    private String name;
    private String contact;

    private String context;

    public Merchant(String name, String contact, String context) {

        this.name = name;
        this.contact = contact;
        this.context = context;
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
