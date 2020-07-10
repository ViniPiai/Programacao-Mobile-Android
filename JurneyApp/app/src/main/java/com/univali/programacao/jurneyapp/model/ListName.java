package com.univali.programacao.jurneyapp.model;

public class ListName {

    private int id;
    private String name;

    public ListName(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
