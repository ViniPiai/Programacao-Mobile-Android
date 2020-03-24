package com.univali.programacao.atividade2.model;

import java.util.Comparator;

public class Candidatos implements Comparable<Candidatos> {

    private String nome;
    private Integer votos;

    public Candidatos(String nome, Integer votos) {
        this.nome = nome;
        this.votos = votos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    @Override
    public String toString() {
        return "Candidatos{" +
                "nome='" + nome + '\'' +
                ", votos=" + votos +
                '}';
    }

    @Override
    public int compareTo(Candidatos candidatos) {
        return votos.compareTo(candidatos.getVotos());
    }
}
