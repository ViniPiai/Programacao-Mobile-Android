package com.univali.programacao.atividade7_agendapessoal.model;

import java.util.Date;

public class PersonalAgenda {

    private int id;
    private String name;
    private String description;
    private String date;

    public PersonalAgenda() {
    }

    public PersonalAgenda(Integer id, String name, String description, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
