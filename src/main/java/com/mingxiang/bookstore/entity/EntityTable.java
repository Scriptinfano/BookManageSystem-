package com.mingxiang.bookstore.entity;

import java.util.ArrayList;

public class EntityTable {
    private final ArrayList<String>titles=new ArrayList<>();
    private final ArrayList<ArrayList<String>>data=new ArrayList<>();

    public ArrayList<String> getTitles() {
        return titles;
    }

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }
}
