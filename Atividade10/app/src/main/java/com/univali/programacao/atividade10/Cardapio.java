package com.univali.programacao.atividade10;

import java.io.Serializable;

public class Cardapio implements Serializable {

    private String name;
    private String description;
    private float price;
    private int calories;
    private int quantity;

    public Cardapio(String name, String description, float price, int calories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.calories = calories;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cardapio{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", calories=" + calories +
                '}';
    }
}
