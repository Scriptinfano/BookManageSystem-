package com.mingxiang.bookstore.entity;

public class Book {



    private String bookName;
    private String author;
    private String type;
    private String publisher;

    private String context;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Book(String bookName, String author, String type, String publisher, String context) {

        this.bookName = bookName;
        this.author = author;
        this.type = type;
        this.publisher = publisher;
        this.context = context;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
