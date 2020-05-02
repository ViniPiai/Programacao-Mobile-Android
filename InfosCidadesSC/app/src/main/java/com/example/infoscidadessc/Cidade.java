package com.example.infoscidadessc;

import java.io.Serializable;

public class Cidade implements Serializable {

    private String nome ;
    private String numeroPopulacao;
    private String densidadeDemográfica;

    public Cidade(String nome, String numeroPopulacao, String densidadeDemográfica){
        this.nome = nome;
        this.numeroPopulacao = numeroPopulacao;
        this.densidadeDemográfica = densidadeDemográfica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroPopulacao() {
        return numeroPopulacao;
    }

    public void setNumeroPopulacao(String numeroPopulacao) {
        this.numeroPopulacao = numeroPopulacao;
    }

    public String getDensidadeDemográfica() {
        return densidadeDemográfica;
    }

    public void setDensidadeDemográfica(String densidadeDemográfica) {
        this.densidadeDemográfica = densidadeDemográfica;
    }

    @Override
    public String toString() {
        return nome;
    }
}
