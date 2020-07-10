package com.example.tripmanager;

public class Location {
    private String nome;
    private String descricao;
    private String imageUrl;

    public Location(String nome, String imageUrl, String descricao){
        this.nome = nome;
        this.imageUrl = imageUrl;
        this.descricao = descricao;
    }

    public String getNome(){return nome;}

    public void setNome(String nome){this.nome = nome;}

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
