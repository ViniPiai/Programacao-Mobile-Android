package com.univali.programacao.atividade4.model;

import java.io.Serializable;

public class Cidade implements Serializable {

    private String nome;
    private String populacao;
    private String area;
    private String densidadePopulacional;

    public Cidade(String nome, String populacao, String area, String densidadePopulacional) {
        this.nome = nome;
        this.populacao = populacao;
        this.area = area;
        this.densidadePopulacional = densidadePopulacional;
    }

    public String getNome() {
        return nome;
    }

    public String getPopulacao() {
        return populacao;
    }

    public String getArea() {
        return area;
    }

    public String getDensidadePopulacional() {
        return densidadePopulacional;
    }

    @Override
    public String toString() {
        return nome;
    }
}
