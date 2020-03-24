package com.univali.programacao.atividade2.model;

public class Atleta implements Comparable<Atleta> {

    private String nome;
    private double tempo;

    public Atleta(String nome, double tempo) {
        this.nome = nome;
        this.tempo = tempo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    @Override
    public int compareTo(Atleta atleta) {
        if(tempo < atleta.tempo){
            return -1;
        }else if(atleta.tempo < tempo){
            return 1;
        }
        return 0;
    }
}
